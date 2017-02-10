import java.util.LinkedList;

/**
 * Created by laptop on 2017-02-06.
 */
public class Car  implements ParkingAssistant {
    private State state=new State();
    //private int[] parkingSpaces = new int[100];
    private int stretch = 0;
    

    
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
                if (stretch >= 5) {
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
        	state.position += 1;
        }
    }

    public State MoveForward() {
        if (state.isParked) {
            System.out.println("ERROR - PARKED, CANNOT MOVE");
        } else if(state.position<spaces.length-1 &&state.position >= 0){
        	state.position += 1;
            double[] data=isEmpty();
            int avg=(int) ((data[0]+data[1])/2);
            //System.out.println(avg);
            if(avg > 150) stretch++;
            	else stretch=0;
        }
        return state;
    }
    public State MoveBackward() {
        if (state.isParked) {
            System.out.println("ERROR - PARKED, CANNOT MOVE");
        } else if (state.position > 0 && state.position<spaces.length-1){
        	state.position -= 1;
        }else {
           System.out.println("ERROR - OUT OF BOUNDS, CANNOT MOVE");
        }
        return state;
    }

    public double[] isEmpty() {
    	double[][] sensorData= new double[2][5];
    	double tmp;
    	double max=1.3;
    	double min=0.85;
    	for(int i=0;i<2;i++){
    		for(int n=0;n<5;n++){
	    		sensorData[i][n]=(int) (spaces[state.position]* ((tmp=Math.random()*2)<min?min+((1-min)*tmp) : (tmp >max)? (max-(max-1)*(tmp-1)): tmp));
	    		//System.out.println("sensor "+i+" actual distance "+spaces[state.position]+" noised is "+sensorData[i][n]);
	    		//System.out.println("percantage is "+sensorData[i][n]/spaces[state.position]);
    		}
    		//System.out.println("max regulation "+(max-(max-1)*(tmp-1)));
    	}
    	
    	double fmax=1.3;
    	double fmin=0.75;
    	
    	double[] filteredData=new double[2];
    	
    	for(int i=0;i<2;i++){
    		int[] count=new int[5];
    		LinkedList<Double> list=new LinkedList<Double>();
    		list.add(sensorData[i][0]);
    		count[0]++;
    		for(int n=1;n<5;n++){
    			int x=0;
    			for(x=0;x<list.size();x++){
    				if(list.get(x)/sensorData[i][n]>fmin && fmax > list.get(x)/sensorData[i][n]){
    					list.set(x,(list.get(x)+sensorData[i][n]));
    					count[x]++;
    					break;
    				}
    			}
    			if(x==list.size()){
    				list.add(sensorData[i][n]);
    			}	
    		}
    		int maxnumberIndex=0;
    		for(int n=0;n<count.length;n++){
    			if(count[n] >count[maxnumberIndex]) maxnumberIndex=n;
    			//System.out.println(count[n]);
    		}
    		//System.out.println("original value "+spaces[state.position]+" noised reduced "+list.get(maxnumberIndex)/count[maxnumberIndex]);
    		filteredData[i]=list.get(maxnumberIndex)/count[maxnumberIndex];
    	}
      
		return filteredData;
    }

    public State WhereIs() {
        //System.out.println(state.position + "," + state.isParked);
		return state;
    }






}


