
	/**
	 * Class for running the test cases
	 */

	import org.junit.runner.JUnitCore;
	import org.junit.runner.Result;
	import org.junit.runner.notification.Failure;
public class TestMain {

	
	


	        



	    public static void main(String[] args) {
	        int[] spaces = new int[500];
	        generateMap(spaces);
		    //Actions pa = new Actions(0,false);
		    
		    Result result = JUnitCore.runClasses(CarTest.class);

	        for (Failure failure : result.getFailures()) {
	            System.out.println(failure.toString());
	        }

	        System.out.println(result.wasSuccessful());

	    }
	    public static void generateMap(int[] spaces) {
	        for (int i =0; i<500;i++) {
	            spaces[i] = (int) (Math.random()*200);
	            System.out.println(spaces[i]);
	        }
	    }
	

}
