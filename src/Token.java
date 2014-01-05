import java.util.*;


public class Token {
	private String tokenText;
	private TokenType tokenType;
	private Token subToken;
	private Transformation transformation;

	public Token(TokenType type, Transformation transformation) {
		this.tokenType = type;
		this.setTransformation(transformation);
	}

	public String getTokenText() {
		return tokenText;
	}

	public void setTokenText(String tokenText) {
		
		tokenText = tokenText.trim();
		
		if (transformation.isGoBack()) {
			tokenText = tokenText.substring(0, tokenText.length()-1);
		}
		
		
		if (subToken != null) {
			this.tokenText = tokenText.substring(0, tokenText.length()-1);
			subToken.setTokenText(tokenText.substring(tokenText.length()-1));
		} else {
			this.tokenText = tokenText;
		}		
	}
	
	public String toString() {
		return tokenText +  " (" + tokenType.toString() + " in "+ transformation.getFrom() + "->"+ transformation.getTo() + ")";
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	public Token getSubToken() {
		return subToken;
	}

	public void setSubToken(Token subToken) {
		this.subToken = subToken;
	}
	
	public List<Token> getAll() {
		List<Token> list = new ArrayList<Token>();
		list.add(this);
		if (subToken!=null) list.addAll(subToken.getAll());
		return list;
	}

	public Transformation getTransformation() {
		return transformation;
	}

	public void setTransformation(Transformation transformation) {
		this.transformation = transformation;
	}
}
