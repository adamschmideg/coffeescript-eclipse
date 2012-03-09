package csep.example.cake.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

import csep.example.cake.parser.antlr.internal.InternalCakefileLexer;
import csep.parser.Lexer;

public class CustomLexer extends Lexer {
	
	public CustomLexer(CharStream in) {
		super(in);
	}

	@Override
	public Token nextToken() {
		Token token = super.nextToken();
		token.setType(TokenTypeMapper.coffeeToCakefile(token.getType()));
		if (token.getType() == InternalCakefileLexer.RULE_IDENTIFIER) {
			if ("task".equals(token.getText())) {
				token.setType(InternalCakefileLexer.RULE_TASK);
			}
		}
		return token;
	}
}
