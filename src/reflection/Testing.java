package reflection;
import java.lang.reflect.*;
import java.util.*;

public class Testing {
    private List<String> passedTests = new ArrayList<>();
    private List<String> failedTests = new ArrayList<>();
    
    
    public static void main(String[] args) {
        Testing test = new Testing();
        test.runTests(SimpleMultiply.class);
    }
  
    /**********************************
     * When the runTests method attempts to invoke a method that starts with "check" using reflection,
     *  it catches any exception that occurs while invoking the method and adds the method's name to the failedTests list.
     *   This ensures that if a test causes an exception,
     *    the framework will not misbehave and will record the failure in the report.
     * @param testClass
     */

    public void runTests(Class<?> testClass) { // Resilience to Expectations
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {  // The behaviour is 
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
        assertFloatsEqual(expected, actual, 0.01f); //Simple Assertations and Reporting 
    }
    /*
     * The assertFloatsEqual method provides a simple assertion by checking if two float values are equal with a given epsilon.
     *  If the assertion fails, it throws an AssertionError.*/
}

