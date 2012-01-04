package csep.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

/**
 * String representation includes starting text that would be tokenized into the hidden channel.
 * @author adam
 *
 */
public class FirstCommentIncludingXtextTokenStream extends XtextTokenStream {

	public FirstCommentIncludingXtextTokenStream(TokenSource tokenSource,
			ITokenDefProvider tokenDefProvider) {
		super(tokenSource, tokenDefProvider);
	}

	@Override
	public String toString() {
		if ( p == -1 ) {
			fillBuffer();
		}	
		if (tokenSource instanceof Lexer) {
			if (tokens.isEmpty())
				return "";
			Token lastToken = (Token) tokens.get(tokens.size() - 1);
			if (lastToken instanceof CommonToken) {
				CommonToken commonStop = (CommonToken) lastToken;
				CharStream charStream = ((Lexer) tokenSource).getCharStream();
				String result = charStream.substring(0, commonStop.getStopIndex());
				return result;
			}
		}
		return super.toString();
	}
}
