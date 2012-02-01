package csep.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import csep.parser.antlr.CoffeeScriptParser;

public class Parser extends CoffeeScriptParser {
	@Override
	protected TokenSource createLexer(CharStream stream) {
		Lexer lexer = new Lexer(stream);
		return lexer;
	}
	
	@Override
	protected XtextTokenStream createTokenStream(TokenSource tokenSource) {
		return new FirstCommentIncludingXtextTokenStream(tokenSource, getTokenDefProvider());
	}
	
	/**
	 * XXX: true, otherwise a partialParser will be used which generates wrong offsets
	 */
	@Override
	protected boolean isReparseSupported() {
		return false;
	}
}
