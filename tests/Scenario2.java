

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
public class Scenario2 { //rename to Scenario1, mock classes are actually test implementations of interfaces to be mocked

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
                lines[i]=Double.parseDouble(line);
                //lines.add(Double.parseDouble(line));
                i++;
            }
            bufferReader.close();
            //return lines.toArray(new Double[lines.size()]);
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
     * Scenario no 2 :
     * Start parked at a random position on the road
     * Unparks
     * Tries to park at the first available space (streak of 5 or more)
     * It might not find an empty space, at which case it returns to the start
     * When it moves 20 steps, one sensor breaks.
     * */
    Sensor sensors[];
    Actuator actuator;
    Car pa;
    @Before
    public void create(){
        Sensor sensors[]=new Sensor[2];
        sensors[0]=mock(Sensor.class);
        sensors[1]=mock(Sensor.class);
        this.sensors=sensors;
       
        actuator=mock(Actuator.class);
        double rand=Math.ceil(Math.random()*470);
        int randInt=(int)rand;
        pa=new Car(randInt,true,sensors,actuator);
        
        pa.generateMap(null);
        double[] map=read("map/MAP.txt");
        
        when(actuator.moveF(new State(anyInt(),false))).thenReturn(1);
        when(actuator.reverse(pa.WhereIs())).thenReturn(-1);
        
        for(int i=0;i<sensors.length;i++)
            for(int n=0;n<500;n++)
                when(sensors[i].read(pa.spaces, n)).thenReturn(map[n]);



        for(int i=pa.WhereIs().position+5;i<pa.WhereIs().position+25;i++)
            when(sensors[0].read(pa.spaces,i)).thenReturn(0.0);
        
      
      
        //double p=sensors[0].read(pa.spaces,249);
       // System.out.println("test "+p);

    }

    @Test
    public void test(){
    	
    	//System.out.println("initail pos "+pa.WhereIs().position);
        
        pa.UnPark();
        pa.MoveForward();
        while(pa.WhereIs().streak!=0)
        	pa.MoveForward();
        
        
       pa.Park();
       boolean state=(pa.WhereIs().position==499 && !pa.WhereIs().isParked)||( (pa.WhereIs().position==251 || pa.WhereIs().position==465) && pa.WhereIs().isParked);
       //System.out.println(pa.WhereIs().position+" "+pa.WhereIs().isParked);
       assertTrue(state);



        //System.out.println("pos is "+pa.WhereIs().position);


        //assertSame(10,obj.get());

        //verify(sensors[1],times(5)).read(pa.spaces,1);
    }
}
