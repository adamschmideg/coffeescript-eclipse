package csep.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

import beaver.Symbol;

import com.aptana.editor.coffee.parsing.Terminals;
import com.aptana.editor.coffee.parsing.lexer.CoffeeScanner;
import com.aptana.editor.coffee.parsing.lexer.CoffeeSymbol;

import csep.parser.antlr.internal.InternalCoffeeScriptLexer;

/**
 * A token which is instantiated with a beaver.Symbol
 * @author Adam Schmideg <adam@schmideg.net>
 *
 */
public class BeaverToken extends CommonToken {

	private static final long serialVersionUID = 1L;
	/**
	 * A mapping from Aptana terminal id to generated Antlr token id
	 */
	public final static int[] TOKEN_MAP = new int[77];

	static {
		TOKEN_MAP[Terminals.EOF] = InternalCoffeeScriptLexer.EOF;
		TOKEN_MAP[Terminals.MINUS] = InternalCoffeeScriptLexer.RULE_MINUS;
		TOKEN_MAP[Terminals.PLUS] = InternalCoffeeScriptLexer.RULE_PLUS;
		TOKEN_MAP[Terminals.WHILE] = InternalCoffeeScriptLexer.RULE_WHILE;
		TOKEN_MAP[Terminals.UNTIL] = InternalCoffeeScriptLexer.RULE_UNTIL;
		TOKEN_MAP[Terminals.FOR] = InternalCoffeeScriptLexer.RULE_FOR;
		TOKEN_MAP[Terminals.IDENTIFIER] = InternalCoffeeScriptLexer.RULE_IDENTIFIER;
		TOKEN_MAP[Terminals.AT_SIGIL] = InternalCoffeeScriptLexer.RULE_AT_SIGIL;
		TOKEN_MAP[Terminals.LCURLY] = InternalCoffeeScriptLexer.RULE_LCURLY;
		TOKEN_MAP[Terminals.LBRACKET] = InternalCoffeeScriptLexer.RULE_LBRACKET;
		TOKEN_MAP[Terminals.NUMBER] = InternalCoffeeScriptLexer.RULE_NUMBER;
		TOKEN_MAP[Terminals.STRING] = InternalCoffeeScriptLexer.RULE_STRING;
		TOKEN_MAP[Terminals.LPAREN] = InternalCoffeeScriptLexer.RULE_LPAREN;
		TOKEN_MAP[Terminals.JS] = InternalCoffeeScriptLexer.RULE_JS;
		TOKEN_MAP[Terminals.REGEX] = InternalCoffeeScriptLexer.RULE_REGEX;
		TOKEN_MAP[Terminals.BOOL] = InternalCoffeeScriptLexer.RULE_BOOL;
		TOKEN_MAP[Terminals.SUPER] = InternalCoffeeScriptLexer.RULE_SUPER;
		TOKEN_MAP[Terminals.THIS] = InternalCoffeeScriptLexer.RULE_THIS;
		TOKEN_MAP[Terminals.HERECOMMENT] = InternalCoffeeScriptLexer.RULE_HERECOMMENT;
		TOKEN_MAP[Terminals.IF] = InternalCoffeeScriptLexer.RULE_IF;
		TOKEN_MAP[Terminals.FUNC_ARROW] = InternalCoffeeScriptLexer.RULE_FUNC_ARROW;
		TOKEN_MAP[Terminals.BOUND_FUNC_ARROW] = InternalCoffeeScriptLexer.RULE_BOUND_FUNC_ARROW;
		TOKEN_MAP[Terminals.MINUS_MINUS] = InternalCoffeeScriptLexer.RULE_MINUS_MINUS;
		TOKEN_MAP[Terminals.PLUS_PLUS] = InternalCoffeeScriptLexer.RULE_PLUS_PLUS;
		TOKEN_MAP[Terminals.SWITCH] = InternalCoffeeScriptLexer.RULE_SWITCH;
		TOKEN_MAP[Terminals.PARAM_START] = InternalCoffeeScriptLexer.RULE_PARAM_START;
		TOKEN_MAP[Terminals.CLASS] = InternalCoffeeScriptLexer.RULE_CLASS;
		TOKEN_MAP[Terminals.TRY] = InternalCoffeeScriptLexer.RULE_TRY;
		TOKEN_MAP[Terminals.STATEMENT] = InternalCoffeeScriptLexer.RULE_STATEMENT;
		TOKEN_MAP[Terminals.RETURN] = InternalCoffeeScriptLexer.RULE_RETURN;
		TOKEN_MAP[Terminals.THROW] = InternalCoffeeScriptLexer.RULE_THROW;
		TOKEN_MAP[Terminals.LOOP] = InternalCoffeeScriptLexer.RULE_LOOP;
		TOKEN_MAP[Terminals.UNARY] = InternalCoffeeScriptLexer.RULE_UNARY;
		TOKEN_MAP[Terminals.QUESTION] = InternalCoffeeScriptLexer.RULE_QUESTION;
		TOKEN_MAP[Terminals.MATH] = InternalCoffeeScriptLexer.RULE_MATH;
		TOKEN_MAP[Terminals.INDENT] = InternalCoffeeScriptLexer.RULE_INDENT;
		TOKEN_MAP[Terminals.SHIFT] = InternalCoffeeScriptLexer.RULE_SHIFT;
		TOKEN_MAP[Terminals.RELATION] = InternalCoffeeScriptLexer.RULE_RELATION;
		TOKEN_MAP[Terminals.COMPARE] = InternalCoffeeScriptLexer.RULE_COMPARE;
		TOKEN_MAP[Terminals.LOGIC] = InternalCoffeeScriptLexer.RULE_LOGIC;
		TOKEN_MAP[Terminals.POST_IF] = InternalCoffeeScriptLexer.RULE_POST_IF;
		TOKEN_MAP[Terminals.OUTDENT] = InternalCoffeeScriptLexer.RULE_OUTDENT;
		TOKEN_MAP[Terminals.CALL_START] = InternalCoffeeScriptLexer.RULE_CALL_START;
		TOKEN_MAP[Terminals.TERMINATOR] = InternalCoffeeScriptLexer.RULE_TERMINATOR;
		TOKEN_MAP[Terminals.INDEX_START] = InternalCoffeeScriptLexer.RULE_INDEX_START;
		TOKEN_MAP[Terminals.INDEX_SOAK] = InternalCoffeeScriptLexer.RULE_INDEX_SOAK;
		TOKEN_MAP[Terminals.INDEX_PROTO] = InternalCoffeeScriptLexer.RULE_INDEX_PROTO;
		TOKEN_MAP[Terminals.COMMA] = InternalCoffeeScriptLexer.RULE_COMMA;
		TOKEN_MAP[Terminals.DOT] = InternalCoffeeScriptLexer.RULE_DOT;
		TOKEN_MAP[Terminals.QUESTION_DOT] = InternalCoffeeScriptLexer.RULE_QUESTION_DOT;
		TOKEN_MAP[Terminals.DOUBLE_COLON] = InternalCoffeeScriptLexer.RULE_DOUBLE_COLON;
		TOKEN_MAP[Terminals.FUNC_EXIST] = InternalCoffeeScriptLexer.RULE_FUNC_EXIST;
		TOKEN_MAP[Terminals.INDEX_END] = InternalCoffeeScriptLexer.RULE_INDEX_END;
		TOKEN_MAP[Terminals.WHEN] = InternalCoffeeScriptLexer.RULE_WHEN;
		TOKEN_MAP[Terminals.RBRACKET] = InternalCoffeeScriptLexer.RULE_RBRACKET;
		TOKEN_MAP[Terminals.ELLIPSIS] = InternalCoffeeScriptLexer.RULE_ELLIPSIS;
		TOKEN_MAP[Terminals.LEADING_WHEN] = InternalCoffeeScriptLexer.RULE_LEADING_WHEN;
		TOKEN_MAP[Terminals.ELSE] = InternalCoffeeScriptLexer.RULE_ELSE;
		TOKEN_MAP[Terminals.EXTENDS] = InternalCoffeeScriptLexer.RULE_EXTENDS;
		TOKEN_MAP[Terminals.DOT_DOT] = InternalCoffeeScriptLexer.RULE_DOT_DOT;
		TOKEN_MAP[Terminals.RPAREN] = InternalCoffeeScriptLexer.RULE_RPAREN;
		TOKEN_MAP[Terminals.EQUAL] = InternalCoffeeScriptLexer.RULE_EQUAL;
		TOKEN_MAP[Terminals.BY] = InternalCoffeeScriptLexer.RULE_BY;
		TOKEN_MAP[Terminals.CALL_END] = InternalCoffeeScriptLexer.RULE_CALL_END;
		TOKEN_MAP[Terminals.FINALLY] = InternalCoffeeScriptLexer.RULE_FINALLY;
		TOKEN_MAP[Terminals.COLON_SLASH] = InternalCoffeeScriptLexer.RULE_COLON_SLASH;
		TOKEN_MAP[Terminals.FORIN] = InternalCoffeeScriptLexer.RULE_FORIN;
		TOKEN_MAP[Terminals.COMPOUND_ASSIGN] = InternalCoffeeScriptLexer.RULE_COMPOUND_ASSIGN;
		TOKEN_MAP[Terminals.PARAM_END] = InternalCoffeeScriptLexer.RULE_PARAM_END;
		TOKEN_MAP[Terminals.RCURLY] = InternalCoffeeScriptLexer.RULE_RCURLY;
		TOKEN_MAP[Terminals.OWN] = InternalCoffeeScriptLexer.RULE_OWN;
		TOKEN_MAP[Terminals.FOROF] = InternalCoffeeScriptLexer.RULE_FOROF;
		TOKEN_MAP[Terminals.COLON] = InternalCoffeeScriptLexer.RULE_COLON;
		TOKEN_MAP[Terminals.CATCH] = InternalCoffeeScriptLexer.RULE_CATCH;
		TOKEN_MAP[Terminals.THEN] = InternalCoffeeScriptLexer.RULE_THEN;
		TOKEN_MAP[Terminals.NEW] = InternalCoffeeScriptLexer.RULE_NEW;
		TOKEN_MAP[Terminals.HIDDEN_COMMENT] = InternalCoffeeScriptLexer.RULE_SL_COMMENT;
	}

	public BeaverToken(Symbol symbol) {
		super(mapId(symbol.getId()));
		if (symbol.value != null)
			setText("" + symbol.value);
		setStartIndex(symbol.getStart());
		int end = -1;
		if (symbol.getStart() == symbol.getEnd()) {
			// Tokens inserted by the rewriter may have zero length
			end = symbol.getEnd() - 1;
		}
		else {
			// XXX: AbstractInternalAntlrParser.createLeafNode will add 1 to length
			end = symbol.getEnd() - 1;
		}
		setStopIndex(end);
		// TODO: Aptana scanner doesn't seem to keep track of lines and position within a line
	}
	
	/**
	 * Map an Aptana symbol id to an Antlr token id.
	 * XXX: if <var>aptanaId</var> is {@link CoffeeScanner#UNKNOWN}, it gets swallowed
	 * @return a mapping token id, or {@link Token#INVALID_TOKEN_TYPE} if no mapping was found
	 */
	public static int mapId(int aptanaId) {
		if (aptanaId < TOKEN_MAP.length && aptanaId >= 0)
			return TOKEN_MAP[aptanaId];
		else
			return INVALID_TOKEN_TYPE;
	}
	
	/**
	 * String representation of a {@link Symbol}, because {@link CoffeeSymbol#coffeeToken()} 
	 * does not handle special tokens of negative value.
	 */
	public static String stringify(CoffeeSymbol symbol) {
		if (symbol == null) {
			return "null";
		}
		else if (symbol.getId() >= 0) {
			return symbol.coffeeToken();
		}
		else {
			String val = "" + symbol.getValue();
			return "[" + symbol.getId() + ", '" + val.replace("\n", "NL") + "']";
		}
	}
	
}