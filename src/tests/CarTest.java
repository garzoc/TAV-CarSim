
/**
 * Class containing the test cases
 */
import static org.junit.Assert.*;
/*import org.mockito.*;
import static org.mockito.Mockito.when;   // ...or...
import static org.mockito.Mockito.*;*/

import org.junit.*;


public class CarTest {

	
    @Test
    public void MoveForwardOnce() {
    	Car pa = new Car(0,false);
    	Parkable.State state=pa.MoveForward();
    	assertEquals(1,state.position);
    }

    @Test
    public void MoveForwardOutOfBounds() {
    	Car pa = new Car(499,false);
    	Parkable.State state=pa.MoveForward();
    	assertEquals(499,state.position);
    }
    
   
    /*@Test
    public void MoveForwardFromOutOfBounds() {
    	Car pa = new Car(-1,false);
    	Parkable.State state=pa.MoveForward();
    	assertEquals(-1,state.position);
    }*/
    
    

    @Test
    public void MoveForwardWhileParked() {
    	Car pa = new Car(50,true);
    	Parkable.State state=pa.MoveForward();
    	assertEquals(50,state.position);
    }
    
    
   
    @Test
    public void isEmptySensor1(){
    	
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
    
  
    @Test
    public void isEmptySensor2(){
    	
    	int []spaces=new int[10];
    	for (int i =0; i<10;i++) 
            spaces[i] = 100;
        
    	
    	Car pa = new Car(2,false);
    	pa.generateMap(spaces);
    	
    	double[] test=pa.isEmpty();
    	assertEquals(100.0,test[1],15);

    }
    
    
   
    @Test
    public void readSensorWhileParked(){
    	
    	Car pa = new Car(2,true);
    	
    	//double[] test=pa.isEmpty();
    	//System.out.println("1 ewonfioewnfoiwenfieowfnwenfiwnweonf is");
    	//assertTrue(test[0]==0 && test[1]==0);
    }
    
 
    
    
   
    @Test
    public void MoveBackward(){
    
    	Car pa = new Car(100,false);
    	pa.generateMap(new int[0]);
    	Parkable.State state=pa.MoveBackward();
    	assertEquals(99,state.position);
    }
    
  
    @Test
    public void MoveBackwardOutOfBounds(){
    	Car pa = new Car(0,false);
    	pa.generateMap(new int[0]);
    	Parkable.State state=pa.MoveBackward();
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
    	Car pa = new Car(50,true);
    	Parkable.State state=pa.MoveBackward();
    	assertEquals(50,state.position);
    }

  
    
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
    

    @Test
    public void allSensorsDiabledPark(){
    	
    	
    	int []spaces=new int[16];
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
    
 
    @Test
    public void Park(){
    	
    	int []spaces={100,100,100,100,100,178,100,100,178,178,178,178,100,178,178,178,178,178};
    
    	Car pa = new Car(0,false);
    	pa.generateMap(spaces);
    	pa.Park();
    	assertTrue(pa.WhereIs().isParked==true && pa.WhereIs().position==17);
    }
   
    
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
    
    
    
  
    @Test
    public void ParkWhileParked(){
    	Car pa = new Car(58,true);
    	assertTrue(58==pa.WhereIs().position && pa.WhereIs().isParked);
    }
    
    
    


    @Test
    public void UnPark(){
    	Car pa = new Car(0,true);
    	pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 1);
    	
    }
    
    
 
    @Test
    public void UnParkWhileNotParked(){
    	Car pa = new Car(0,false);
    	//pa.UnPark();
    	assertTrue(!pa.WhereIs().isParked && pa.WhereIs().position == 0);
    	
    }
    
   
    
   

    @Test
    public void WhereIs(){
    	Car pa = new Car(0,false);
   
    	Parkable.State test=pa.WhereIs();
    	//assertEquals();
    	assertTrue(0==test.position&&test.isParked==false);
    	//assertEquals(true,test.isParked);
    	
    	
    }

}