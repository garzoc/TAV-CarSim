
public class Ultrasonic implements Sensor{
	
	private boolean disabled;
	
	public Ultrasonic(){
		disabled=false;
		
	}
	
	public int get(){
		
		return 10;
	}
	
	public double read(int []spaces,int pos){
		//prepare an array with 2 sensors each with a space for 5 readings
    	double sensorData=0;
    	/*
    	 * tmp is the noise factor which is randomly generated
    	 * */
    	double tmp=0;
    	double max=1.15;
    	double min=0.85;


    			/*
    			 * use the map which has been generated and create a small amount noise of based on the min and max values
    			 * noise this allows us to go get similar values if the car goes back to a
    			 * previous position 
    			 * */
	    		sensorData=(int) (spaces[pos]* ((tmp=Math.random()*2)<min?min+((1-min)*tmp) : (tmp >max)? (max-(max-1)*(tmp-1)): tmp));
	    		/*
	    		 * When Car is at the specified position sensor will fail and output
	    		 * invalid values this can be done on both sensors
	    		 * */
	    		if(disabled){
	    			sensorData=0;
	    		}
	    		
	    		
    		
    		//System.out.println("max regulation "+(max-(max-1)*(tmp-1)));
    	
	
		return sensorData;
	}
	
	public boolean isDisabled(){
		return this.disabled;
	}
	
	public void disable(){
		this.disabled=true;
	}
	
	public void enable(){
		this.disabled=false;
	}
}
