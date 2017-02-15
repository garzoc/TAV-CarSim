

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
public class MockClass {
	
    /*
     * Test to see if the car can move forward in a normal situation
     * */
	Sensor sensors[];
	Car pa;
	@Before
	public void create(){
		Sensor sensors[]=new Sensor[2];
		sensors[0]=mock(Sensor.class);
		sensors[1]=mock(Sensor.class);
		pa=new Car(0,false,sensors);
		this.sensors=sensors;
		for(int i=0;i<pa.spaces.length;i++)
			when(sensors[0].read(pa.spaces, 1)).thenReturn((double)pa.spaces[i]);
		
	}
	
	@Test
	public void test(){
		
		pa.generateMap(null);
		pa.MoveForward();
		//assertSame(10,obj.get());
		//obj.get();
		verify(sensors[1],times(5)).read(pa.spaces,1);
	}
}
