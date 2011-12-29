package csep.parser;

import java.io.IOException;
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
		Exception problem = null;
		try {
			symbol = aptanaScanner.nextToken();
			if (symbol == null) {
				// XXX: why do we get a null symbol?
				token = CommonToken.INVALID_TOKEN;
			} else if (symbol.getId() == Terminals.EOF) {
				token = new CommonToken(CommonToken.EOF);
			} else {
				token = new BeaverToken(symbol);
			}
		} catch (IOException e) {
			problem = e;
		} catch (beaver.Scanner.Exception e) {
			problem = e;
		} catch (Exception e) {
			problem = e;
		}
		// Common exception handling
		if (problem != null) {
			logger.warn("Cannot get next token ", problem);
			token = new CommonToken(Token.INVALID_TOKEN_TYPE, problem.getLocalizedMessage());
		}
		// Token superToken = super.nextToken();
		logger.debug("token: " + token);
		//return super.nextToken();
		return token;
	}

	/**
	 * Read the whole input and tokenize it
	 * 
	 * @return a list of tokens
	 * @throws Exception
	 */
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

	/**
	 * Tokenize the whole input, skipping hidden terminals
	 * 
	 * @return a simplified string representation of the token stream in the
	 *         form of "tokenName:text"
	 * @throws Exception
	 */
	public List<String> tokenizeToStrings() throws Exception {
		List<String> strings = new ArrayList<String>();
		for (Token t : tokenize()) {
			if (t.getChannel() == Token.DEFAULT_CHANNEL) {
				strings.add(Helper.getNameAndText(t));
			}
		}
		return strings;
	}
}
