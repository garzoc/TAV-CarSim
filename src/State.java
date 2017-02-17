/*
 * Description:
 * This class contains the the current position of the cart
 * and whether it's is parked or not
 * as well as streak which is a counter of how many open spaces that are adjacent to each other.
 * */
public class State {
        public int position;
        public boolean isParked;
        public int streak;
        public State(){
            position=0;
            isParked=false;
            streak=0;
        }

        public State(int position,boolean parked){
            this.position=position;
            isParked=parked;
            streak=0;
        }
}
