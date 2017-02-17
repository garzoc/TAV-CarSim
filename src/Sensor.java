
public interface Sensor {

	double read(double[] road, int pos);
	/**
	 * Method used for reading a sensor.
	 * Pre-Condition
	 * 1. Car is on the road
	 * Post-Condition
	 * 1. The sensor returns a reading.
	 * Tests
     * @return int
     */

	void disable();
	/**
	 * Method used for disabling a faulty sensor.
	 * Pre-Condition
	 * 1. Car is on the road
	 * 2. A sensor reading is faulty
	 * Post-Condition
	 * 1. The sensor is disabled
	 * Tests
	 */

	void enable();
	/**
	 * Method used for enabling a previously faulty sensor.
	 * Pre-Condition
	 * 1. Car is on the road
	 * 2. A sensor is disabled
	 * Post-Condition
	 * 1. The sensor is enabled
	 * Tests
	 */
}
