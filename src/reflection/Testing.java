package reflection;

import java.lang.reflect.*;
import java.util.*;

public class Testing {
    private List<String> passedTests = new ArrayList<>();
    private List<String> failedTests = new ArrayList<>();

    public void runTests(Class<?> testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("check")) {
                try {
                    method.invoke(testClass.newInstance());
                    passedTests.add(method.getName());
                } catch (Exception ex) {
                    failedTests.add(method.getName());
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

    public void assertFloatsEqual(float expected, float actual, float epsilon) {
        if (Math.abs(expected - actual) > epsilon) {
            throw new AssertionError("Floats not equal");
        }
    }

    public void assertPrivateFloatB(SimpleMultiply obj, float expected) throws Exception {
        Field field = obj.getClass().getDeclaredField("b");
        field.setAccessible(true);
        float actual = (float) field.get(obj);
        assertFloatsEqual(expected, actual, 0.01f);
    }
}

