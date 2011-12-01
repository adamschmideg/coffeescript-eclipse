package csep.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

import com.aptana.editor.coffee.parsing.Terminals;
import com.aptana.editor.coffee.parsing.lexer.CoffeeScanner;
import com.aptana.editor.coffee.parsing.lexer.CoffeeSymbol;

public class Lexer extends csep.parser.antlr.internal.InternalCoffeeScriptLexer {
	private final static Logger logger = Logger.getLogger(Lexer.class);
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
	
	public Lexer(CharSequence str) {
		this(str.toString());
	}
	
	@Override
	public Token nextToken() {
		Token token = null;
		CoffeeSymbol symbol = null;
		try {
			symbol = aptanaScanner.nextToken();
			if (symbol == null) {
				// XXX: why do we get a null symbol?
				token = CommonToken.INVALID_TOKEN;
			}
			else if (symbol.getId() == Terminals.EOF) {
				token = new CommonToken(CommonToken.EOF);
			}
			else {
				token = new BeaverToken(symbol);
			} 
	    }
		catch (Exception e) {
			logger.warn("Cannot get next token " + BeaverToken.stringify(symbol));
			token = CommonToken.INVALID_TOKEN; 
		}
		//Token superToken = super.nextToken();
		logger.debug("token: " + token);
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
