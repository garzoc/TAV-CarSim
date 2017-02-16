
interface Sensor {

	/**
	 * Description:
	 * Reads a position of the road and returns the space size.
	 * Pre-Condition:
	 * 1. pos is not out of bounds
	 * Post-Condition:
	 *
	 * @return
	 */
	public double read(int[] road,int pos);


	/**
	 * Description:
	 * Checks if this sensor is disabled
	 * Pre-Condition:
	 *
	 * Post-Condition:
	 *
	 * @return
	 */
	public boolean isDisabled();

	/**
	 * Description:
	 * Disables this sensor
	 * Pre-Condition:
	 * 1. Sensor is enabled.
	 * Post-Condition:
	 * 1. Sensor is disabled.
	 *
	 * @return
	 */
	public void disable();


	/**
	 * Description:
	 * Disables this sensor
	 * Pre-Condition:
	 * 1. Sensor is disabled.
	 * Post-Condition:
	 * 1. Sensor is enabled.
	 *
	 * @return
	 */
	public void enable();

}
