package csep.example.cake.ui.syntaxcoloring;

import csep.example.cake.parser.TokenTypeMapper;
import csep.example.cake.parser.antlr.internal.InternalCakefileLexer;
import csep.ui.syntaxcoloring.AntlrTokenToAttributeIdMapper;
import csep.ui.syntaxcoloring.LexicalHighlightingConfiguration;

public class CustomAntlrTokenToAttributeIdMapper extends
		AntlrTokenToAttributeIdMapper {

	@Override
	public String calculateId(String tokenName, int tokenType) {
		if (InternalCakefileLexer.RULE_TASK == tokenType)
			return LexicalHighlightingConfiguration.KEYWORD_ID;
		int coffeeToken = TokenTypeMapper.cakefileToCoffee(tokenType);
		return super.calculateId(tokenName, coffeeToken);
	}
}
