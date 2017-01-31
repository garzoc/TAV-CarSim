/**
 * Class for running the test cases
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ActionsTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ActionsTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
