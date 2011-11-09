package csep.parser;

import org.antlr.runtime.CommonToken;

import beaver.Symbol;

/**
 * A token which is instantiated with a beaver.Symbol
 * @author Adam Schmideg <adam@schmideg.net>
 *
 */
public class BeaverToken extends CommonToken {

	public BeaverToken(Symbol symbol) {
		super(symbol.getId());
		if (symbol.value != null)
			setText("" + symbol.value);
		int packedStart = symbol.getStart();
		int startLine = Symbol.getLine(packedStart) + 1;
		int startPosition = Symbol.getColumn(packedStart);
		int packedStop = symbol.getEnd();
		int stopPosition = Symbol.getColumn(packedStop) - 1;
		setStartIndex(startPosition);
		setStopIndex(stopPosition);
		// FIXME: Aptana scanner doesn't seem to keep track of lines and position within a line
		setLine(startLine);
		setCharPositionInLine(startPosition);
	}
}
