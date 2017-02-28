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
import src.*;

public class Scenario1 {
	
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
				lines[i]=(int)Double.parseDouble(line);
				i++;
			}
			bufferReader.close();
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
     * This scenario is take from the TAV Assignment Phase 2
     * 1. Starts at the beginning of the street,  
     * 2. Moves along the street to the end of the road and scan the available parking places, 
     * 3. Moves backwards until the most efficient parking place (the smallest available parking where it can still park safely), 
     * 4. Parks the car, 
     * 5. Unparks the car and drive to the end of the street. 

     * */
	Sensor sensors[];
	Actuator actuator;
	Car pa;
	@Before
	public void create(){
		/*
		 * Mock to sensors from the sensor interface
		 * */
		Sensor sensors[]=new Sensor[2];
		sensors[0]=mock(Sensor.class);
		sensors[1]=mock(Sensor.class);
		/*
		 * Mock the actuator interface
		 * */
		actuator=mock(Actuator.class);
		/*
		 * Initiate car to position zero and isParked to false
		 * */
		pa=new Car(0,false,sensors,actuator);
		
		this.sensors=sensors;
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
		 * Stub the moveF method so that it return 1 for whenever the car is positioned at zero
		 * */
		when(actuator.reverse(new State(0,false))).thenReturn(0);
		
		/*
		 * Stub the read method from sensors so that it return the value from the map that was read from the "MAP" text file
		 * */
		for(int i=0;i<sensors.length;i++)
			for(int n=0;n<500;n++)
				when(sensors[i].read(pa.spaces, n)).thenReturn(map[n]);
		
	
	}
	
	@Test
	public void test(){
		 boolean test=true;
		//pa.generateMap(null);
		/*
		 * Move the car to the end of he street and at the same time scan for available parking spaces
		 * */
		for(int i=0;i<499;i++){
			pa.MoveForward();
		}
		/*
		 * verify position
		 * */
		test=test && pa.WhereIs().position==499;
		/*
		 * find the most efficient parking space
		 * (the smallest possible  space where the car can park)
		 * */
		FreeSpace optimal=pa.freeSpaces.getFirst();
		/*
		 * for every space in freeSpaces (a list of spaces where the car can park ) check if 
		 * the size of the optimal space is greater the the size of the space in the list then
		 * set the optimal space to the space in the list
		 * */
		for(int i=1;i<pa.freeSpaces.size();i++){
			if(pa.freeSpaces.get(i).size<optimal.size){
				optimal=pa.freeSpaces.get(i);
			}
		}
		
		
		
		/*
		 * Move the car back until the beginning of the most efficient space is reached
		 * */
		for(int i=0;i<499-optimal.position;i++){
			pa.MoveBackward();
		}
		
		/*
		 * ones reached park the car
		 * */
		pa.Park();
		
		/*
		 * test if car parked successfully
		 * */
		test=test && pa.WhereIs().isParked && pa.WhereIs().position==optimal.position+5;
		/*
		 * then unpark the car
		 * */
		pa.UnPark();
		
		/*
		 * then move to the end of the road.
		 * */
		for(int i=pa.WhereIs().position;i<499;i++){
			pa.MoveForward();
		}
		
		/*
		 * test if car moved to the end of the road
		 * */
		test=test && pa.WhereIs().position==499;
		/*
		 * verify test
		 * */
		assertTrue(test);
	}
}
