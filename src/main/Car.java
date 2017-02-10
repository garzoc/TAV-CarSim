package main;

public class Car implements Parkable {
    private int[] parkingSpaces = new int[100];
    public CarState carState;

   public Car(CarState cs) {
       carState.setParked(cs.getIsParked());
       carState.setPosition(cs.getPosition());
       carState.setStreak(cs.getStreak());
   }

    public void Park() {


    }
    public void UnPark() {

    }
    public void MoveForward() {

    }
    public void MoveBackward() {

    }
    public int isEmpty() {
        return 0;
    }
    public CarState WhereIs() {
        return this.carState;
    }



    private int getDistance() {
        return 0;
    }
    private void Noise() {}
    private void Average() {};

}


