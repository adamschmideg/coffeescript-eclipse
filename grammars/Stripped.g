grammar Stripped;

options {
  output = AST;
}

entryRuleRoot
  :
  ruleRoot EOF
  ;

ruleRoot
  :
  (
    ruleBody
    | (ruleBlock RULE_TERMINATOR)
  )
  ;

entryRuleBody
  :
  ruleBody EOF
  ;

ruleBody
  :
  (
    ( (ruleLine)) (RULE_TERMINATOR ( (ruleLine))?)*
  )
  ;

entryRuleLine
  :
  ruleLine EOF
  ;

ruleLine
  :
  (
    ruleStmt
    | ruleExpression
  )
  ;

entryRuleStmt
  :
  ruleStmt EOF
  ;

ruleStmt
  :
  (
    ruleReturnStmt
    | ruleThrowStmt
  )
  ;

entryRuleReturnStmt
  :
  ruleReturnStmt EOF
  ;

ruleReturnStmt
  :
  (RULE_RETURN ( (ruleExpression)))
  ;

entryRuleThrowStmt
  :
  ruleThrowStmt EOF
  ;

ruleThrowStmt
  :
  (RULE_THROW ( (ruleExpression)))
  ;

entryRuleExpression
  :
  ruleExpression EOF
  ;

ruleExpression
  :
  rulePostfixIf
  ;

entryRulePostfixIf
  :
  rulePostfixIf EOF
  ;

rulePostfixIf
  :
  (
    ruleAssignment
    (
      (
        (
          (
            ()
            (
              (
                (
                  RULE_POST_IF
                  | RULE_IF
                )
              )
            )
          )
        )
          =>
        (
          ()
          (
            (
              (
                RULE_POST_IF
                | RULE_IF
              )
            )
          )
        )
      )
      ( (ruleExpression))
    )?
  )
  ;

entryRuleAssignment
  :
  ruleAssignment EOF
  ;

ruleAssignment
  :
  (
    (
      ()
      (
        (
          (
            (
              ( (ruleAssignable))
              (
                (
                  (
                    RULE_EQUAL
                    | RULE_COMPOUND_ASSIGN
                  )
                )
              )
            )
          )
            =>
          (
            ( (ruleAssignable))
            (
              (
                (
                  RULE_EQUAL
                  | RULE_COMPOUND_ASSIGN
                )
              )
            )
          )
        )
        ( (ruleExpression))
      )
    )
    | ruleLogicOp
  )
  ;

entryRuleLogicOp
  :
  ruleLogicOp EOF
  ;

ruleLogicOp
  :
  (
    ruleCompareOp
    (
      (
        (
          (
            () ( (RULE_LOGIC))
          )
        )
          =>
        (
          () ( (RULE_LOGIC))
        )
      )
      ( (ruleCompareOp))
    )*
  )
  ;

entryRuleCompareOp
  :
  ruleCompareOp EOF
  ;

ruleCompareOp
  :
  (
    ruleRelationOp
    (
      (
        (
          (
            () ( (RULE_COMPARE))
          )
        )
          =>
        (
          () ( (RULE_COMPARE))
        )
      )
      ( (ruleRelationOp))
    )*
  )
  ;

entryRuleRelationOp
  :
  ruleRelationOp EOF
  ;

ruleRelationOp
  :
  (
    ruleShiftOp
    (
      (
        (
          (
            () ( (RULE_RELATION))
          )
        )
          =>
        (
          () ( (RULE_RELATION))
        )
      )
      ( (ruleShiftOp))
    )*
  )
  ;

entryRuleShiftOp
  :
  ruleShiftOp EOF
  ;

ruleShiftOp
  :
  (
    ruleAdditiveOp
    (
      (
        (
          (
            () ( (RULE_SHIFT))
          )
        )
          =>
        (
          () ( (RULE_SHIFT))
        )
      )
      ( (ruleAdditiveOp))
    )*
  )
  ;

entryRuleAdditiveOp
  :
  ruleAdditiveOp EOF
  ;

ruleAdditiveOp
  :
  (
    ruleMathOp
    (
      (
        (
          (
            ()
            (
              (
                (
                  RULE_PLUS
                  | RULE_MINUS
                )
              )
            )
          )
        )
          =>
        (
          ()
          (
            (
              (
                RULE_PLUS
                | RULE_MINUS
              )
            )
          )
        )
      )
      ( (ruleMathOp))
    )*
  )
  ;

entryRuleMathOp
  :
  ruleMathOp EOF
  ;

ruleMathOp
  :
  (
    ruleUnaryOp
    (
      (
        (
          (
            () ( (RULE_MATH))
          )
        )
          =>
        (
          () ( (RULE_MATH))
        )
      )
      ( (ruleUnaryOp))
    )*
  )
  ;

entryRuleUnaryOp
  :
  ruleUnaryOp EOF
  ;

ruleUnaryOp
  :
  (
    (
      (
        RULE_UNARY
        | RULE_PLUS
        | RULE_MINUS
      )
      ruleExpression
    )
    |
    (
      (
        RULE_PLUS_PLUS
        | RULE_MINUS_MINUS
      )
      ruleVariable
    )
    | (ruleApplication (RULE_QUESTION)?)
  )
  ;

entryRuleApplication
  :
  ruleApplication EOF
  ;

ruleApplication
  :
  (
    ( (rulePrimaryExpression)) ( (ruleFeatureCall))*
  )
  ;

entryRuleFeatureCall
  :
  ruleFeatureCall EOF
  ;

ruleFeatureCall
  :
  (
    ruleFunctionCall
    | rulePropertyAccess
  )
  ;

entryRuleFunctionCall
  :
  ruleFunctionCall EOF
  ;

ruleFunctionCall
  :
  (
    () (RULE_FUNC_EXIST)? RULE_CALL_START RULE_CALL_END
  )
  ;

entryRulePropertyAccess
  :
  rulePropertyAccess EOF
  ;

rulePropertyAccess
  :
  (
    ruleNamedPropertyAccess
    | ruleIndexedPropertyAccess
  )
  ;

entryRuleNamedPropertyAccess
  :
  ruleNamedPropertyAccess EOF
  ;

ruleNamedPropertyAccess
  :
  (
    (
      (
        (
          (
            RULE_DOT
            | RULE_QUESTION_DOT
          )
        )
      )
      ( (ruleId))
    )
    |
    (
      ( (RULE_DOUBLE_COLON)) ( (ruleId))?
    )
  )
  ;

entryRuleIndexedPropertyAccess
  :
  ruleIndexedPropertyAccess EOF
  ;

ruleIndexedPropertyAccess
  :
  (RULE_INDEX_START ( (ruleIndex)) RULE_INDEX_END)
  ;

entryRuleIndex
  :
  ruleIndex EOF
  ;

ruleIndex
  :
  (
    (
      (
        ( ( (RULE_DOT_DOT))) => ( (RULE_DOT_DOT))
      )
      ( (ruleExpression))
    )
    |
    (
      (
        (
          (
            ( (ruleExpression)) ( (RULE_DOT_DOT))
          )
        )
          =>
        (
          ( (ruleExpression)) ( (RULE_DOT_DOT))
        )
      )
      ( (ruleExpression))?
    )
    | ( (ruleExpression))
  )
  ;

entryRulePrimaryExpression
  :
  rulePrimaryExpression EOF
  ;

rulePrimaryExpression
  :
  (
    ruleIfExp
    |
    (
      (RULE_LPAREN) => ruleParenthetical
    )
    | ruleLiteral
    |
    (
      (RULE_LBRACKET) => ruleArray
    )
    | ruleId
  )
  ;

entryRuleBlock
  :
  ruleBlock EOF
  ;

ruleBlock
  :
  (
    () RULE_INDENT (ruleBody)? RULE_OUTDENT
  )
  ;

entryRuleIfExp
  :
  ruleIfExp EOF
  ;

ruleIfExp
  :
  (
    ( (ruleCondBlock)) (RULE_ELSE ( (ruleCondBlock)))* (RULE_ELSE ( (ruleBlock)))?
  )
  ;

entryRuleCondBlock
  :
  ruleCondBlock EOF
  ;

ruleCondBlock
  :
  (
    ( (RULE_IF)) ( (ruleExpression)) ( (ruleBlock))
  )
  ;

entryRuleId
  :
  ruleId EOF
  ;

ruleId
  :
  ( (RULE_IDENTIFIER))
  ;

entryRuleProperty
  :
  ruleProperty EOF
  ;

ruleProperty
  :
  (ruleId RULE_DOT ( (ruleId)))
  ;

entryRuleVariable
  :
  ruleVariable EOF
  ;

ruleVariable
  :
  (
    ruleId
    | ruleProperty
  )
  ;

entryRuleAssignable
  :
  ruleAssignable EOF
  ;

ruleAssignable
  :
  (
    ruleVariable
    |
    (
      (RULE_LBRACKET) => ruleArray
    )
  )
  ;

entryRuleArg
  :
  ruleArg EOF
  ;

ruleArg
  :
  (
    ( (ruleExpression)) ( (RULE_ELLIPSIS))?
  )
  ;

entryRuleArgLine
  :
  ruleArgLine EOF
  ;

ruleArgLine
  :
  (
    ( (ruleArg)) (RULE_COMMA ( (ruleArg)))* (RULE_COMMA)?
  )
  ;

entryRuleExplicitArgList
  :
  ruleExplicitArgList EOF
  ;

ruleExplicitArgList
  :
  (
    ( (ruleArgLine))
    (
      (RULE_TERMINATOR ( (ruleArgLine)))
      | (RULE_INDENT ( (ruleArgLine)) RULE_OUTDENT)
    )*
  )
  ;

entryRuleArgList
  :
  ruleArgList EOF
  ;

ruleArgList
  :
  (
    ()
    (
      ( (ruleExplicitArgList))
      | (RULE_INDENT ( (ruleExplicitArgList)) RULE_OUTDENT)
    )?
  )
  ;

entryRuleArray
  :
  ruleArray EOF
  ;

ruleArray
  :
  (
    (
      (RULE_LBRACKET) => RULE_LBRACKET
    )
    ruleArgList RULE_RBRACKET
  )
  ;

entryRuleNumberLiteral
  :
  ruleNumberLiteral EOF
  ;

ruleNumberLiteral
  :
  ( () RULE_NUMBER)
  ;

entryRuleStringLiteral
  :
  ruleStringLiteral EOF
  ;

ruleStringLiteral
  :
  ( () RULE_STRING)
  ;

entryRuleBoolLiteral
  :
  ruleBoolLiteral EOF
  ;

ruleBoolLiteral
  :
  ( () RULE_BOOL)
  ;

entryRuleLiteral
  :
  ruleLiteral EOF
  ;

ruleLiteral
  :
  (
    ruleNumberLiteral
    | ruleStringLiteral
    | ruleBoolLiteral
  )
  ;

entryRuleParenthetical
  :
  ruleParenthetical EOF
  ;

ruleParenthetical
  :
  (
    (
      (RULE_LPAREN) => RULE_LPAREN
    )
    ( (ruleBody)) RULE_RPAREN
  )
  ;

RULE_ELLIPSIS
  :
  '...'
  ;

RULE_DOT_DOT
  :
  '..'
  ;

RULE_DOUBLE_COLON
  :
  '::'
  ;

RULE_SHIFT
  :
  (
    '<<'
    | '>>'
  )
  ;

RULE_COMPARE
  :
  (
    '<'
    | '=='
    | '>'
    | '<='
    | '>='
    | '!='
  )
  ;

RULE_COMPOUND_ASSIGN
  :
  (
    '+='
    | '-='
  )
  ;

RULE_PLUS_PLUS
  :
  '++'
  ;

RULE_MINUS_MINUS
  :
  '--'
  ;

RULE_AT_SIGIL
  :
  '@'
  ;

RULE_BOOL
  :
  (
    'true'
    | 'false'
    | 'undefined'
    | 'null'
  )
  ;

RULE_BOUND_FUNC_ARROW
  :
  'dummy BOUND_FUNC_ARROW'
  ;

RULE_BY
  :
  'dummy BY'
  ;

RULE_CALL_END
  :
  '*)'
  ;

RULE_CALL_START
  :
  '(*'
  ;

RULE_CATCH
  :
  'dummy CATCH'
  ;

RULE_CLASS
  :
  'class'
  ;

RULE_COLON
  :
  ':'
  ;

RULE_COLON_SLASH
  :
  'dummy COLON_SLASH'
  ;

RULE_COMMA
  :
  'dummy COMMA'
  ;

RULE_DOT
  :
  '.'
  ;

RULE_ELSE
  :
  'else'
  ;

RULE_EQUAL
  :
  '='
  ;

RULE_EXTENDS
  :
  'dummy EXTENDS'
  ;

RULE_FINALLY
  :
  'dummy FINALLY'
  ;

RULE_FOR
  :
  'dummy FOR'
  ;

RULE_FORIN
  :
  'dummy FORIN'
  ;

RULE_FOROF
  :
  'dummy FOROF'
  ;

RULE_FUNC_ARROW
  :
  'dummy FUNC_ARROW'
  ;

RULE_FUNC_EXIST
  :
  'dummy FUNC_EXIST'
  ;

RULE_HERECOMMENT
  :
  'dummy HERECOMMENT'
  ;

RULE_IF
  :
  (
    'if'
    | 'unless'
  )
  ;

RULE_INDENT
  :
  '{*'
  ;

RULE_INDEX_END
  :
  '*]'
  ;

RULE_INDEX_PROTO
  :
  'dummy INDEX_PROTO'
  ;

RULE_INDEX_SOAK
  :
  'dummy INDEX_SOAK'
  ;

RULE_INDEX_START
  :
  '[*'
  ;

RULE_JS
  :
  'dummy JS'
  ;

RULE_LBRACKET
  :
  '['
  ;

RULE_LCURLY
  :
  'dummy LCURLY'
  ;

RULE_LEADING_WHEN
  :
  'dummy LEADING_WHEN'
  ;

RULE_LOGIC
  :
  (
    '&&'
    | '||'
  )
  ;

RULE_LOOP
  :
  'dummy LOOP'
  ;

RULE_LPAREN
  :
  '('
  ;

RULE_MATH
  :
  (
    '*'
    | '/'
  )
  ;

RULE_MINUS
  :
  '-'
  ;

RULE_NEW
  :
  'dummy NEW'
  ;

RULE_NUMBER
  :
  ('0'..'9')+
  ;

RULE_OUTDENT
  :
  '*}'
  ;

RULE_OWN
  :
  'dummy OWN'
  ;

RULE_PARAM_END
  :
  'dummy PARAM_END'
  ;

RULE_PARAM_START
  :
  'dummy PARAM_START'
  ;

RULE_PLUS
  :
  '+'
  ;

RULE_POST_IF
  :
  'dummy POST_IF'
  ;

RULE_QUESTION
  :
  '?'
  ;

RULE_QUESTION_DOT
  :
  '?.'
  ;

RULE_RBRACKET
  :
  ']'
  ;

RULE_RCURLY
  :
  'dummy RCURLY'
  ;

RULE_REGEX
  :
  'dummy REGEX'
  ;

RULE_RELATION
  :
  (
    'in'
    | 'of'
    | 'instanceof'
  )
  ;

RULE_RETURN
  :
  'return'
  ;

RULE_RPAREN
  :
  ')'
  ;

RULE_STATEMENT
  :
  'dummy STATEMENT'
  ;

RULE_STRING
  :
  '"'
  (
    'a'..'z'
    | ' '
  )*
  '"'
  ;

RULE_SUPER
  :
  'dummy SUPER'
  ;

RULE_SWITCH
  :
  'dummy SWITCH'
  ;

RULE_TERMINATOR
  :
  '\n'+
  ;

RULE_THEN
  :
  'dummy THEN'
  ;

RULE_THIS
  :
  'dummy THIS'
  ;

RULE_THROW
  :
  'throw'
  ;

RULE_TRY
  :
  'dummy TRY'
  ;

RULE_UNARY
  :
  (
    '!'
    | '~'
    | 'new'
  )
  ;

RULE_UNTIL
  :
  'dummy UNTIL'
  ;

RULE_WHEN
  :
  'dummy WHEN'
  ;

RULE_WHILE
  :
  'dummy WHILE'
  ;

RULE_IDENTIFIER
  :
  '^'?
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
    | '0'..'9'
  )*
  ;

RULE_WS
  :
  (
    ' '
    | '\t'
  )+
  ;
