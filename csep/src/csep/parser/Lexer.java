package csep.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

import beaver.Symbol;

import com.aptana.editor.coffee.parsing.Terminals;
import com.aptana.editor.coffee.parsing.lexer.CoffeeScanner;
import com.aptana.editor.coffee.parsing.lexer.CoffeeSymbol;

//public class Lexer extends org.eclipse.xtext.parser.antlr.Lexer {
public class Lexer extends csep.parser.antlr.internal.InternalCoffeeScriptLexer {
	private CoffeeScanner aptanaScanner;

	public Lexer(CharStream in) {
		super(in);
		aptanaScanner = new CoffeeScanner();
		String content = in.substring(0, in.size() - 1);
		aptanaScanner.setSource(content);
	}

	public Lexer(String str) {
		this(new ANTLRStringStream(str));
	}

	@Override
	public Token nextToken() {
		Token token = null;
		try {
			CoffeeSymbol symbol = aptanaScanner.nextToken();
			if (symbol.getId() == Terminals.EOF) {
				token = new CommonToken(CommonToken.EOF);
			}
			else {
				String text = "" + symbol.getValue();
				token = new CommonToken(symbol.getId(), text);
				int packedStart = symbol.getStart();
				int startLine = Symbol.getLine(packedStart);
				int startPosition = Symbol.getColumn(packedStart);
				token.setLine(startLine);
				token.setCharPositionInLine(startPosition);
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
			token = new CommonToken(CommonToken.INVALID_TOKEN); 
		}
		System.out.println("token: " + token + ", " + token.getType() + ": " + token.getText());
		return token;
	}

	public List<Token> tokenize() throws Exception {
		List<Token> symbols = new ArrayList<Token>();
		while (true) {
			Token token = nextToken();
			if (Token.EOF == token.getType()) {
				break;
			} else {
				symbols.add(token);
			}
		}
		return symbols;
	}

}
