public class Main {


    public static void main(String[] args) {
        int[] spaces = new int[500];
        generateMap(spaces);
	    ParkAssistant pa = new ParkAssistant(0,false);

    }

    public static void generateMap(int[] spaces) {
        for (int i =0; i<500;i++) {
            spaces[i] = (int) (Math.random()*200);
            System.out.println(spaces[i]);
        }
    }
}
