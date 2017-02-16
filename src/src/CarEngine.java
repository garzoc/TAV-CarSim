
public class CarEngine implements Actuator{

	@Override
	public void moveF(Parkable.State state) {
		if(state.position<499 &&state.position >= 0){
			state.position++;
			
		}
		
	}

	@Override
	public void reverse(Parkable.State state) {
		if (state.position > 0 && state.position<499){
			state.position--;
			
		}
	}

}
