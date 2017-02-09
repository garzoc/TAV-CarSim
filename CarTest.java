
/**
 * Class containing the test cases
 */

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class CarTest {
    String message = "Hello World";
   // Actions messa = new Actions();

   /* @Test
    public void justATest(){
        message = "New Word";
        assertEquals("","");
    }*/


    @Test
    public void MoveForward() {

    }

    @Test
    public void isEmpty(){
    	Car pa = new Car(0,false);
    	ParkingAssistant.State test=pa.WhereIs();
    	assertEquals(0,test.position);
    }

    @Test
    public void MoveBackward(){

    }

    @Test
    public void Park(){
    
    }

    @Test
    public void UnPark(){
    	Car pa = new Car(0,false);
    	
    }

    @Test
    public void WhereIs(){
    	Car pa = new Car(0,false);
    	ParkingAssistant.State test=pa.WhereIs();
    	assertEquals(0,test.position);
    	
    }

}