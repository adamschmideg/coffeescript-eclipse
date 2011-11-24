
root
  : body
  ///| block TERMINATOR
  ;

body
  : line
  ;

line
  : expression
  ///| statement
  ;

expression
  : value
  | operation
  ;
 
identifier
  : IDENTIFIER
  ;

simpleAssignable
  : identifier
  ;

assignable
  : simpleAssignable
  ;

value
  : assignable
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
  : LPAREN body RPAREN
  ;

operation
  : MINUS expression
  | PLUS expression
  | MINUS_MINUS simpleAssignable
  | PLUS_PLUS simpleAssignable
  | simpleAssignable PLUS_PLUS
  | simpleAssignable MINUS_MINUS
  | simpleAssignable COMPOUND_ASSIGN expression
  ;
