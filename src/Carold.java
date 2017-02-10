
/**
 * Created by laptop on 2017-02-06.

public class Carold  implements ParkingAssistant {
    private State state=new State();
    private int[] parkingSpaces = new int[100];
    private int stretch = 0;
    

    
    private int[] spaces = new int[500];

    public void generateMap(int[] spaces) {
        for (int i =0; i<500;i++) {
            this.spaces[i] = (int) (Math.random()*200);
          //  System.out.println(spaces[i]);
        }
    }
    
    
    public  main.Car(int position, boolean isParked) {
    	state.position = position;
    	state.isParked = isParked;
    	generateMap(spaces);
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

    public int isEmpty() {
        parkingSpaces[state.position] = getDistance();
        if (parkingSpaces[state.position] >= 100 && parkingSpaces[state.position] <= 200) {
            stretch += 1;
        }
        else {
            stretch = 0;
        }
		return state.position;
    }

    public State WhereIs() {
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


*/