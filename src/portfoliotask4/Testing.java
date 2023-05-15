package portfoliotask4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Testing {

	private List<String> passedTests = new ArrayList<>();
	private List<String> failedTests = new ArrayList<>();

	public static void main(String[] args) {
		Testing test = new Testing();
		test.runTests(Reflection01.class);
	}

	public void runTests(Class<?> testClass) {
		Method[] methods = testClass.getDeclaredMethods();
		Object testObject;
		try {
			testObject = testClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new RuntimeException("Could not create test object", e);
		}
		for (Method method : methods) {
			if (method.isAnnotationPresent(Test.class)) {
				try {
					method.invoke(testObject);
					passedTests.add(method.getName());
				} catch (Throwable t) {
					failedTests.add(method.getName() + ": " + t.getMessage());
				}
			}
		}
		printReport();
	}

	private void printReport() {
		System.out.println("----- Test Report -----");
		System.out.println("Passed Tests:");
		for (String testName : passedTests) {
			System.out.println(testName);
		}
		System.out.println("Failed Tests:");
		for (String testName : failedTests) {
			System.out.println(testName);
		}
		System.out.println("-----------------------");
	}

	public <T> void assertEquals(T expected, T actual) {
		if (expected == null && actual == null) {
			return;
		}
		if (expected == null || !expected.equals(actual)) {
			throw new AssertionError("Expected: " + expected + " but was: " + actual);
		}
	}

	public <T> void assertNotEquals(T expected, T actual) {
		if (expected == null && actual == null) {
			throw new AssertionError("Expected: not " + expected + " but was: " + actual);
		}
		if (expected == null || expected.equals(actual)) {
			throw new AssertionError("Expected: not " + expected + " but was: " + actual);
		}
	}

	public void assertFloatsEqual(float expected, float actual, float epsilon) {
		if (Math.abs(expected - actual) > epsilon) {
			throw new AssertionError("Expected: " + expected + " but was: " + actual);
		}
	}

	public void assertPrivateFieldEquals(Object obj, String fieldName, Object expectedValue) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			Object actualValue = field.get(obj);
			assertEquals(expectedValue, actualValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("Could not access field: " + fieldName, e);
		}
	}

	@Test
	public void testAssertEquals() {
		assertEquals("foo", "foo");
		assertEquals(null, null);
		assertEquals(42, 42);
		try {
			assertEquals("foo", "bar");
			throw new AssertionError("Should have failed");
		} catch (AssertionError e) {
			assertEquals("Expected: foo but was: bar", e.getMessage());
		}
	}

	@Test
	public void testMultiplyFloatA() { // SIMPLEMULTIPLY CLASS
		SimpleMultiply sm = new SimpleMultiply(2.0f, 3.0f);
		sm.multiplyFloatA();
		assertEquals(4.0f, sm.getFloatA());
	}

	@Test
	public void testMultiplyFloatB() {// SIMPLEMULTIPLY CLASS
		SimpleMultiply sm = new SimpleMultiply(2.0f, 3.0f);
		sm.multiplyFloatB();
		assertEquals(9.0f, sm.getFloatB());
	}

	@Test
	public void testToString() {// SIMPLEMULTIPLY CLASS
		SimpleMultiply sm = new SimpleMultiply(2.0f, 3.0f);
		assertEquals("(a:2.00, b:3.00)", sm.toString());
	}

	@Test
	public void testReflection01() throws Exception { // UNIT TEST REFLECTION 1
		SimpleMultiply s = new SimpleMultiply();
		Field fieldA = s.getClass().getField("a");
		Field fieldB = s.getClass().getDeclaredField("b");
		fieldB.setAccessible(true);

		// Verify initial state
		assertEquals(3.0f, fieldA.get(s));
		assertEquals(2.5f, fieldB.get(s));
	}

	@Test
	public void testReflection02() throws Exception { // UNIT TEST REFLECTION 2
		SimpleMultiply s = new SimpleMultiply();
		s.multiplyFloatA();

		// Access private member b using reflection
		Field bField = SimpleMultiply.class.getDeclaredField("b");
		bField.setAccessible(true);
		float b = (float) bField.get(s);

		// Verify the result
		assertEquals(2.5f, b);
	}

	@Test
	public void testReflection03() {
		SimpleMultiply s = new SimpleMultiply();
		assertEquals("class portfoliotask4.SimpleMultiply", s.getClass().toString());
		assertEquals("portfoliotask4.SimpleMultiply", s.getClass().getName());
	}

	@Test
	public void testReflection04() throws Exception {
		SimpleMultiply s = new SimpleMultiply();
		Field[] fields = s.getClass().getFields();
		assertEquals(1, fields.length);
		assertEquals("a", fields[0].getName());
		assertEquals(float.class, fields[0].getType());

		float expectedValue = 3.0f;
		float actualValue = fields[0].getFloat(s);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testReflection05() throws Exception {
		SimpleMultiply s = new SimpleMultiply();
		Field[] fields = s.getClass().getDeclaredFields();
		assertEquals(2, fields.length);

		Field fieldA = fields[0];
		assertEquals("a", fieldA.getName());
		assertEquals(float.class, fieldA.getType());
		fieldA.setAccessible(true);
		assertEquals(3.0f, fieldA.getFloat(s));

		Field fieldB = fields[1];
		assertEquals("b", fieldB.getName());
		assertEquals(float.class, fieldB.getType());
		fieldB.setAccessible(true);
		assertEquals(2.5f, fieldB.getFloat(s));
	}

	@Test
	public void testReflection06() throws Exception {
		SimpleMultiply s = new SimpleMultiply();
		Field[] fields = s.getClass().getDeclaredFields();
		assertEquals(2, fields.length);

		Field fieldA = fields[0];
		assertEquals("a", fieldA.getName());
		assertEquals(float.class, fieldA.getType());
		assertFalse(fieldA.isAccessible());

		Field fieldB = fields[1];
		assertEquals("b", fieldB.getName());
		assertEquals(float.class, fieldB.getType());
		assertFalse(fieldB.isAccessible());
	}

	@Test
	public void testReflection07() throws Exception {
		SimpleMultiply s = new SimpleMultiply();
		Field[] fields = s.getClass().getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);

			if (f.getName().equals("a")) {
				Assertions.assertEquals(3.0f, f.getFloat(s));
			} else if (f.getName().equals("b")) {
				Assertions.assertEquals(2.5f, f.getFloat(s));
			} else {
				Assertions.fail("Unexpected field name: " + f.getName());
			}
		}
	}

	@Test
	public void testReflection08() throws Exception {
		SimpleMultiply s = new SimpleMultiply();
		Field[] fields = s.getClass().getDeclaredFields();
		System.out.printf(" There are %d fields \n", fields.length);

		for (Field f : fields) {
			f.setAccessible(true);
			Float expectedValue = f.getFloat(s) + 1;
			f.setFloat(s, expectedValue);

			Float actualValue = f.getFloat(s);
			assertEquals(expectedValue, actualValue);
		}
	}

	@Test
    public void testReflection10() throws Exception {
        SimpleMultiply s = new SimpleMultiply();
        Method m = s.getClass().getDeclaredMethod("setA", float.class);
        m.setAccessible(true);
        m.invoke(s, 76);
        assertEquals("(a:76.00, b:2.50)", s.toString());
    }
}