Lexer
=====

Simple lexer for prolog, which I made for a school lecture, not finished..

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
<Liste>			::= <ListeStart>{<ListeInhalt><ListeTrennzeichen>}<ListeEnde>
<ListeStart>		::= [
<ListeEnde>		::= ]	
<ListeTrennzeichen>	::=	|
<ListeInhalt>		::=	<Variable> | <String> | <Konstante> | <Atom> | Struktur>
