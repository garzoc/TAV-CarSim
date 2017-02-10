import java.util.LinkedList;

/**
 * Created by laptop on 2017-02-06.
 */
public class Car  implements ParkingAssistant {
    private State state=new State();
    //private int[] parkingSpaces = new int[100];
    

    
    private int[] spaces = new int[500];

    public void generateMap(int[] spaces) {
    	/*
    	 * Create predefined obsatcles at distance 100
    	 * to the test how well we can filter out the noise  
    	 * */
    	for (int i =0; i<5;i++) {
            this.spaces[i] = 100;
        }
    	/*
    	 * Create predefined parking space from 5 to 10
    	 * so we can test that the parking method actually is consistense
    	 * 
    	 * */
    	for (int i =5; i<10;i++) {
            this.spaces[i] = 178;
        }
    	/*
    	 * for the rest create random map for the rest of the road
    	 * */
        for (int i =10; i<500;i++) {
            this.spaces[i] = (int) (Math.random()*200);
          
        }
    }
    
    
    public  Car(int position, boolean isParked) {
    	/*
    	 * set the position of the care and 
    	 * so that we can test different posintoin without having to use move forward or backward
    	 * */
    	state.position = position;
    	/*
    	 * set the car is parked status so that we can test what happens when we we call different methods
    	 * while the car is parked without having to use the park method
    	 * */
    	state.isParked = isParked;
    	/*
    	 * generate a random map where the first 10 metres are constant 
    	 * */
    	generateMap(spaces);
    }

    public void Park() {
    		/*
    		 * look for a parking space as long as the car is not parked
    		 * and the car is not out of bounds
    		 * */
            while ( !state.isParked && state.position < 500) {
            	/*
            	 * for every iteration move the car forward one metre
            	 * */
                State state=MoveForward();
               /*
                * strtch keeps track of how many ajdecent openspaces there are adjacent to eacheother from the
                * current position and if there are 5 open space that means that the car can park there
                * */
                if (state.stretch >= 5) {
                	/*set the parking state of the car to true 
                	 * and decrement the posinot by one
                	 * */
                	state.isParked = true;
                	state.position -= 1;
                }
            }
    }

    public void UnPark() {
    	/*
    	 * if car is parked the we can unpark without checking anything else
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
        } else if(state.position<spaces.length-1 &&state.position >= 0){
        	/*
        	 * increment the position of the car
        	 * */
        	state.position += 1;
        	/*
        	 * isEmpty return an array where each cell represent the average filtered value from a sensor 
        	 * */
            double[] data=isEmpty();
            /*
             * get average from both sensors
             * */
            int avg=(int) ((data[0]+data[1])/2);
            /*
             * if the average is greater than 150 which means that there is a open space
             * increment stretch by one
             * if not reset stretch to zero
             * */
            if(avg > 150) state.stretch++;
            	else state.stretch=0;
           
        }
        /*
         * return the car status
         * */
        return state;
    }
    
    public State MoveBackward() {
    	/*
    	 * reset the stretch to zero
    	 * */
    	state.stretch=0;
    	/*
    	 * car cannot move while parked
    	 * */
        if (state.isParked) {
            System.out.println("ERROR - PARKED, CANNOT MOVE");
            /*
             * car is within bounds move it backwards by one
             * */
        } else if (state.position > 0 && state.position<spaces.length-1){
        	state.position -= 1;
        	/*when the car is out ofbouns print an error message*/
        }else {
           System.out.println("ERROR - OUT OF BOUNDS, CANNOT MOVE");
        }
        /*
         * return the state of the car
         * */
        return state;
    }

    /*
     * distance reader with nose and noise filter*/
    public double[] isEmpty() {
    	//prepare an array with 2 sensors each with a space for 5 readings
    	double[][] sensorData= new double[2][5];
    	/*
    	 * tmp is the noise factor which is randomly generated
    	 * */
    	double tmp;
    	/*
    	 *specify maximum noise so that the noise is somewhat controlled and isn't 
    	 * completely random and breaks the test
    	 * */
    	double max=1.3;
    	/*
    	 *specify minimum noise so that the noise is somewhat controlled and isn't 
    	 * completely random and breaks the test
    	 * */
    	double min=0.85;
    	/*
    	 * for each sensor read data
    	 * */
    	for(int i=0;i<2;i++){
    		/*
    		 * for each sensor make 5 readings
    		 * */
    		for(int n=0;n<5;n++){
    			/*
    			 * use the map which has been generated and create a small amount noise of based on the min and max values
    			 * noise this allows us to go get similar values if the car goes back to a
    			 * previous position 
    			 * */
	    		sensorData[i][n]=(int) (spaces[state.position]* ((tmp=Math.random()*2)<min?min+((1-min)*tmp) : (tmp >max)? (max-(max-1)*(tmp-1)): tmp));
	    		//System.out.println("sensor "+i+" actual distance "+spaces[state.position]+" noised is "+sensorData[i][n]);
	    		//System.out.println("percantage is "+sensorData[i][n]/spaces[state.position]);
    		}
    		//System.out.println("max regulation "+(max-(max-1)*(tmp-1)));
    	}
    	
    	
    	/*
    	 * defined the max and min delta values between the different readings if two or more values are within this
    	 * range they will be added together if not the will get their own group of values
    	 * */
    	double fmax=1.3;
    	double fmin=0.75;
    	
    	/*
    	 * this array represent the two sensors
    	 * */
    	double[] filteredData=new double[2];
    	/*
    	 * for every sensor
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
    			for(x=0;x<list.size();x++){
    				/*
    				 * for every group take the average value and comapre it to the reading and if the are
    				 * reading is within range add the reading to that group that was compared and increment count by one
    				 * and break
    				 * */
    				if((list.get(x)/count[x])/sensorData[i][n]>fmin && fmax > (list.get(x)/count[x])/sensorData[i][n]){
    					list.set(x,(list.get(x)+sensorData[i][n]));
    					count[x]++;
    					break;
    				}
    			}
    			/*
    			 * if x is equal to the size of the list that means the the values wasn't added to an
    			 * existing group thefor add it to a new group 
    			 * */
    			if(x==list.size()){
    				list.add(sensorData[i][n]);
    			}	
    		}
    		
    		/*
    		 * this values represnt the index of the group that has most values added to it
    		 * */
    		int maxnumberIndex=0;
    		/*
    		 * loop through the count array and if there a group with more values set 
    		 * maxnumberIndex to that index
    		 * */
    		for(int n=0;n<count.length;n++){
    			if(count[n] >count[maxnumberIndex]) maxnumberIndex=n;
    		}
    		//System.out.println("original value "+spaces[state.position]+" noised reduced "+list.get(maxnumberIndex)/count[maxnumberIndex]);
    		/*
    		 * finally set the value for each sensor the the average of the biggest group belonging that sensor
    		 * */
    		filteredData[i]=list.get(maxnumberIndex)/count[maxnumberIndex];
    		
    	}
    	/*
    	 * return the value for the two sensor
    	 * */
		return filteredData;
    }

    public State WhereIs() {
        //System.out.println(state.position + "," + state.isParked);
    	/*
    	 * simply return the state of the car no checking is required
    	 * */
		return state;
    }






}


