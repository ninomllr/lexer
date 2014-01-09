Lexer
=====

Simpler Lexer für prolog. Es wird vermutlich nicht jedes File funktionieren, da der Automat evtl. noch erweitert werden müsste. Jedoch wurde mit dem Übungsfile blocksworld.pl erfolgreich getestet. Der Automat des Lexers ist komplett dynamisch und könnte theoretisch auch für andere Sprachen verwendet werden. Der Automat arbeitet mit Regex um Zeichenmengen zu erkennen, arbeitet aber sich aber bei jedem String Zeichen (char) für Zeichen (char) durch das komplette File.


Verwendung
====
java -jar lexer.jar /home/nino/file.pl

Grammatik
=====
Allgemeine Definitionen: 
<Prolog> 		::=	<Steuerzeichen> | <Term> | <Kommentar> 
<Term> 			::=	<Konstante> | <Variable> | <Struktur> 

Zeichenmenge: 
<Steuerzeichen> 	::=	Zeilenumbruch | Leerzeichen | Tabulator 
<Kleinbuchstabe>  	::=	a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z 
<Grossbuchstabe>  	::=	A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z

<Ziffer>  		::= 	0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 
<Symbol> 		::= 	( | ) | [ | ] | { | } | . | | | ! | = | < | : > | + | ­ | * | / | ^

Konstanten:
<Konstante> 		::=	<Atom> | <Zahl> 
<Atom>			::=	<AtomBeginn> [<AtomInhalt>] 
<AtomBeginn>		::=	<Kleinbuchstabe> 
<AtomInhalt> 		::=	{ <Grossbuchstabe> | <Kleinbuchstabe> | <Ziffer> } 
<Zahl> 			::=	<Ziffer> { <Ziffer> }

Variable:
<Variable> 		::= 	<VariableBeginn> [<VariableInhalt>] | _ 
<VariableBeginn> 	::= 	<Grossbuchstabe> 
<VariableInhalt> 	::= 	<AtomInhalt>

String:
<String> 		::= 	<StringBeginn><StringInhalt><StringEnde> 
<StringBeginn> 		::= 	Hochkomma 
<StringInhalt> 		::= 	{ <Grossbuchstabe> | <Kleinbuchstabe> | <Ziffer> | <Symbol> } 
<StringEnde> 		::= 	Hochkomma

Kommentar:
<Kommentar> 		::=	<KommentarBeginn> <KommentarInhalt> <KommentarEnde>
<KommentarBeginn> 	::= 	% 
<KommentarInhalt> 	::=	{ <Grossbuchstabe> | <Kleinbuchstabe> | <Ziffer> | <Symbol> } 
<KommentarEnde>		::=	Zeilenumbruch | Dateiende

Struktur: 
<Struktur>		::= 

Liste:
<Liste>			::= 	<ListeStart>{<ListeInhalt><ListeTrennzeichen>}<ListeEnde>
<ListeStart>		::= 	[
<ListeEnde>		::= 	]	
<ListeTrennzeichen>	::=	|
<ListeInhalt>		::=	<Variable> | <String> | <Konstante> | <Atom> | Struktur>


Definition des Automaten / Übergängen
====
Die Definition ist folgendermassen:
Transformation(VON_STATUS, ZU_STATUS, ERLAUBTE_ZEICHENMENGE, TOKEN_TYP, TOKEN_TYP_LETZTES_SYMBOL, NUR_EOL, GEHE_ZURUECK);

TOKEN_TYP = alle Zeichen im Buffer werden nun zu einem Token mit TOKEN_TYP gemacht, ausser es ist NULL dann ist es kein Endzustand
TOKEN_TYP_LETZTES_SYMBOL = das letzte Symbol wird zu einem anderen Token gemacht und vom Hauptbuffer abgezogen
NUR_EOL = es wird nur ein Token erstellt, wenn man EOL ist
GEHE_ZURUECK = das letzte Symbol wird nicht dem Token angehängt sondern verbleibt im Buffer und gehört bereits zum nächsten Token


Automat
====
Transformation(States.S0, States.S2, "[%]", null, null, false, false));
Transformation(States.S0, States.S2, "/", null, null, false, false));
Transformation(States.S0, States.S0, "[_]", TokenType.Variable, null, false, false));
Transformation(States.S0, States.S3, "[A-Z]", null, null, false, false));
Transformation(States.S0, States.S1, "[a-z]", null, null, false, false));
Transformation(States.S0, States.S0, "[\\[\\]]", TokenType.Liste, null, false, false));
Transformation(States.S0, States.S4, "[\\.\\!:-]", null, null, false, false));
Transformation(States.S0, States.S0, "[ \t]", null, null, false, false));	
Transformation(States.S0, States.S0, "[,|)]", TokenType.Zeichen, null, false, false));
Transformation(States.S0, States.S5, "[']", TokenType.Zeichen, null, false, false));
		
Transformation(States.S1, States.S1, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
Transformation(States.S1, States.S0, "[(]", TokenType.Struktur, TokenType.Zeichen, false, false));
Transformation(States.S1, States.S0, "[ ]", TokenType.Konstante, null, false, false));
Transformation(States.S1, States.S0, "[,|)]", TokenType.Konstante, TokenType.Zeichen, false, false));
Transformation(States.S1, States.S0, "[\\]]", TokenType.Konstante, TokenType.Liste, false, false));
Transformation(States.S1, States.S4, "[\\.\\!\t:-]", null, null, false, true));

Transformation(States.S2, States.S2, ".*", null, null, false, false));
		
Transformation(States.S3, States.S3, "[a-zA-Z0-9_]", TokenType.Konstante, null, true, false));
Transformation(States.S3, States.S0, "[ ]", TokenType.Variable, null, false, false));
Transformation(States.S3, States.S0, "[,|)]", TokenType.Variable, TokenType.Zeichen, false, false));
Transformation(States.S3, States.S0, "[\\]]", TokenType.Variable,  TokenType.Liste, false, false));
		
Transformation(States.S4, States.S4, "[\\.\\!\t:-]", null, null, false, false));
Transformation(States.S4, States.S0, "[ ]", TokenType.Konstante, null, false, false));
Transformation(States.S4, States.S0, "[,|]", TokenType.Konstante, TokenType.Zeichen, true, false));
Transformation(States.S4, States.S0, "[\\]\\[]", TokenType.Konstante, TokenType.Liste, false, false));
Transformation(States.S4, States.S0, "[a-zA-Z0-9]", TokenType.Konstante, null, false, true));
		
Transformation(States.S5, States.S5, "[a-zA-Z0-9 ]", null, null, false, true));
Transformation(States.S5, States.S0, "[']", TokenType.String, TokenType.Zeichen, false, false));

