public class ParkAssistant {
    private int position;
    private boolean isParked;
    private int[] parkingSpaces = new int[100];
    private int stretch = 0;

    public ParkAssistant(int position, boolean isParked) {
        this.position = position;
        this.isParked = isParked;
    }

    public void Park() {

            while ( !isParked && position < 500) {
                MoveForward();
                if (stretch == 5) {
                    isParked = true;
                    position -= 1;
                }
            }
    }

    public void unPark() {
        if (isParked) {
            isParked = false;
            position += 1;
        }
    }

    public void MoveForward() {
        if (isParked) {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");
        } else {
            position += 1;
            isEmpty();
        }
    }
    public void MoveBackward() {
        if (isParked) {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");
        } else if (position > 0)
             {
                position -= 1;
            }
            else {
            System.out.print("ERROR - PARKED, CANNOT MOVE OR OUT OF BOUNDS");

        }
    }

    public void isEmpty() {
        parkingSpaces[position] = getDistance();
        if (parkingSpaces[position] >= 100 && parkingSpaces[position] <= 200) {
            stretch += 1;
        }
        else {
            stretch = 0;
        }
    }

    public void whereIs() {
        System.out.println(position + "," + isParked);
    }



    private int getDistance() {
        int i = 0;

        while (i <= 5) {
            //this.position;

            i++;
        }
        return position;
    }

    private void Noise() {}
    private void Average() {};

}


