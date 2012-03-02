package csep.ui.syntaxcoloring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;

import csep.parser.antlr.internal.InternalCoffeeScriptLexer;

@SuppressWarnings("restriction")
public class AntlrTokenToAttributeIdMapper extends
		DefaultAntlrTokenToAttributeIdMapper {

	Set<Integer> KEYWORD_TOKENS = new HashSet<Integer>(
			Arrays.asList(new Integer[] { 
					InternalCoffeeScriptLexer.RULE_BOOL,
					InternalCoffeeScriptLexer.RULE_BY,
					InternalCoffeeScriptLexer.RULE_CATCH,
					InternalCoffeeScriptLexer.RULE_CLASS,
					InternalCoffeeScriptLexer.RULE_ELSE,
					InternalCoffeeScriptLexer.RULE_EXTENDS,
					InternalCoffeeScriptLexer.RULE_FINALLY,
					InternalCoffeeScriptLexer.RULE_FOR,
					InternalCoffeeScriptLexer.RULE_FORIN,
					InternalCoffeeScriptLexer.RULE_FOROF,
					InternalCoffeeScriptLexer.RULE_IF,
					InternalCoffeeScriptLexer.RULE_LOOP,
					InternalCoffeeScriptLexer.RULE_POST_IF,
					InternalCoffeeScriptLexer.RULE_RELATION,
					InternalCoffeeScriptLexer.RULE_RETURN,
					InternalCoffeeScriptLexer.RULE_STATEMENT,
					InternalCoffeeScriptLexer.RULE_SUPER,
					InternalCoffeeScriptLexer.RULE_SWITCH,
					InternalCoffeeScriptLexer.RULE_THIS,
					InternalCoffeeScriptLexer.RULE_THROW,
					InternalCoffeeScriptLexer.RULE_TRY,
					InternalCoffeeScriptLexer.RULE_UNTIL,
					InternalCoffeeScriptLexer.RULE_WHEN,
					// Not keywords in the coffeescript spec
					InternalCoffeeScriptLexer.RULE_COMPARE_WORD,
					InternalCoffeeScriptLexer.RULE_LOGIC_WORD,
					InternalCoffeeScriptLexer.RULE_THEN,
					InternalCoffeeScriptLexer.RULE_UNARY_WORD,
			}));
			
	@Override
	public String calculateId(String tokenName, int tokenType) {
		if (KEYWORD_TOKENS.contains(tokenType))
			return LexicalHighlightingConfiguration.KEYWORD_ID;
		if (InternalCoffeeScriptLexer.RULE_NUMBER == tokenType)
			return LexicalHighlightingConfiguration.NUMBER_ID;
		if (InternalCoffeeScriptLexer.RULE_STRING == tokenType)
			return LexicalHighlightingConfiguration.STRING_ID;
		if (InternalCoffeeScriptLexer.RULE_REGEX == tokenType)
			return LexicalHighlightingConfiguration.REGEX_ID;
		if (InternalCoffeeScriptLexer.RULE_SL_COMMENT == tokenType
				|| InternalCoffeeScriptLexer.RULE_ML_COMMENT == tokenType)
			return LexicalHighlightingConfiguration.COMMENT_ID;
		return LexicalHighlightingConfiguration.DEFAULT_ID;
	}
}
