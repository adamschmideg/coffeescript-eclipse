
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

assign
  : assignable EQUAL expression
  ;

// left-factor
expressionNotOperation
  : value
  | assign
  ;
 
expression
  : expressionNotOperation
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

// order of precedence, from higher to lower:
// questionOp, unaryOp, mathOp, additiveOp, shiftOp, relationOp, compareOp, logicOp

atom
  : expressionNotOperation
  ;

questionOp
  : atom QUESTION?
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
  //| logicOp
  ;

