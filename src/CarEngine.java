package src;

public class CarEngine implements Actuator{

	@Override
	public int moveF(State state) {
		if(state.position<499 &&state.position >= 0){
			return 1;
		}
		return 0;
		
	}

	@Override
	public int reverse(State state) {
		if (state.position > 0 && state.position<500){
			return -1;
			
		}
		return 0;
	}

}
