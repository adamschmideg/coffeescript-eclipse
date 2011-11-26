grammar FunExp;

ID: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
NUMBER: '0'..'9'+;
WS: (' ')+ {skip();};

root
  : expression
  ;

// atom and functionCall would go here,
// but they are reachable via operation -> term
// so they are omitted here
expression
  : operation
  ;

atom
  : NUMBER
  | ID
  ;

functionCall
  : ID '(' expression (',' expression)* ')'
  ;

operation
  : multiOp
  ;

multiOp
  : additiveOp (('*' | '/') additiveOp)*
  ;

additiveOp
  : term (('+' | '-') term)*
  ;

term
  : atom
  | functionCall
  | '(' expression ')'
  ;
