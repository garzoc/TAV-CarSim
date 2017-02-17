import java.util.LinkedList;

public class Car  implements Parkable {
	
	/*
	 * Keeps the state of the car
	 * like whether it's parked or not it's position and how many open spaces 
	 * it has found adjacent to eachother
	 */
    private State state=new State();

	/*
     * Specifies at which position a certain sensor will fail
     * failPosition1 mapped to sensor 1
     * ailPosition2 mapped to sensor 2
     */
    public LinkedList<FreeSpace> freeSpaces=new LinkedList<>();

    int failPosition1=-1;
    int failPosition2=-1;

	/*
     * This array represents the road each cell represents one meter
     */
    Sensor sensors[];
    Actuator carEngine;


    public double[] spaces = new double[500];
    
    
    public void generateMap(double[] spaces) {
    	//System.out.println(spaces);
    	if(spaces==null) spaces=new double[0];
    	for (int i =0; i<spaces.length;i++) {
            this.spaces[i] = spaces[i];
        }
    	
    	/*
    	 * as for the rest create random map of the road
    	 * */
    	
        for (int i =spaces.length; i<500;i++) {
            this.spaces[i] = (Math.random()*200);
          
        }
    }

    public  Car(int position, boolean isParked) {
   
    	state.position = position;
		state.isParked = isParked;
    	this.sensors = new Ultrasonic[2];
    	this.sensors[0] = new Ultrasonic();
    	this.sensors[1] = new Ultrasonic();
    	this.carEngine=new CarEngine();
    	//generateMap(spaces);
    }
    
    public  Car(int position, boolean isParked,Sensor[] ultrasonics) {
    	state.position = position;
    	state.isParked = isParked;
    	this.sensors=ultrasonics;
    	this.carEngine=new CarEngine();
    }
    
    public  Car(int position, boolean isParked,Sensor[] ultrasonics,Actuator carEngine) {
    	state.position = position;
    	state.isParked = isParked;
    	this.sensors=ultrasonics;
    	this.carEngine=carEngine;
    }

    public void Park() {
    
            while ( !state.isParked && state.position < 499) {
            
                State state=MoveForward();
               /*
                * stretch keeps track of how many ajdecent openspaces there are adjacent to eacheother from the
                * current position and if there are 5 open space that means that the car can park there
                * */
                if (state.streak >= 5) {
                	/*set the parking state of the car to true 
                	 * and parallel park increment then decrement the position by one
                	 * */
                	state.position++;
                	state.isParked = true;
                	state.position -= 1;
                }
            }
    }

    public void UnPark() {
    	/*
    	 * if car is parked the we can unpark without checking anything else
    	 * and that means that nothing happens when we try to unpark while not parked
    	 * */
        if (state.isParked) {
        	/*
        	 * set the parking the state the false
        	 * */
        	state.isParked = false;
        	/*
        	 * increment the position of the car by one
        	 * */
        	state.position += 1;
        }
    }

    public State MoveForward() {
    	/*
    	 * the car should not be able to move forward while it's parked
    	 * */
        if (state.isParked) {
        	
            System.out.println("ERROR - PARKED, CANNOT MOVE");
            /*
             * if car is within bounds it can be moved
             * */
        } else {
        	/*
        	 * increment the position of the car
        	 * */
        	//state.position += 1;
        	state.position+=carEngine.moveF(state);
        	/*
        	 * isEmpty return an array where each cell represent the average filtered value from a sensor 
        	 * */
            double[] data=isEmpty();
           
            /*
             * checks if one or more sensors are disabled by testing if it returned an
             *  invalid number (0) then discard that sensor
             * */
            int count=0;
            int avg=0;
            for(int n=0;n<data.length;n++){
            	//System.out.println("pos is "+state.position+" and avg is "+data[n]);
            	if(data[n]!=0){
            		count++;
            		avg+=data[n];
            	}	
            }
           
            /*
             * get average from the sensors
             * 
             * */
            if(count!=0)
            	avg=avg/count;
            
            
            //System.out.println(avg);
            if(avg > 150) state.streak++;
            	else state.streak=0;
            //System.out.println("pos is "+state.position+" avg "+avg);
            if(state.streak>=5){
            	//System.out.println("pos is "+state.position+" streak is "+state.streak+" first pos is "+(state.position-state.streak));
            	int i=0;
            	for(i=0;i<freeSpaces.size();i++){
            		//System.out.println("pos is "+state.position+" space pos is "+freeSpaces.get(i).position+" first pos is "+(state.position-state.streak));
            		if (state.position-state.streak >=freeSpaces.get(i).position && state.position-state.streak <= freeSpaces.get(i).position+freeSpaces.get(i).size){
            			
            			if(state.streak >= freeSpaces.get(i).size)freeSpaces.get(i).size=state.streak;
            				else
            					state.streak= freeSpaces.get(i).size-(state.position-freeSpaces.get(i).position);
            			break;
            		}
            	}
            	if(i==freeSpaces.size()) freeSpaces.add(new FreeSpace(state.position-state.streak,state.streak));
            	//System.out.println("pos it is "+freeSpaces.get(freeSpaces.size()-1).position);
            }
           
        }
        /*
         * return the car status
         * */
        return state;
    }
    
    public State MoveBackward() {
    	/*
    	 * reset the streak to zero
    	 * */
    	if(state.streak!=0)state.streak--;
    	/*
    	 * car cannot move while parked
    	 * */
        if (state.isParked) {
            System.out.println("ERROR - PARKED, CANNOT MOVE");
            /*
             * car is within bounds move it backwards by one
             * */
        } else {
        	//state.position -= 1;
        	state.position+=carEngine.reverse(state);
        	/*when the car is out out of bounds print an error message but don't move it*/
        }/*else {
           System.out.println("ERROR - OUT OF BOUNDS, CANNOT MOVE");
        }
        /**/
        
        return state;
    }
    
    public void disableSensor(int pos1,int pos2){
    	/*
    	 * set the position of when sensor 1 and 2 will fail 
    	 * */
    	failPosition1=pos1;
    	failPosition2=pos2;
    }

    /*
     * read distance with noise and filter that noise
     */
    public double[] isEmpty() {
    	
    	//for(int k=0;k<5;k++) this.sensor1.read();
    	
    	//prepare an array with 2 sensors each with a space for 5 readings
    	double[][] sensorData= new double[2][5];
    	/*
    	 * tmp is the noise factor which is randomly generated
    	 * */
    
    	/*
    	 * for each sensor read data
    	 * */
    	
    	for(int i=0;i<2 && !state.isParked;i++){
    		/*
    		 * for each sensor make 5 readings
    		 * */
    		sensors[i].enable();
    		for(int n=0;n<5;n++){
    			
    			
    			if(state.isParked||(state.position==failPosition1&&i==0)){
	    			sensors[i].disable();
	    		}
    			
    			if(state.isParked||(state.position==failPosition2&&i==1)){
	    			sensors[i].disable();
	    		}
    			
	    		sensorData[i][n]=sensors[i].read(spaces,state.position);
	    		/*
	    		 * When Car is at the specified position sensor will fail and output
	    		 * invalid values this can be done on both sensors
	    		 * */
	    		
    		}
    	}
    
    	//for(int i=0;i<sensors.length;i++) sensors[i].enable();
    	
    	
    	/*
    	 * defined the max and min delta values between the different readings if two or more values are within this
    	 * range they will be added together if not the will get their own group of values
    	 * */
    	double fmax=1.3;
    	double fmin=0.75;
    	
    	/*
    	 *failCount keeps track how how many times a sensor has output an invalid number
    	 * */
    	int[] failCount=new int[2];
    	/*
    	 * this array represent the two sensors
    	 * */
    	double[] filteredData=new double[2];
    	
    	/*
    	 * for every sensor when car is not parked
    	 * */
    	for(int i=0;i<2;i++){
    		/*
    		 * this keeps track how many values there are in each group of values
    		 * so we know how what we should divide the sum of each group with
    		 * */
    		int[] count=new int[5];
    		/*
    		 * this keeps track of the sum for each group of values
    		 * so we later can calculate the average values
    		 * */
    		LinkedList<Double> list=new LinkedList<Double>();
    		/*
    		 * add the first value to the linked list 
    		 * and so the first value group has one value so increment the count by one
    		 * */
    		list.add(sensorData[i][0]);
    		count[0]++;
    		/*
    		 * now go through the the 4 other value
    		 * */
    		for(int n=1;n<5;n++){
    			/*
    			 * defined x up here so that we can compare the size of the list to how many iteration the for loop did
    			 * */
    			int x=0;
    			/*
    			 * for every group in the list
    			 * */
    			//System.out.println("pos is "+state.position+" and sensors is "+i+" read "+sensorData[i][n]);
    			for(x=0;x<list.size();x++){
    				/*
    				 * for every group take the average value and comapre it to the reading and if the are
    				 * reading is within range add the reading to that group that was compared and increment count by one
    				 * and break
    				 * */
    				if((list.get(x)/count[x])/sensorData[i][n]>fmin && fmax > (list.get(x)/count[x])/sensorData[i][n]){
    					
    					/*
    					 * 230 is the maximum noise values
    					 * if the sensor output is outside the valid range increment failcount by one
    					 * */
    					if(sensorData[i][n]<=0 || sensorData[i][n]>230){
    						
    						failCount[i]++;
    					}else{
    						/*
    						 * if sensor value is valid then add it to the group of values and increment count by one
    						 * */
    						list.set(x,(list.get(x)+sensorData[i][n]));
    						count[x]++;
    					}
    					break;
    				}
    			}
    			/*
    			 * if x is equal to the size of the list that means the the values wasn't added to an
    			 * existing group therefore add it to a new group 
    			 * */
    			if(x==list.size()){
    				/*
					 * 230 is the maximum noise values
					 * if the sensor output is outside the valid range increment failcount by one
					 * otherwise add it to a new group of values
					 * */
    				if(sensorData[i][n]<=1 || sensorData[i][n]>230)
						failCount[i]++;
					}else{
						list.add(sensorData[i][n]);
					}
    				
    			}	
    		
    		
    		/*
    		 * this values represent the index of the group that has had most values added to it
    		 * */
    		int maxnumberIndex=0;
    		/*
    		 * loop through the count array and if there a group with more values set 
    		 * maxnumberIndex to that index
    		 * */
    		for(int n=0;n<count.length;n++){
    			if(count[n] >count[maxnumberIndex]) maxnumberIndex=n;
    		}
    		/*
    		 * if the number of failed sensor output is less than 2
    		 *  set the value for each sensor the the average of the biggest group of values in the list
    		 * otherwise set the values to 0
    		 * */
    		if(failCount[i]<2){
    			sensors[i].disable();
    			filteredData[i]=list.get(maxnumberIndex)/count[maxnumberIndex];
    		}else{
    			
    			filteredData[i]=0;
    			//System.out.println(filteredData[i]+"  i "+i);
    		}
    		
    	}
    	/*
    	 * return the value for the two sensor
    	 * */
    	
		return filteredData;
    }

    public State WhereIs() {
    	/*
    	 * simply return the state of the car no checking is required
    	 * */
		return state;
    }

}