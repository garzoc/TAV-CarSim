package src;

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
	int moveF(State state);

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
	int reverse(State state);
}