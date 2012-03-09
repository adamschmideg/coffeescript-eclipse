package csep.example.cake.parser;

import csep.example.cake.parser.antlr.internal.InternalCakefileLexer;
import csep.parser.antlr.internal.InternalCoffeeScriptLexer;

/**
 * Mindless mappings between coffee and cakefile token types
 * @author adam
 *
 */
@SuppressWarnings("restriction")
public class TokenTypeMapper {
	static int[] COFFEE_TO_CAKEFILE = new int[90];
	static int[] CAKEFILE_TO_COFFEE = new int[90];
	
	static {
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_AT_SIGIL] = InternalCakefileLexer.RULE_AT_SIGIL;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_BOOL] = InternalCakefileLexer.RULE_BOOL;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_BOUND_FUNC_ARROW] = InternalCakefileLexer.RULE_BOUND_FUNC_ARROW;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_BY] = InternalCakefileLexer.RULE_BY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_CALL_END] = InternalCakefileLexer.RULE_CALL_END;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_CALL_START] = InternalCakefileLexer.RULE_CALL_START;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_CATCH] = InternalCakefileLexer.RULE_CATCH;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_CLASS] = InternalCakefileLexer.RULE_CLASS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_COLON] = InternalCakefileLexer.RULE_COLON;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_COLON_SLASH] = InternalCakefileLexer.RULE_COLON_SLASH;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_COMMA] = InternalCakefileLexer.RULE_COMMA;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_COMPARE] = InternalCakefileLexer.RULE_COMPARE;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_COMPOUND_ASSIGN] = InternalCakefileLexer.RULE_COMPOUND_ASSIGN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_DOT_DOT] = InternalCakefileLexer.RULE_DOT_DOT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_DOT] = InternalCakefileLexer.RULE_DOT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_DOUBLE_COLON] = InternalCakefileLexer.RULE_DOUBLE_COLON;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_ELLIPSIS] = InternalCakefileLexer.RULE_ELLIPSIS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_ELSE] = InternalCakefileLexer.RULE_ELSE;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_EQUAL] = InternalCakefileLexer.RULE_EQUAL;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_EXTENDS] = InternalCakefileLexer.RULE_EXTENDS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FINALLY] = InternalCakefileLexer.RULE_FINALLY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FORIN] = InternalCakefileLexer.RULE_FORIN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FOR] = InternalCakefileLexer.RULE_FOR;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FOROF] = InternalCakefileLexer.RULE_FOROF;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FUNC_ARROW] = InternalCakefileLexer.RULE_FUNC_ARROW;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_FUNC_EXIST] = InternalCakefileLexer.RULE_FUNC_EXIST;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_HERECOMMENT] = InternalCakefileLexer.RULE_HERECOMMENT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_IDENTIFIER] = InternalCakefileLexer.RULE_IDENTIFIER;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_IF] = InternalCakefileLexer.RULE_IF;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_INDENT] = InternalCakefileLexer.RULE_INDENT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_INDEX_END] = InternalCakefileLexer.RULE_INDEX_END;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_INDEX_PROTO] = InternalCakefileLexer.RULE_INDEX_PROTO;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_INDEX_SOAK] = InternalCakefileLexer.RULE_INDEX_SOAK;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_INDEX_START] = InternalCakefileLexer.RULE_INDEX_START;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_JS] = InternalCakefileLexer.RULE_JS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LBRACKET] = InternalCakefileLexer.RULE_LBRACKET;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LCURLY] = InternalCakefileLexer.RULE_LCURLY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LEADING_WHEN] = InternalCakefileLexer.RULE_LEADING_WHEN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LOGIC] = InternalCakefileLexer.RULE_LOGIC;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LOOP] = InternalCakefileLexer.RULE_LOOP;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_LPAREN] = InternalCakefileLexer.RULE_LPAREN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_MATH] = InternalCakefileLexer.RULE_MATH;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_MINUS] = InternalCakefileLexer.RULE_MINUS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_MINUS_MINUS] = InternalCakefileLexer.RULE_MINUS_MINUS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_NEW] = InternalCakefileLexer.RULE_NEW;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_NUMBER] = InternalCakefileLexer.RULE_NUMBER;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_OUTDENT] = InternalCakefileLexer.RULE_OUTDENT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_OWN] = InternalCakefileLexer.RULE_OWN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_PARAM_END] = InternalCakefileLexer.RULE_PARAM_END;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_PARAM_START] = InternalCakefileLexer.RULE_PARAM_START;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_PLUS] = InternalCakefileLexer.RULE_PLUS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_PLUS_PLUS] = InternalCakefileLexer.RULE_PLUS_PLUS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_POST_IF] = InternalCakefileLexer.RULE_POST_IF;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_QUESTION_DOT] = InternalCakefileLexer.RULE_QUESTION_DOT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_QUESTION] = InternalCakefileLexer.RULE_QUESTION;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_RBRACKET] = InternalCakefileLexer.RULE_RBRACKET;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_RCURLY] = InternalCakefileLexer.RULE_RCURLY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_REGEX] = InternalCakefileLexer.RULE_REGEX;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_RELATION] = InternalCakefileLexer.RULE_RELATION;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_RETURN] = InternalCakefileLexer.RULE_RETURN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_RPAREN] = InternalCakefileLexer.RULE_RPAREN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_SHIFT] = InternalCakefileLexer.RULE_SHIFT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_SL_COMMENT] = InternalCakefileLexer.RULE_SL_COMMENT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_STATEMENT] = InternalCakefileLexer.RULE_STATEMENT;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_STRING] = InternalCakefileLexer.RULE_STRING;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_SUPER] = InternalCakefileLexer.RULE_SUPER;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_SWITCH] = InternalCakefileLexer.RULE_SWITCH;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_TERMINATOR] = InternalCakefileLexer.RULE_TERMINATOR;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_THEN] = InternalCakefileLexer.RULE_THEN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_THIS] = InternalCakefileLexer.RULE_THIS;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_THROW] = InternalCakefileLexer.RULE_THROW;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_TRY] = InternalCakefileLexer.RULE_TRY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_UNARY] = InternalCakefileLexer.RULE_UNARY;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_UNTIL] = InternalCakefileLexer.RULE_UNTIL;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_WHEN] = InternalCakefileLexer.RULE_WHEN;
	    COFFEE_TO_CAKEFILE[InternalCoffeeScriptLexer.RULE_WHILE] = InternalCakefileLexer.RULE_WHILE;
	    
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_AT_SIGIL] = InternalCoffeeScriptLexer.RULE_AT_SIGIL;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_BOOL] = InternalCoffeeScriptLexer.RULE_BOOL;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_BOUND_FUNC_ARROW] = InternalCoffeeScriptLexer.RULE_BOUND_FUNC_ARROW;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_BY] = InternalCoffeeScriptLexer.RULE_BY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_CALL_END] = InternalCoffeeScriptLexer.RULE_CALL_END;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_CALL_START] = InternalCoffeeScriptLexer.RULE_CALL_START;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_CATCH] = InternalCoffeeScriptLexer.RULE_CATCH;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_CLASS] = InternalCoffeeScriptLexer.RULE_CLASS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_COLON] = InternalCoffeeScriptLexer.RULE_COLON;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_COLON_SLASH] = InternalCoffeeScriptLexer.RULE_COLON_SLASH;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_COMMA] = InternalCoffeeScriptLexer.RULE_COMMA;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_COMPARE] = InternalCoffeeScriptLexer.RULE_COMPARE;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_COMPOUND_ASSIGN] = InternalCoffeeScriptLexer.RULE_COMPOUND_ASSIGN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_DOT_DOT] = InternalCoffeeScriptLexer.RULE_DOT_DOT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_DOT] = InternalCoffeeScriptLexer.RULE_DOT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_DOUBLE_COLON] = InternalCoffeeScriptLexer.RULE_DOUBLE_COLON;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_ELLIPSIS] = InternalCoffeeScriptLexer.RULE_ELLIPSIS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_ELSE] = InternalCoffeeScriptLexer.RULE_ELSE;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_EQUAL] = InternalCoffeeScriptLexer.RULE_EQUAL;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_EXTENDS] = InternalCoffeeScriptLexer.RULE_EXTENDS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FINALLY] = InternalCoffeeScriptLexer.RULE_FINALLY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FORIN] = InternalCoffeeScriptLexer.RULE_FORIN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FOR] = InternalCoffeeScriptLexer.RULE_FOR;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FOROF] = InternalCoffeeScriptLexer.RULE_FOROF;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FUNC_ARROW] = InternalCoffeeScriptLexer.RULE_FUNC_ARROW;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_FUNC_EXIST] = InternalCoffeeScriptLexer.RULE_FUNC_EXIST;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_HERECOMMENT] = InternalCoffeeScriptLexer.RULE_HERECOMMENT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_IDENTIFIER] = InternalCoffeeScriptLexer.RULE_IDENTIFIER;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_IF] = InternalCoffeeScriptLexer.RULE_IF;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_INDENT] = InternalCoffeeScriptLexer.RULE_INDENT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_INDEX_END] = InternalCoffeeScriptLexer.RULE_INDEX_END;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_INDEX_PROTO] = InternalCoffeeScriptLexer.RULE_INDEX_PROTO;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_INDEX_SOAK] = InternalCoffeeScriptLexer.RULE_INDEX_SOAK;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_INDEX_START] = InternalCoffeeScriptLexer.RULE_INDEX_START;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_JS] = InternalCoffeeScriptLexer.RULE_JS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LBRACKET] = InternalCoffeeScriptLexer.RULE_LBRACKET;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LCURLY] = InternalCoffeeScriptLexer.RULE_LCURLY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LEADING_WHEN] = InternalCoffeeScriptLexer.RULE_LEADING_WHEN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LOGIC] = InternalCoffeeScriptLexer.RULE_LOGIC;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LOOP] = InternalCoffeeScriptLexer.RULE_LOOP;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_LPAREN] = InternalCoffeeScriptLexer.RULE_LPAREN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_MATH] = InternalCoffeeScriptLexer.RULE_MATH;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_MINUS] = InternalCoffeeScriptLexer.RULE_MINUS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_MINUS_MINUS] = InternalCoffeeScriptLexer.RULE_MINUS_MINUS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_NEW] = InternalCoffeeScriptLexer.RULE_NEW;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_NUMBER] = InternalCoffeeScriptLexer.RULE_NUMBER;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_OUTDENT] = InternalCoffeeScriptLexer.RULE_OUTDENT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_OWN] = InternalCoffeeScriptLexer.RULE_OWN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_PARAM_END] = InternalCoffeeScriptLexer.RULE_PARAM_END;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_PARAM_START] = InternalCoffeeScriptLexer.RULE_PARAM_START;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_PLUS] = InternalCoffeeScriptLexer.RULE_PLUS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_PLUS_PLUS] = InternalCoffeeScriptLexer.RULE_PLUS_PLUS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_POST_IF] = InternalCoffeeScriptLexer.RULE_POST_IF;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_QUESTION_DOT] = InternalCoffeeScriptLexer.RULE_QUESTION_DOT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_QUESTION] = InternalCoffeeScriptLexer.RULE_QUESTION;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_RBRACKET] = InternalCoffeeScriptLexer.RULE_RBRACKET;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_RCURLY] = InternalCoffeeScriptLexer.RULE_RCURLY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_REGEX] = InternalCoffeeScriptLexer.RULE_REGEX;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_RELATION] = InternalCoffeeScriptLexer.RULE_RELATION;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_RETURN] = InternalCoffeeScriptLexer.RULE_RETURN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_RPAREN] = InternalCoffeeScriptLexer.RULE_RPAREN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_SHIFT] = InternalCoffeeScriptLexer.RULE_SHIFT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_SL_COMMENT] = InternalCoffeeScriptLexer.RULE_SL_COMMENT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_STATEMENT] = InternalCoffeeScriptLexer.RULE_STATEMENT;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_STRING] = InternalCoffeeScriptLexer.RULE_STRING;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_SUPER] = InternalCoffeeScriptLexer.RULE_SUPER;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_SWITCH] = InternalCoffeeScriptLexer.RULE_SWITCH;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_TERMINATOR] = InternalCoffeeScriptLexer.RULE_TERMINATOR;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_THEN] = InternalCoffeeScriptLexer.RULE_THEN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_THIS] = InternalCoffeeScriptLexer.RULE_THIS;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_THROW] = InternalCoffeeScriptLexer.RULE_THROW;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_TRY] = InternalCoffeeScriptLexer.RULE_TRY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_UNARY] = InternalCoffeeScriptLexer.RULE_UNARY;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_UNTIL] = InternalCoffeeScriptLexer.RULE_UNTIL;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_WHEN] = InternalCoffeeScriptLexer.RULE_WHEN;
	    CAKEFILE_TO_COFFEE[InternalCakefileLexer.RULE_WHILE] = InternalCoffeeScriptLexer.RULE_WHILE;
	}
	
	public static int coffeeToCakefile(int coffeeType) {
		try {
			return COFFEE_TO_CAKEFILE[coffeeType];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return coffeeType;
		}
	}
	
	public static int cakefileToCoffee(int cakefileType) {
		try {
			return CAKEFILE_TO_COFFEE[cakefileType];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return cakefileType;
		}
	}
}
