package csep.example.cake.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import csep.example.cake.parser.antlr.CakefileParser;
import csep.parser.FirstCommentIncludingXtextTokenStream;

public class CustomCakefileParser extends CakefileParser {
	  @Override
	  protected TokenSource createLexer(CharStream stream) {
	    return new CustomLexer(stream);
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
