
root
  : body
  ;

body
  : line
  ;

line
  : expression
  ;

assign
  : assignable EQUAL expression
  ;

expression
  : value
  | assign
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
  : NUMBER 
  | STRING;

parenthetical
  : LPAREN body RPAREN
  ;

// term should be the same as expression except operation to avoid left-recursion
term
  : value
  | assign
  ;

questionOp
  : term QUESTION?
  ;

mathOp
  : questionOp (MATH questionOp)*
  ;

additiveOp
  : mathOp ((PLUS | MINUS) mathOp)*
  ;

shiftOp
  : additiveOp (SHIFT additiveOp)*
  ;

relationOp
  : shiftOp (RELATION shiftOp)*
  ;

compareOp
  : relationOp (COMPARE relationOp)*
  ;

logicOp
  : compareOp (LOGIC compareOp)*
  ;

operation
  : UNARY expression
  | MINUS expression
  | PLUS expression
  | MINUS_MINUS simpleAssignable
  | PLUS_PLUS simpleAssignable
  | simpleAssignable PLUS_PLUS
  | simpleAssignable MINUS_MINUS
  | simpleAssignable COMPOUND_ASSIGN expression
  | logicOp
  ;

