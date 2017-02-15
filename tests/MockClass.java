

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
public class MockClass {
	Sensor hej;
	Sensor obj;
    /*
     * Test to see if the car can move forward in a normal situation
     * */
	
	@Before
	public void create(){
		obj=mock(Sensor.class);
	
		//when(obj.read()).thenReturn(10.0);
		
	}
	
	@Test
	public void test(){
		
		Car pa=new Car(0,false,obj,obj);
		pa.MoveForward();
		//assertSame(10,obj.get());
		//obj.get();
		verify(obj,times(5)).read(pa.spaces,1);
	}
}
