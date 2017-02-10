
/**
 * Interface file for the Actions file.
 */
interface ParkingAssistant {

	public class State{
    	public int position;
    	public boolean isParked;
    	public int stretch;
    	public State(){
    		position=0;
    		isParked=false;
    		stretch=0;
    	}
    	
    }
	
	/*
	 * Description:
	 * This class contains the the current position of the cart
	 * and whether it's is parked or not
	 * as well as stretch which is a counter of how many open spaces that are adjacent to each other.
	 * */
	
	
	//---------------------

    State MoveForward();

    /**
     * Description:
     * This method is used to make the car move forward
     * Pre-Condition:
     * 1. Car is not parked
     * 2. Car isn't at the end of the street
     * Post-Condition:
     * 1. Car is moved forward by 1 meters
     * 2. Method returns position of the car
     * Test-Cases:
     * 1. @Test moveForward()
     */



    //---------------------

    double[] isEmpty();

    /**
     * Description:
     * This method Checks is there is an empty parking space.
     * Pre-Condition:
     * 1. Car is not parked
     * 2. Car is moving forward
     * Post-Condition:
     * 1. Returns true if adjacent space is empty
     * Test-Cases:
     * 1. @Test isEmpty()
     * 2. Test sensor outputs
     * 3. Test noise filtering
     * @return 
     */

    //---------------------

    State MoveBackward();

    /**
     * Description:
     * This method is used for moving the car backwards
     * Pre-Condition:
     * 1. Car is not parked
     * 2. Car is not at the beginning of the street
     * Post-Condition:
     * 1. The car gets moved 1 meter backwards
     * Test-Cases:
     * 1. @Test MoveBackward()
     * 2.
     */

    //---------------------

    void Park();

    /**
     * Description:
     * This method is used for the car to be able to park in a free parking space
     * Pre-Condition:
     * 1. Car is not parked
     * 2. The car is positioned on the road at the beginning of a free parking space
     * Post-Condition:
     * 1. The car gets parked in a parking space
     * Test-Cases:
     * 1. @Test park()
     * 2.
     */

    //---------------------

    void UnPark();

    /**
     * Description:
     * This method is used for the car to be able to move out of a parkingspace
     * 1. Car is parked
     * Post-Condition:
     * 1. The car gets out of the parking space
     * 2. The car gets placed in front of the parking space that the car was parked in
     * Test-Cases:
     * 1. @Test UnPark()
     * 2.
     */

    //---------------------

    State WhereIs();

    /**
     * Description:
     * This method is used for keeping track of the cars position on the street as well as if the car is parked or not
     * Post-Condition:
     * 1.Method returns the position of the car
     * Test-Cases:
     * 1. @Test Whereis()
     * 2.
     */

    //---------------------
}