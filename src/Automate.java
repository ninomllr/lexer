
public class Automate {
	
	private States state;
	private State[] states;
	private boolean goBack;

	public Automate() {
	
		States[] cache = States.values();
		states = new State[cache.length];
		
		for (int i = 0; i < cache.length; i++) {
			states[i] = new State(cache[i], this);
		}
		
		reset();
	}
	
	public void reset() {
		// init state
		state = States.S0;
		goBack = false;
	}

	public Token feed(String c, boolean endOfLine) throws Exception {
		return states[state.getValue()].transform(c, endOfLine);
	}
	
	public void addTransformation(Transformation transformation) {
		states[transformation.getFrom().getValue()].addTransformation(transformation);
	}
	
	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}
	
	public State[] getStates() {
		return states;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

	public boolean isGoBack() {
		return goBack;
	}

	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}
	
}
