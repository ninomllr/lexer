public class Transformation {
	private TokenType type;
	private TokenType lastSymbol;
	private States from;
	private States to;
	private String regex;
	private boolean onlyOnEndOfLine;
	private boolean goBack;
	
	public Transformation(States from, States to, String regex, TokenType type, TokenType lastSymbol, boolean onlyOnEndOfLine, boolean goBack) {
		this.setFrom(from);
		this.setTo(to);
		this.setRegex(regex);
		this.setType(type);
		this.setLastSymbol(lastSymbol);
		this.setOnlyOnEndOfLine(onlyOnEndOfLine);
		this.setGoBack(goBack);
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public States getFrom() {
		return from;
	}

	public void setFrom(States from) {
		this.from = from;
	}

	public States getTo() {
		return to;
	}

	public void setTo(States to) {
		this.to = to;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public boolean isOnlyOnEndOfLine() {
		return onlyOnEndOfLine;
	}

	public void setOnlyOnEndOfLine(boolean endOfLine) {
		this.onlyOnEndOfLine = endOfLine;
	}

	public TokenType getLastSymbol() {
		return lastSymbol;
	}

	public void setLastSymbol(TokenType lastSymbol) {
		this.lastSymbol = lastSymbol;
	}

	public boolean isGoBack() {
		return goBack;
	}

	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}
}
