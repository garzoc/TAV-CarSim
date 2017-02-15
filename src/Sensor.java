
interface Sensor {
	
	public double read(int[] road,int pos);	
	
	public boolean isDisabled();

	public void disable();
	
	
	public void enable();
	
}
