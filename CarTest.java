
/**
 * Class containing the test cases
 */

import static org.junit.Assert.*;

import org.junit.*;

public class CarTest {

    /*
     * Test to see if the car can move forward in a normal situation
     * */
    @Test
    public void MoveForwardOnce() {
    	Car pa = new Car(0,false);
    	ParkingAssistant.State state=pa.MoveForward();
    	assertEquals(1,state.position);
    }
    
    /*
     * Test to see what happens if the cars try to go out of bounds
     * */
    @Test
    public void MoveForwardOutOfBounds() {
    	Car pa = new Car(499,false);
    	ParkingAssistant.State state=pa.MoveForward();
    	assertEquals(499,state.position);
    }
    
    /*
     * Test to see what happens if the cars starting position is out of bound and it tries to move
     * */
    @Test
    public void MoveForwardFromOutOfBounds() {
    	Car pa = new Car(-1,false);
    	ParkingAssistant.State state=pa.MoveForward();
    	assertEquals(-1,state.position);
    }
    
    
    /*
     *Test to see what happen when the car tries to move forward while it is parked
     * */
    @Test
    public void MoveForwardWhileParked() {
    	Car pa = new Car(50,true);
    	ParkingAssistant.State state=pa.MoveForward();
    	assertEquals(50,state.position);
    }
    
    
    /*
     * Test to see how well the noise filtering works 
     * The maximum offset is 15
     * */
    @Test
    public void isEmptySensor1(){
    	/*
    	 * Create predefined obsatcles at distance 100
    	 * to the test how well we can filter out the noise on sensor 1
    	 * */
    	int []spaces=new int[10];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
        
    	
    	Car pa = new Car(2,false);
    	pa.generateMap(spaces);
    	double[] test=pa.isEmpty();
    	//System.out.println(test[0]);
    	//System.out.println(test[1]);
    	assertEquals(100.0,test[0],15);
    }
    
    /*
     * Test to see how well the noise filtering works 
     * The maximum offset is 15
     * */
    @Test
    public void isEmptySensor2(){
    	/*
    	 * Create predefined obsatcles at distance 100
    	 * to the test how well we can filter out the noise on sensor 2  
    	 * */
    	int []spaces=new int[10];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
        
    	
    	Car pa = new Car(2,false);
    	pa.generateMap(spaces);
    	
    	double[] test=pa.isEmpty();
    	assertEquals(100.0,test[1],15);

    }
    
    
    /*
     * test to see if the sensor will read a valid input while parked
     * */
    @Test
    public void readSensorWhileParked(){
    	
    	Car pa = new Car(2,true);
    	
    	double[] test=pa.isEmpty();
    	assertTrue(test[0]==0 && test[1]==0);
    }
    
 
    
    
    /*
     * Test to see if the car can move forward in a normal situation
     * */
    @Test
    public void MoveBackward(){
    
    	Car pa = new Car(100,false);
    	pa.generateMap(new int[0]);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(99,state.position);
    }
    
    
    /*
     * Test to see what happens when the car back out of bounds
     * */
    @Test
    public void MoveBackwardOutOfBounds(){
    	Car pa = new Car(0,false);
    	pa.generateMap(new int[0]);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(0,state.position);
    }
    /*
     * Test to see what happens when is out of bounds and tries to back
     * */
    @Test
    public void MoveBackwardFromOutOfBounds(){
    	Car pa = new Car(500,false);
    	pa.generateMap(new int[0]);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(500,state.position);
    }
    
    
    /*
     *Test to see what happen when the car tries to move forward while it is parked
     * */
    @Test
    public void MoveBackwardWhileParked() {
    	Car pa = new Car(50,true);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(50,state.position);
    }

    /*
     * Test to see what happens if one the sensor get's disabled when the car tries to park
     * */
    
    @Test
    public void oneSensorDisabledPark(){
    	
    	int []spaces=new int[15];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
    	for (int i =10; i<15;i++) 
            spaces[i] = 178;
    	
    	Car pa = new Car(2,false);   	
    	pa.disableSensor(-1, 13);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(pa.WhereIs().isParked && pa.WhereIs().position==14);


    }
    
    /*
     * Test to see what happens when the car tries to park and all sensors are disabled
     * */
    @Test
    public void allSensorsDiabledPark(){
    	
    	
    	int []spaces=new int[15];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
    	for (int i =10; i<15;i++) 
            spaces[i] = 178;
    	Car pa = new Car(2,false);   	
    	pa.disableSensor(14, 14);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(!(pa.WhereIs().isParked && pa.WhereIs().position==14));
   

    }
    
    /*
     * Test to see if the car can park
     * */
    @Test
    public void Park(){
    	
    	int []spaces={100,100,100,100,100,178,100,100,178,178,178,178,100,178,178,178,178,178};
    
    	Car pa = new Car(0,false);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(pa.WhereIs().isParked==true && pa.WhereIs().position==17);
    }
    /*
     * Test to see if the car parks in the first space it sees
     * */
    @Test
    public void ParkInFirstSpace(){
    	int []spaces=new int[15];
    	for (int i =0; i<5;i++) spaces[i] = 100;
    	for (int i =5; i<10;i++) spaces[i] = 178;
        
    	Car pa = new Car(0,false);
    	pa.generateMap(spaces);
    	
    	pa.Park();
    	assertTrue(9 == pa.WhereIs().position && pa.WhereIs().isParked);
    }
    
    
    
    /*
     * Test to see what happens when the car park while parked
     * */
    @Test
    public void ParkWhileParked(){
    	Car pa = new Car(58,true);
    	assertTrue(58==pa.WhereIs().position && pa.WhereIs().isParked);
    }
    
    
    

    /*
     * Test to see if we can unpark
     * */
    @Test
    public void UnPark(){
    	Car pa = new Car(0,true);
    	pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 1);
    	
    }
    
    
    /*
     * Test to see what happens when thre car unpark while not parked
     * */
    @Test
    public void UnParkWhileNotParked(){
    	Car pa = new Car(0,false);
    	//pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 0);
    	
    }
    
   
    
   

    /*
     * test to see if we can use where is
     * */
    @Test
    public void WhereIs(){
    	Car pa = new Car(0,false);
   
    	ParkingAssistant.State test=pa.WhereIs();
    	//assertEquals();
    	assertTrue(0==test.position&&test.isParked==false);
    	//assertEquals(true,test.isParked);
    	
    	
    }

}
