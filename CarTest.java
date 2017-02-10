
/**
 * Class containing the test cases
 */

import static org.junit.Assert.*;

import org.junit.*;

public class CarTest {
    String message = "Hello World";
   // Actions messa = new Actions();

   /* @Test
    public void justATest(){
        message = "New Word";
        assertEquals("","");
    }*/


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
    
    
    /**/
    @Test
    public void isEmpty(){
    	Car pa = new Car(2,false);
    	double[] test=pa.isEmpty();
    	assertEquals(100.0,test[0],20);
    	//assertEquals(0,test.position);
    }

    @Test
    public void MoveBackward(){
    	Car pa = new Car(100,false);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(99,state.position);
    }
    
    @Test
    public void MoveBackwardOutOfBounds(){
    	Car pa = new Car(0,false);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(0,state.position);
    }
    
    @Test
    public void MoveBackwardFromOutOfBounds(){
    	Car pa = new Car(500,false);
    	ParkingAssistant.State state=pa.MoveBackward();
    	assertEquals(500,state.position);
    }

    @Test
    public void Park(){
    	Car pa = new Car(0,false);
    	pa.Park();
    	assertEquals(pa.WhereIs().isParked,true);
    }
    
    @Test
    public void ParkInFirstSpace(){
    	Car pa = new Car(0,false);
    	pa.Park();
    	assertEquals(8,pa.WhereIs().position);
    }

    @Test
    public void UnPark(){
    	Car pa = new Car(0,true);
    	pa.UnPark();
    	assertEquals(pa.WhereIs().isParked,false);
    	
    }

    @Test
    public void WhereIs(){
    	Car pa = new Car(0,false);
   
    	ParkingAssistant.State test=pa.WhereIs();
    	//assertEquals();
    	assertTrue(0==test.position&&test.isParked==false);
    	//assertEquals(true,test.isParked);
    	
    	
    }

}