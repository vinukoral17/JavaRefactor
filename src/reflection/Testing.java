package reflection;

import java.lang.reflect.*;
import java.util.*;

import org.junit.Test;

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
	public void testSimpleMultiply() {
		SimpleMultiply obj = new SimpleMultiply(2.0f, 3.0f);
		obj.multiplyFloatA();
		assertEquals(4.0f, obj.getFloatA());
		obj.setFloatA(3.0f);
		assertEquals(3.0f, obj.getFloatA());
		obj.multiplyFloatB();
		assertEquals(6.0f, obj.getFloatB());
	}

}