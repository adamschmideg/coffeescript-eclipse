
root : body;

body	: value;
//expression (TERMINATOR expression)*;

expression
  : value
  ;
  
value
  : IDENTIFIER
  | literal
  | parenthetical
  ;

literal
  : alphaNumeric
  ;

alphaNumeric
 :	 NUMBER 
 | 	STRING;

parenthetical
  : LPAREN expression RPAREN
  ;
