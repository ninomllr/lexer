import java.io.*;
import java.util.*;


public class Lexer {

	private static String file = "/home/nino/file.pl";
	private static List<Token> list;
	private static String buffer;

	public static void main(String[] args) throws Exception {
		
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
		automate.addTransformation(new Transformation(States.S0, States.S4, "[\\.\\!\t:-]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S0, "[ ]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S0, States.S0, "[,|)]", TokenType.Zeichen, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S5, "[']", TokenType.Zeichen, null, false, false));
		
		automate.addTransformation(new Transformation(States.S1, States.S1, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[(]", TokenType.Struktur, TokenType.Zeichen, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[,|)]", TokenType.Konstante, TokenType.Zeichen, true, false));
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
		automate.addTransformation(new Transformation(States.S4, States.S0, "[\\]]", TokenType.Konstante, TokenType.Liste, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[a-zA-Z0-9]", TokenType.Konstante, null, false, true));
		
		automate.addTransformation(new Transformation(States.S5, States.S5, "[a-zA-Z0-9 ]", null, null, false, true));
		automate.addTransformation(new Transformation(States.S5, States.S0, "[']", TokenType.String, TokenType.Zeichen, false, false));
		
		/*automate.addTransformation(new Transformation(States.S3, States.S3, "[a-zA-Z0-9]", TokenType.Variable, null, true, false));
		
		
		automate.addTransformation(new Transformation(States.S3, States.S3, "[A-Z_:.-]", null, null, false, false));
				
		automate.addTransformation(new Transformation(States.S4, States.S3, "[A-Z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S4, States.S1, "[a-z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S4, States.S6, "[\\[]", TokenType.Liste, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S6, "[\\.]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[)]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S4, "[ ]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S3, "[:.-]", TokenType.Struktur, null, true, false));
		
		automate.addTransformation(new Transformation(States.S6, States.S3, "[A-Z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S6, States.S1, "[_]", TokenType.Variable, null, false, false));	
		automate.addTransformation(new Transformation(States.S6, States.S6, "[\\[]", TokenType.Liste, null, false, false));	// list in list
		automate.addTransformation(new Transformation(States.S6, States.S0, "[)]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S6, States.S4, "[\\]]", TokenType.Liste, null, false, false));
		
		automate.addTransformation(new Transformation(States.S11, States.S11, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S11, States.S4, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S11, States.S4, "[(]", TokenType.Struktur, null, false, false));
		*/
		
		
		
		
		
		
		
		
		/*automate.addTransformation(new Transformation(States.S0, States.S0, "[ ]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S0, States.S2, "[%]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S3, "[A-Z]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S9, "[:.-]", null, null, false, false));
		automate.addTransformation(new Transformation(States.S0, States.S1, "[a-z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S0, States.S6, "[\\[]", TokenType.Liste, null, false, false));	// list from start
		
		automate.addTransformation(new Transformation(States.S1, States.S1, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S1, States.S0, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S1, States.S4, "[(]", TokenType.Struktur, null, false, false));

		automate.addTransformation(new Transformation(States.S2, States.S2, ".*", null, null, false, false));
		
		automate.addTransformation(new Transformation(States.S3, States.S3, "[a-zA-Z0-9]", TokenType.Variable, null, true, false));
		automate.addTransformation(new Transformation(States.S3, States.S0, "[ ]", TokenType.Variable, null, false, false));
				
		automate.addTransformation(new Transformation(States.S4, States.S5, "[A-Z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S4, States.S11, "[a-z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S4, States.S6, "[\\[]", TokenType.Liste, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S6, "[\\.]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S0, "[)]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S4, "[ ]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S4, States.S10, "[:.-]", TokenType.Struktur, null, true, false));
		
		automate.addTransformation(new Transformation(States.S5, States.S5, "[a-zA-Z0-9]", TokenType.Variable, null, true, false));
		automate.addTransformation(new Transformation(States.S5, States.S4, "[,)]", TokenType.Variable, TokenType.Struktur, false, false));
		
		automate.addTransformation(new Transformation(States.S6, States.S7, "[A-Z]", null, null, false, false));	
		automate.addTransformation(new Transformation(States.S6, States.S8, "[_]", TokenType.Variable, null, false, false));	
		automate.addTransformation(new Transformation(States.S6, States.S6, "[\\[]", TokenType.Liste, null, false, false));	// list in list
		automate.addTransformation(new Transformation(States.S6, States.S0, "[)]", TokenType.Struktur, null, false, false));
		automate.addTransformation(new Transformation(States.S6, States.S4, "[\\]]", TokenType.Liste, null, false, false));
		
		automate.addTransformation(new Transformation(States.S7, States.S7, "[a-zA-Z0-9]", TokenType.Variable, null, true, false));
		automate.addTransformation(new Transformation(States.S7, States.S6, "[|\\]]", TokenType.Variable, TokenType.Liste, false, false));
		
		automate.addTransformation(new Transformation(States.S8, States.S6, "[|\\]]", TokenType.Liste, null, false, false));
		
		automate.addTransformation(new Transformation(States.S9, States.S9, "[:.-]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S9, States.S0, "[ ]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S9, States.S3, "[A-Z]", TokenType.Konstante, null, false, true));
		automate.addTransformation(new Transformation(States.S9, States.S1, "[a-z]", TokenType.Konstante, null, false, true));
		
		automate.addTransformation(new Transformation(States.S10, States.S10, "[:.-]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S10, States.S4, "[ ]", TokenType.Konstante, null, true, false));
		
		automate.addTransformation(new Transformation(States.S11, States.S11, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
		automate.addTransformation(new Transformation(States.S11, States.S4, "[ ]", TokenType.Konstante, null, false, false));
		automate.addTransformation(new Transformation(States.S11, States.S4, "[(]", TokenType.Struktur, null, false, false));*/
		
		
		
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
				
				/*switch (state) {
				case S0: // init
					
					if (c.matches("[a-z]")) {
						state = States.S1;
					}
					else if (c.matches("[A-Z]")) {
						state = States.S3;
					} 
					else if (c.matches("[\\[\\]]")) {
						list.add(new Token(buffer, TokenType.Liste));
						buffer = "";
					} 
					else if (c.matches("[_]")) {
						state = States.S5;
					}
					else if (c.matches("[%]")) {
						state = States.S4;
					}
					else if (c.matches("[ ]")) {
						state = States.S0;
						buffer = "";
					} 
					else if (c.matches("[).|]")) {
						list.add(new Token(buffer, TokenType.Struktur));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[,\\]]")) {
						list.add(new Token(buffer, TokenType.Liste));
						buffer = "";
						state=States.S0;
					}
					else {
						throw new Exception("unbekannter Term " + buffer+c);
					}
					
					break;
					
				case S1: // Konstante (end) oder Strutkur
					
					if (c.matches("[ ]")) {
						list.add(new Token(buffer, TokenType.Konstante));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[).|]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Struktur));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[,\\]]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Liste));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[a-zA-Z1-9]")) {
						state = States.S1;
					}
					else if (c.matches("[(]")) {
						list.add(new Token(buffer, TokenType.Struktur));
						buffer = "";
						state = States.S0;
					}
					else {
						throw new Exception("unbekannter Term " + buffer+c);
					}
					
					break;
				case S3: // Variable
					
					if (c.matches("[ ]")) {
						list.add(new Token(buffer, TokenType.Variable));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[).|]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Struktur));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[,\\]]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Liste));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[a-zA-Z1-9]")) {
						state = States.S3;
					}
					else {
						throw new Exception("unbekannter Term " + buffer);
					}
					
					break;
				case S4:
					skip = true;
					break;
				case S5: // Anonyme Variable
					
					if (c.matches("[ ]")) {
						list.add(new Token(buffer, TokenType.Variable));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[).|]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Struktur));
						buffer = "";
						state=States.S0;
					}
					else if (c.matches("[,\\]]")) {
						list.add(new Token(buffer.substring(0,buffer.length()-1), TokenType.Konstante));
						list.add(new Token(buffer.substring(buffer.length()-1), TokenType.Liste));
						buffer = "";
						state=States.S0;
					}
					else {
						throw new Exception("unbekannter Term " + buffer);
					}
					
					break;
				default:
					break;
				}*/
				
				
			}
			
			/*if (skip) {
				buffer="";
				skip = true;
				continue;
			}
			
			switch (state) {
			
			case S1:
				list.add(new Token(buffer, TokenType.Konstante));
				buffer = "";
				state=States.S0;
				break;
			case S3:
				list.add(new Token(buffer, TokenType.Variable));
				buffer = "";
				state=States.S0;
				break;
			default: 
				break;
			}*/
			
		}
		br.close();
		
		for (int i = 0; i < list.size(); i++)
			System.out.println(i+": " + list.get(i).toString());
	}
	
}
