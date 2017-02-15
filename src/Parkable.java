
/**
 * Interface file for the Actions file.
 */
interface Parkable {

	public class State{
    	public int position;
    	public boolean isParked;
    	public int streak;
    	public State(){
    		position=0;
    		isParked=false;
    		streak=0;
    	}
    	
    }
	
	/*
	 * Description:
	 * This class contains the the current position of the cart
	 * and whether it's is parked or not
	 * as well as streak which is a counter of how many open spaces that are adjacent to each other.
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
     * 1. @Test MoveForwardOnce()
     * 2. @Test MoveForwardOutOfBounds()
     * 3. @Test MoveForwardFromOutOfBounds()
     * 4. @Test MoveForwardWhileParked()
     * 
     */



    //---------------------

    double[] isEmpty();

    /**
     * Description:
     * This method Checks is there is an empty parking space.
     * Pre-Condition:
     * 1. Car is not parked
     * 2. Car is moving
     * Post-Condition:
     * 1. Returns an array of sensors
     * Test-Cases:
     * 1. @Test isEmptySensor1()
     * 2. @Test isEmptySensor2()
     * 3. @Test readSensorWhileParked()
     * 
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
     * 2. @Test MoveBackwardOutOfBounds()
     * 3. @Test MoveBackwardFromOutOfBounds()
     * 4. @Test MoveBackwardWhileParked()
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
     * 1. @Test oneSensorDisabledPark()
     * 2. @Test allSensorsDiabledPark()
     * 3. @Test Park()
     * 4. @Test ParkInFirstSpace()
     * 5. @Test ParkWhileParked
     * 
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
     * 2. @Test UnParkWhileNotParked
     */

    //---------------------

    State WhereIs();

    /**
     * Description:
     * This method is used for keeping track of the cars position on the street as well as if the car is parked or not
     * Post-Condition:
     * 1.Method returns the position of the car
     * Test-Cases:
     * 1. @Test WhereIs()
     * 2.
     */

    //---------------------
}