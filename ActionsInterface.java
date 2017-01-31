import org.junit.runners.Parameterized;

/**
 * Interface file for the Actions file.
 */
interface ActionsInterface {

    //---------------------

    void MoveForward();

    /**
     * Description:
     * This method is used to make the car move forward
     * Pre-Condition:
     * 1. Car is on the road and
     * 2. Car is not parked
     * 3. Car isn't at the end of the street
     * Post-Condition:
     * 1. Car is moved forward by 1 meters
     * 2. Method returns position of the car and If the space on the right side is free
     * Test-Cases:
     * 1. @Test moveForward()
     * 2.
     */



    //---------------------

    void isEmpty();

    /**
     * Description:
     * This method Checks is there is an empty parking space.
     * Pre-Condition:
     * 1. Car is on the road and
     * 2. Car is not parked
     * 3. Car is moving forward
     * Post-Condition:
     * 1. The empty space added to a list
     * Test-Cases:
     * 1. @Test isEmpty()
     * 2.
     */

    //---------------------

    void MoveBackward();

    /**
     * Description:
     * This method is used for moving the car backwards
     * Pre-Condition:
     * 1. Car is on the road and
     * 2. Car is not parked
     * 3. Car is not at the beginning of the street
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
     * 1. Car is on the road and
     * 2. Car is not parked
     * 3. An empty space is found
     * 4. The car is positioned on the road at the beginning of the free parking space
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

    void WhereIs();

    /**
     * Description:
     * This method is used for keeping track of the cars position on the street as well as if the car is parked or not
     * Post-Condition:
     * 1.Method shoiuld return the position of the car and whether or not it's parked
     * Test-Cases:
     * 1. @Test Whereis()
     * 2.
     */

    //---------------------


}
