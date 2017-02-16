

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        actuator=mock(Actuator.class);
        pa=new Car((int)Math.random()*500,true,sensors,actuator);
        this.sensors=sensors;
        pa.generateMap(null);
        double[] map=read("map/MAP.txt");
        when(actuator.moveF(new Parkable.State(anyInt(),false))).thenReturn(1);
        when(actuator.reverse(pa.WhereIs())).thenReturn(-1);
        for(int i=0;i<sensors.length;i++)
            for(int n=0;n<500;n++)
                when(sensors[i].read(pa.spaces, n)).thenReturn(map[n]);



        for(int i=250;i<pa.spaces.length;i++)
            when(sensors[0].read(pa.spaces,i)).thenReturn(0.0);

    }

    @Test
    public void test(){

        int mileage = 0; //once this reaches 20, a sensor should break
        pa.UnPark();
        int o = 500-pa.WhereIs().position
        pa.MoveForward(); //starts looking for places




        for(int i=0;i<500;i++){
            pa.MoveForward();
        }
        Parkable.FreeSpace optimal=pa.freeSpaces.getFirst();
        for(int i=1;i<pa.freeSpaces.size();i++){
            if(pa.freeSpaces.get(i).size<optimal.size){
                optimal=pa.freeSpaces.get(i);
            }
        }
        for(int i=0;i<500-optimal.position;i++){
            pa.MoveBackward();
        }

        pa.Park();

        pa.UnPark();

        for(int j=pa.WhereIs().position;i<500;i++){
            pa.MoveForward();
        }


        //System.out.println("pos is "+pa.WhereIs().position);


        //assertSame(10,obj.get());

        //verify(sensors[1],times(5)).read(pa.spaces,1);
    }
}
