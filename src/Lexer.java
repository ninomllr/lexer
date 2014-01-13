import java.io.*;
import java.util.*;


public class Lexer {

	private static List<Token> list;
	private static String buffer;

	public static void main(String[] args) throws Exception {
		
		String file = args[0];
		list = new ArrayList<Token>();
		buffer = "";
		
		Automate automate = new Automate();
		
		
		// add transformations
		automate.addTransformation(new Transformation(States.S0, States.S2, "[%]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S2, "/", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S0, "[_]", TokenType.Variable, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S3, "[A-Z]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S1, "[a-z]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S0, "[\\[\\]]", TokenType.Liste, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S4, "[\\.\\!:-]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S0, "[ \t]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S0, States.S0, "[,|)]", TokenType.Zeichen, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S5, "[']", TokenType.Zeichen, null, false, false));
		
		automate.addTransformation(new Transformation(States.S1, States.S1, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[(]", TokenType.Struktur, TokenType.Zeichen, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[,|)]", TokenType.Konstante, TokenType.Zeichen, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[\\]]", TokenType.Konstante, TokenType.Liste, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S4, "[\\.\\!\t:-]", null, null, false, true));

		automate.addTransformation(new Transformation(States.S2, States.S2, ".*", null, null, false, false));
		
		automate.addTransformation(new Transformation(States.S3, States.S3, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S3, States.S0, "[ ]", TokenType.Variable, null, false, false));
		automate.addTransformation(new Transformation(States.S3, States.S0, "[,|)]", TokenType.Variable, TokenType.Zeichen, false, false));
		automate.addTransformation(new Transformation(States.S3, States.S0, "[\\]]", TokenType.Variable,  TokenType.Liste, false, false));
		
		automate.addTransformation(new Transformation(States.S4, States.S4, "[\\.\\!\t:-]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[,|]", TokenType.Konstante, TokenType.Zeichen, true, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[\\]\\[]", TokenType.Konstante, TokenType.Liste, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[a-zA-Z0-9]", TokenType.Konstante, null, false, true));
		
		automate.addTransformation(new Transformation(States.S5, States.S5, "[a-zA-Z0-9 ]", null, null, false, true));
		automate.addTransformation(new Transformation(States.S5, States.S0, "[']", TokenType.String, TokenType.Zeichen, false, false));
		
		
		BufferedReader br = new BufferedReader(new FileReader(file ));
		String line;
		while ((line = br.readLine()) != null) {
			
			automate.reset();
			buffer="";
			
			char[] charArray = line.toCharArray();
			
			for (int i = 0; i < charArray.length; i++) {
				String c = ""+charArray[i];
				
				buffer+=c;
				
				Token t = automate.feed(c, (charArray.length-1 == i));
				if (t!=null) {
					t.setTokenText(buffer);
					list.addAll(t.getAll());
					buffer="";
					if (automate.isGoBack())
						buffer=c;
				}
			}
			
		}
		br.close();
		
		for (int i = 0; i < list.size(); i++)
			System.out.println(i+": " + list.get(i).toString());
	}
	
}
