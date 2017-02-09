
/**
 * Created by laptop on 2017-02-06.
 */
public class Car  implements ParkingAssistant {
    private State state=new State();
    private int[] parkingSpaces = new int[100];
    private int stretch = 0;
    


    public  Car(int position, boolean isParked) {
    	state.position = position;
    	state.isParked = isParked;
    }

    public void Park() {

            while ( !state.isParked && state.position < 500) {
                MoveForward();
                if (stretch == 5) {
                	state.isParked = true;
                	state.position -= 1;
                }
            }
    }

    public void UnPark() {
        if (state.isParked) {
        	state.isParked = false;
        	state.position += 1;
        }
    }

    public void MoveForward() {
        if (state.isParked) {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");
        } else {
        	state.position += 1;
            isEmpty();
        }
    }
    public void MoveBackward() {
        if (state.isParked) {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");
        } else if (state.position > 0)
             {
        	state.position -= 1;
            }
            else {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");

        }
    }

    public boolean isEmpty() {
        parkingSpaces[state.position] = getDistance();
        if (parkingSpaces[state.position] >= 100 && parkingSpaces[state.position] <= 200) {
            stretch += 1;
        }
        else {
            stretch = 0;
        }
		return state.isParked;
    }

    public ParkingAssistant.State WhereIs() {
        System.out.println(state.position + "," + state.isParked);
		return state;
    }



    private int getDistance() {
        int i = 0;

        while (i <= 5) {
            //this.position;

            i++;
        }
        return state.position;
    }


}


