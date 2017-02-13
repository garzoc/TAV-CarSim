package main;

import java.util.LinkedList;

/**
 * Created by laptop on 2017-02-06.
 */
public class CarParker  implements Parkable {

	/*
	 * Keeps the state of the car
	 * like whether it's parked or not it's position and how many open spaces
	 * it has found adjacent to eachother
	 * */
    private State state=new State();

    /*
     * Specifies at which position a certain sensor will fail
     * failPosition1 mapped to sensor 1
     * ailPosition2 mapped to sensor 2
     * */
    int failPosition1=-1;
    int failPosition2=-1;
    /*
     * This array represents the road each cell represents one meter
     * */
    private int[] spaces = new int[500];


    public void generateMap(int[] spaces) {

    	/*
    	 * Adds a predefined map to the first cells in spaces
    	 * So that we can defined a constant road when needed
    	 * */
    	for (int i =0; i<spaces.length;i++) {
            this.spaces[i] = spaces[i];
        }

    	/*
    	 * as for the rest create random map of the road
    	 * */

        for (int i =spaces.length; i<500;i++) {
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

    	//generateMap(spaces);
    }

    public void Park() {
    		/*
    		 * look for a parking space as long as the car is not parked
    		 * and the car is not out of bounds
    		 * */
            while ( !state.isParked && state.position < 499) {
            	//System.out.println(state.streak);
            	/*
            	 * for every iteration move the car forward one metre
            	 * */
                State state=MoveForward();
               /*
                * strtch keeps track of how many ajdecent openspaces there are adjacent to eacheother from the
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
             * checks if one or more sensors are disabled by testing if it returned an
             *  invalid number (0) then discard that sensor
             * */
            int count=0;
            int avg=0;
            for(int n=0;n<data.length;n++){
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


            /*
             * if the average is greater than 150 which means that there is a open space
             * increment streak by one
             * if not reset streak to zero
             * */
            if(avg > 150) state.streak++;
            	else state.streak=0;

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
    	state.streak=0;
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
        	/*when the car is out out of bounds print an error message but don't move it*/
        }else {
           System.out.println("ERROR - OUT OF BOUNDS, CANNOT MOVE");
        }
        /*
         * return the state of the car
         * */
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
     * read distance with noise and filter that noise*/
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
    	double max=1.15;
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
	    		/*
	    		 * When Car is at the specified position sensor will fail and output
	    		 * invalid values this can be done on both sensors
	    		 * */
	    		if(state.position==failPosition1&&i==0){
	    			sensorData[i][n]=300;
	    		}

	    		if(state.position==failPosition2&&i==1){
	    			sensorData[i][n]=300;
	    		}

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
    	for(int i=0;i<2&&!state.isParked;i++){
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
    				if(sensorData[i][n]<=0 || sensorData[i][n]>230)
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
