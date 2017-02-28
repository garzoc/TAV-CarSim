package tests;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import src.Actuator;
import src.Car;
import src.Sensor;
import src.State;

public class Scenario2 { //rename to Scenario1, mock classes are actually test implementations of interfaces to be mocked

    /*
    reading the external file as our map
     */
    public double[]read(String filename){
        try {
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferReader =new BufferedReader(fileReader);
            double[] lines=new double[500];
            //List<Double> lines =new ArrayList<Double>();
            String line=null;
            int i=0;
            while((line=bufferReader.readLine())!=null&&i<500){
                lines[i]=Double.parseDouble(line);
                //lines.add(Double.parseDouble(line));
                i++;
            }
            bufferReader.close();
            //return lines.toArray(new Double[lines.size()]);
            return lines;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /*
     * Scenario no 2 :
     * Start parked at a random position on the road
     * Unparks
     * Tries to park at the first available space (streak of 5 or more)
     * It might not find an empty space, at which case it returns to the start
     * When it moves 20 steps, one sensor breaks.
     * */
    
    
    Sensor sensors[];
    Actuator actuator;
    Car pa;
    @Before
    public void create(){
    	/*
         * Test to see if the car can move forward in a normal situation
         * */
        Sensor sensors[]=new Sensor[2];
        sensors[0]=mock(Sensor.class);
        sensors[1]=mock(Sensor.class);
        this.sensors=sensors;
        /*
		 * Mock to sensors from the sensor interface
		 * */
        actuator=mock(Actuator.class);
        /*
         * generate a random position for the car to start on
         * */
        double rand=Math.ceil(Math.random()*470);
        
        int randInt=(int)rand;
        /*
		 * Initiate car to a random position and isParked to true
		 * */
        pa=new Car(randInt,true,sensors,actuator);
        /*
		 * Generate a random map this will be not be used since the sensors are stubbed
		 * */
        pa.generateMap(null);
        /*
		 * Read VALUES from MAP.txt and to an array of doubles
		 * */
        double[] map=read("src/map/MAP.txt");
        /*
		 * Stub the moveF method so that it return 1 for all integers
		 * */
        when(actuator.moveF(new State(anyInt(),false))).thenReturn(1);
        /*
		 * Stub the moveF method so that it return 1 for whenever the state of the car is passed in
		 * */
        when(actuator.reverse(pa.WhereIs())).thenReturn(-1);
        /*
		 * Stub the read method from sensors so that it return the value from the map that was read from the "MAP" text file
		 * */
        for(int i=0;i<sensors.length;i++)
            for(int n=0;n<500;n++)
                when(sensors[i].read(pa.spaces, n)).thenReturn(map[n]);


        
        /*
         * stub one of the sensors so that if fails one the car has moved forward 20 time by providing out of bound
         * values.
         * After the car has move forward an additional 5 times the sensor will be reactivated
         * */
        for(int i=pa.WhereIs().position+20;i<pa.WhereIs().position+25;i++)
            when(sensors[0].read(pa.spaces,i)).thenReturn(0.0);
        
      
    

    }

    @Test
    public void test(){
    	
    	/*
    	 * since the car is parked we unpark it
    	 * */
        pa.UnPark();
        /*
         * we then move the car forward ones
         * */
        pa.MoveForward();
        /*
         * continue to move the car forward until it reaches the end of the current parking space
         * */
        while(pa.WhereIs().streak!=0)
        	pa.MoveForward();
        
        /*
         * then park the car in the next available space
         * */
       pa.Park();
       /*
        * the car should end up in one of three possible position 499, 251 ,465 
        * if car is at position 499 then the car is not parked
        * if it ended up in any of the other two position the the car should be parked
        * */
       boolean state=(pa.WhereIs().position==499 && !pa.WhereIs().isParked)||( (pa.WhereIs().position==251 || pa.WhereIs().position==465) && pa.WhereIs().isParked);
       /*
        * assert the test
        * */
       assertTrue(state);



    }
}
