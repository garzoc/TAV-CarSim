/**
 * Class containing the test cases
 */

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class ActionsTest {
    String message = "Hello World";
    Actions messa = new Actions(message);

    @Test
    public void justATest(){
        message = "New Word";
        assertEquals(message,messa.printMessage());
    }


    @Test
    public void MoveForward() {

    }

    @Test
    public void isEmpty(){

    }

    @Test
    public void MoveBackward(){

    }

    @Test
    public void Park(){

    }

    @Test
    public void UnPark(){

    }

    @Test
    public void WhereIs(){

    }

}
