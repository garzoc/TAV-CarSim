
	/**
	 * Class for running the test cases
	 */

	import org.junit.runner.JUnitCore;
	import org.junit.runner.Result;
	import org.junit.runner.notification.Failure;
public class TestMain {

	
	


	        



	    public static void main(String[] args) {
	       
	       // generateMap(spaces);
		    //Actions pa = new Actions(0,false);
		    
		    Result result = JUnitCore.runClasses(CarTest.class);

	        for (Failure failure : result.getFailures()) {
	            System.out.println(failure.toString());
	        }

	        System.out.println("\n"+result.wasSuccessful());
	        
	        
	       Result mockResult = JUnitCore.runClasses(MockClass.class);

	        for (Failure failure : mockResult.getFailures()) {
	            System.out.println(mockResult.toString());
	        }

	        System.out.println("\n"+mockResult.wasSuccessful());


	    }
	    
	

}
