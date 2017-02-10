package main;

public class Main {


    public static void main(String[] args) {
        int[] spaces = new int[500];
        generateMap(spaces);
        CarState carState = new CarState(1,false,0);
	    Car car = new Car(carState);

    }

    public static void generateMap(int[] spaces) {
        for (int i =0; i<500;i++) {
            spaces[i] = (int) (Math.random()*200);
            System.out.println(spaces[i]);
        }
    }

}
