package tests;
/**
 * Class containing the test cases
 */
import static org.junit.Assert.*;
/*import org.mockito.*;
import static org.mockito.Mockito.when;   // ...or...
import static org.mockito.Mockito.*;*/

import org.junit.*;
import src.*;


public class CarUnitTests {

	
    @Test
    public void MoveForwardOnce() {
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
//    	State state=pa.MoveForward();
//    	assertEquals(1,state.position);
    }

    @Test
    public void MoveForwardOutOfBounds() {
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(499,false,sensors,new CarEngine());
    	State state=pa.MoveForward();
		assertEquals(499,state.position);
    }


    @Test
    public void MoveForwardWhileParked() {
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(50,true,sensors,new CarEngine());
    	State state=pa.MoveForward();
    	assertEquals(50,state.position);
    }
    
    
   
    @Test
    public void isEmptySensor1(){
    	
    	double []spaces=new double[10];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
        
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(2,false,sensors,new CarEngine());
    	pa.generateMap(spaces);
    	double[] test=pa.isEmpty();
    	//System.out.println(test[0]);
    	//System.out.println(test[1]);
    	//System.out.println("sensor "+test[0]);
    	assertEquals(100.0,test[0],15);
    }
    
  
    @Test
    public void isEmptySensor2(){
    	
    	double []spaces=new double[10];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
        
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(2,false,sensors,new CarEngine());
    	pa.generateMap(spaces);
    	
    	double[] test=pa.isEmpty();
    	assertEquals(100.0,test[1],15);

    }
    
    
   
    @Test
    public void readSensorWhileParked(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(2,true,sensors,new CarEngine());
    	
    	//double[] test=pa.isEmpty();
    	//System.out.println("1 ewonfioewnfoiwenfieowfnwenfiwnweonf is");
    	//assertTrue(test[0]==0 && test[1]==0);
    }
    
 
    
    
   
    @Test
    public void MoveBackward(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(100,false,sensors,new CarEngine());
    	pa.generateMap(new double[0]);
    	State state=pa.MoveBackward();
    	assertEquals(99,state.position);
    }
    
  
    @Test
    public void MoveBackwardOutOfBounds(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
    	pa.generateMap(new double[0]);
    	State state=pa.MoveBackward();
    	assertEquals(0,state.position);
    }
 
    /*@Test
    public void MoveBackwardFromOutOfBounds(){
    	Car pa = new Car(500,false);
    	pa.generateMap(new int[0]);
    	Parkable.State state=pa.MoveBackward();
    	assertEquals(500,state.position);
    }*/
    
    
   
    @Test
    public void MoveBackwardWhileParked() {
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(50,true,sensors,new CarEngine());
    	State state=pa.MoveBackward();
    	assertEquals(50,state.position);
    }

  
    
    @Test
    public void oneSensorDisabledPark(){
    	
    	double []spaces=new double[15];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
    	for (int i =10; i<15;i++) 
            spaces[i] = 178;
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(2,false,sensors,new CarEngine());   	
    	pa.disableSensor(-1, 13);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(pa.WhereIs().isParked && pa.WhereIs().position==14.0);


    }
    

    @Test
    public void allSensorsDiabledPark(){
    	
    	
    	double []spaces=new double[16];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
    	for (int i =10; i<15;i++) 
            spaces[i] = 178;
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(2,false,sensors,new CarEngine());   	
    	
    	pa.disableSensor(14, 14);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(!(pa.WhereIs().isParked && pa.WhereIs().position==14));
   

    }
    
 
    @Test
    public void Park(){
    	
    	double []spaces={100,100,100,100,100,178,100,100,178,178,178,178,100,178,178,178,178,178};
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(pa.WhereIs().isParked==true && pa.WhereIs().position==17);
    }
   
    
    @Test
    public void ParkInFirstSpace(){
    	double []spaces=new double[15];
    	for (int i =0; i<5;i++) spaces[i] = 100;
    	for (int i =5; i<10;i++) spaces[i] = 178;
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
    	pa.generateMap(spaces);
    	
    	pa.Park();
    	assertTrue(9 == pa.WhereIs().position && pa.WhereIs().isParked);
    }
    
    
    
  
    @Test
    public void ParkWhileParked(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(58,true,sensors,new CarEngine());
    	assertTrue(58==pa.WhereIs().position && pa.WhereIs().isParked);
    }
    
    
    


    @Test
    public void UnPark(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,true,sensors,new CarEngine());
    	pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 1);
    	
    }
    
    
 
    @Test
    public void UnParkWhileNotParked(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
    	//pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 0);
    	
    }
    
   
    
   

    @Test
    public void WhereIs(){
    	Sensor[] sensors={new Ultrasonic(),new Ultrasonic()};
    	Car pa = new Car(0,false,sensors,new CarEngine());
   
    	State test=pa.WhereIs();
    	//assertEquals();
    	assertTrue(0==test.position&&test.isParked==false);
    	//assertEquals(true,test.isParked);
    	
    	
    }

}