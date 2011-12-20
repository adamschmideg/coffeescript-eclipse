package csep.ui.syntaxcoloring;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;

public class AntlrTokenToAttributeIdMapper extends
		DefaultAntlrTokenToAttributeIdMapper {

	@Override
	public String calculateId(String tokenName, int tokenType) {
		String id = super.calculateId(tokenName, tokenType);
		if (LexicalHighlightingConfiguration.DEFAULT_ID.equals(id)) {
			if ("RULE_REGEX".equals(tokenName)) {
				return LexicalHighlightingConfiguration.REGEX_ID;
			}
		}
		return id;
	}
}
