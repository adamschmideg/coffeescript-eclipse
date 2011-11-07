package csep.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

//public class Lexer extends org.eclipse.xtext.parser.antlr.Lexer {
public class Lexer extends csep.parser.antlr.internal.InternalCoffeeScriptLexer {

	public Lexer(CharStream in) {
		super(in);
	}

	@Override
	public void mTokens() throws RecognitionException {	
		super.mTokens();
	}
	
	@Override
	public Token nextToken() {
		Token token = super.nextToken();
		System.out.println("token: " + token);
		return token;
	}

}
