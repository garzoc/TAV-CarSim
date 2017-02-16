

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
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

		pa.generateMap(null);

		int pos = 0;
		int i = 0;

		// Five occupied spaces
		for(i = pos; pos < i + 5; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(20d);

		// Four empty spaces
		for(i = pos; pos < i + 4; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(180d);

		// Five occupied
		for(i = pos; pos < i + 5; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(20d);

		// Five empty
		for(i = pos; pos < i + 5; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(180d);

		// Two occupied
		for(i = pos; pos < i + 2; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(20d);

		// Six empty
		for(i = pos; pos < i + 6; pos++)
			when(sensors[0].read(pa.spaces, i)).thenReturn(180d);
		
	}
	
	@Test
	public void test(){

		pa.MoveForward();

		verify(sensors[1],times(5)).read(pa.spaces,1);

	}
}
