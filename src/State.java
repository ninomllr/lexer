import java.util.*;


public class State {
	private States state;
	private List<Transformation> transformations;
	private Automate automate;
	
	public State(States state, Automate automate) {
		this.state = state;
		this.transformations = new ArrayList<Transformation>();
		this.automate = automate;
	}

	public void addTransformation(Transformation transformation) {
		transformations.add(transformation);		
	}

	public Token transform(String c, boolean endOfLine) throws Exception {
		for (int i = 0; i<transformations.size();i++) {
			Transformation t = transformations.get(i);
			if (c.matches(t.getRegex())) {
				automate.setState(t.getTo());
				automate.setGoBack(t.isGoBack());
				Token token=null;
				if (t.getType() != null) {
					
					if (!t.isOnlyOnEndOfLine() || endOfLine) {
						 token = new Token(t.getType(),t);
						 
						 // generate subtoken if needed
						 if (t.getLastSymbol()!=null)
							 token.setSubToken(new Token(t.getLastSymbol(),t));
					}

				}
				return token;
			}
		}
		
		throw new Exception(state.toString() +  ": Could not match pattern: " + c);
	}
}
