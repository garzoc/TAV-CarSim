
public interface Actuator {

	/**
	 * Description:
	 * Moves the car forward one meter, but limited to bounds
	 * Pre-Condition:
	 * 1. Car is not parked
	 * Post-Condition:
	 *
     * Test-Cases:
     *
	 * @return
	 */
	void moveForward(Car.State state);

	/**
	 * Description:
	 * Moves the car backwards one meter, but limited to bounds
	 * Pre-Condition:
	 * 1. Car is not parked
	 * Post-Condition:
	 *
     * Test-Cases:
     *
	 * @return
	 */
	void moveBackward(Car.State state);
}
