
	/**
	 * Class for running the test cases
	 */

	import org.junit.runner.JUnitCore;
	import org.junit.runner.Result;
	import org.junit.runner.notification.Failure;


public class Main {

	    public static void main(String[] args) {
	       


	    	// generateMap(spaces);
		    //Actions pa = new Actions(0,false);

		   /* Result result = JUnitCore.runClasses(CarUnitTests.class);

	        for (Failure failure : result.getFailures()) {
	            System.out.println(failure.toString());
	        }

	        System.out.println("\n"+result.wasSuccessful());*/


//	       	Result mockResult = JUnitCore.runClasses(UnitMocks.class);
//	        for (Failure failure : mockResult.getFailures()) {
//	            System.out.println(mockResult.getFailures().toString());
//	        }

			Result scenario1 = JUnitCore.runClasses(Scenario1.class);
			for (Failure failure : scenario1.getFailures()) {
				System.out.println(scenario1.getFailures().toString());
			}
			System.out.println(scenario1.getFailureCount());

//			Result scenario2 = JUnitCore.runClasses(Scenario2.class);
//			for (Failure failure : scenario2.getFailures()) {
//				System.out.println(scenario2.toString());
//			}


	        //System.out.println("\n"+mockResult.wasSuccessful());


	    }
	    
	

}
