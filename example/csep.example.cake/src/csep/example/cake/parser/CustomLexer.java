package csep.example.cake.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

import csep.example.cake.parser.antlr.internal.InternalCakefileLexer;
import csep.parser.Lexer;
import csep.parser.antlr.internal.InternalCoffeeScriptLexer;

@SuppressWarnings("restriction")
public class CustomLexer extends Lexer {
  static int[] TOKEN_MAP = new int[90];

  static {
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_AT_SIGIL] = InternalCakefileLexer.RULE_AT_SIGIL;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_BOOL] = InternalCakefileLexer.RULE_BOOL;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_BOUND_FUNC_ARROW] = InternalCakefileLexer.RULE_BOUND_FUNC_ARROW;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_BY] = InternalCakefileLexer.RULE_BY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_CALL_END] = InternalCakefileLexer.RULE_CALL_END;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_CALL_START] = InternalCakefileLexer.RULE_CALL_START;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_CATCH] = InternalCakefileLexer.RULE_CATCH;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_CLASS] = InternalCakefileLexer.RULE_CLASS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_COLON] = InternalCakefileLexer.RULE_COLON;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_COLON_SLASH] = InternalCakefileLexer.RULE_COLON_SLASH;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_COMMA] = InternalCakefileLexer.RULE_COMMA;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_COMPARE] = InternalCakefileLexer.RULE_COMPARE;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_COMPOUND_ASSIGN] = InternalCakefileLexer.RULE_COMPOUND_ASSIGN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_DOT_DOT] = InternalCakefileLexer.RULE_DOT_DOT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_DOT] = InternalCakefileLexer.RULE_DOT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_DOUBLE_COLON] = InternalCakefileLexer.RULE_DOUBLE_COLON;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_ELLIPSIS] = InternalCakefileLexer.RULE_ELLIPSIS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_ELSE] = InternalCakefileLexer.RULE_ELSE;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_EQUAL] = InternalCakefileLexer.RULE_EQUAL;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_EXTENDS] = InternalCakefileLexer.RULE_EXTENDS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FINALLY] = InternalCakefileLexer.RULE_FINALLY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FORIN] = InternalCakefileLexer.RULE_FORIN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FOR] = InternalCakefileLexer.RULE_FOR;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FOROF] = InternalCakefileLexer.RULE_FOROF;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FUNC_ARROW] = InternalCakefileLexer.RULE_FUNC_ARROW;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_FUNC_EXIST] = InternalCakefileLexer.RULE_FUNC_EXIST;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_HERECOMMENT] = InternalCakefileLexer.RULE_HERECOMMENT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_IDENTIFIER] = InternalCakefileLexer.RULE_IDENTIFIER;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_IF] = InternalCakefileLexer.RULE_IF;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_INDENT] = InternalCakefileLexer.RULE_INDENT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_INDEX_END] = InternalCakefileLexer.RULE_INDEX_END;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_INDEX_PROTO] = InternalCakefileLexer.RULE_INDEX_PROTO;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_INDEX_SOAK] = InternalCakefileLexer.RULE_INDEX_SOAK;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_INDEX_START] = InternalCakefileLexer.RULE_INDEX_START;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_JS] = InternalCakefileLexer.RULE_JS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LBRACKET] = InternalCakefileLexer.RULE_LBRACKET;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LCURLY] = InternalCakefileLexer.RULE_LCURLY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LEADING_WHEN] = InternalCakefileLexer.RULE_LEADING_WHEN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LOGIC] = InternalCakefileLexer.RULE_LOGIC;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LOOP] = InternalCakefileLexer.RULE_LOOP;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_LPAREN] = InternalCakefileLexer.RULE_LPAREN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_MATH] = InternalCakefileLexer.RULE_MATH;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_MINUS] = InternalCakefileLexer.RULE_MINUS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_MINUS_MINUS] = InternalCakefileLexer.RULE_MINUS_MINUS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_NEW] = InternalCakefileLexer.RULE_NEW;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_NUMBER] = InternalCakefileLexer.RULE_NUMBER;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_OUTDENT] = InternalCakefileLexer.RULE_OUTDENT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_OWN] = InternalCakefileLexer.RULE_OWN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_PARAM_END] = InternalCakefileLexer.RULE_PARAM_END;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_PARAM_START] = InternalCakefileLexer.RULE_PARAM_START;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_PLUS] = InternalCakefileLexer.RULE_PLUS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_PLUS_PLUS] = InternalCakefileLexer.RULE_PLUS_PLUS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_POST_IF] = InternalCakefileLexer.RULE_POST_IF;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_QUESTION_DOT] = InternalCakefileLexer.RULE_QUESTION_DOT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_QUESTION] = InternalCakefileLexer.RULE_QUESTION;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_RBRACKET] = InternalCakefileLexer.RULE_RBRACKET;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_RCURLY] = InternalCakefileLexer.RULE_RCURLY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_REGEX] = InternalCakefileLexer.RULE_REGEX;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_RELATION] = InternalCakefileLexer.RULE_RELATION;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_RETURN] = InternalCakefileLexer.RULE_RETURN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_RPAREN] = InternalCakefileLexer.RULE_RPAREN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_SHIFT] = InternalCakefileLexer.RULE_SHIFT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_SL_COMMENT] = InternalCakefileLexer.RULE_SL_COMMENT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_STATEMENT] = InternalCakefileLexer.RULE_STATEMENT;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_STRING] = InternalCakefileLexer.RULE_STRING;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_SUPER] = InternalCakefileLexer.RULE_SUPER;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_SWITCH] = InternalCakefileLexer.RULE_SWITCH;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_TERMINATOR] = InternalCakefileLexer.RULE_TERMINATOR;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_THEN] = InternalCakefileLexer.RULE_THEN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_THIS] = InternalCakefileLexer.RULE_THIS;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_THROW] = InternalCakefileLexer.RULE_THROW;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_TRY] = InternalCakefileLexer.RULE_TRY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_UNARY] = InternalCakefileLexer.RULE_UNARY;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_UNTIL] = InternalCakefileLexer.RULE_UNTIL;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_WHEN] = InternalCakefileLexer.RULE_WHEN;
    TOKEN_MAP[InternalCoffeeScriptLexer.RULE_WHILE] = InternalCakefileLexer.RULE_WHILE;
  }

	public CustomLexer(CharStream in) {
		super(in);
	}

	@Override
	public Token nextToken() {
		Token token = super.nextToken();
		token.setType(mapTokenType(token.getType()));
		if (token.getType() == InternalCakefileLexer.RULE_IDENTIFIER) {
			if ("task".equals(token.getText())) {
				token.setType(InternalCakefileLexer.RULE_TASK);
			}
		}
		return token;
	}
	
	public static int mapTokenType(int coffeeTokenType) {
		if (coffeeTokenType >= 0)
			return TOKEN_MAP[coffeeTokenType];
		else
			return coffeeTokenType;
	}
}
