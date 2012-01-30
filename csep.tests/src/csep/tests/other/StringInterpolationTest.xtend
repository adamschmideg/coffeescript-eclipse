package csep.tests.other

import csep.parser.Helper
import csep.parser.Lexer
import csep.tests.ParserTestBase
import org.junit.Test
import java.util.ArrayList
import junit.framework.Assert
import org.antlr.runtime.CommonToken

class StringInterpolationTest extends ParserTestBase {
	
    @Test
    def void test_simple() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'"before #{ref}"', '''
	    	LPAREN:(:0:-1
	    	STRING:"before ":0:8
	    	PLUS:+:10:9
	    	IDENTIFIER:ref:10:12
	    	RPAREN:):13:12
	    	TERMINATOR::15:14
    	''')
    }

    @Test
    def void test_simpleWithStringAfter() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'"before #{ref} after"', '''
	    	LPAREN:(:0:-1
	    	STRING:"before ":0:8
	    	PLUS:+:10:9
	    	IDENTIFIER:ref:10:12
	    	PLUS:+:13:12
	    	STRING:" after":13:20
	    	RPAREN:):19:18
	    	TERMINATOR::21:20
    	''')    	
    }
    
    @Test
    def void test_moreExpressions() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'"before #{ref} and #{other}"', '''
	    	LPAREN:(:0:-1
	    	STRING:"before ":0:8
	    	PLUS:+:10:9
	    	IDENTIFIER:ref:10:12
	    	PLUS:+:13:12
	    	STRING:" and ":13:19
	    	PLUS:+:21:20
	    	IDENTIFIER:other:21:25
	    	RPAREN:):26:25
	    	TERMINATOR::28:27
    	''')    	
    }

    @Test
    def void test_offset() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'me = "before #{ref}"', '''
	    	IDENTIFIER:me:0:1
	    	EQUAL:=:3:3
	    	LPAREN:(:5:4
	    	STRING:"before ":5:13
	    	PLUS:+:15:14
	    	IDENTIFIER:ref:15:17
	    	RPAREN:):18:17
	    	TERMINATOR::20:19
    	''')
    }

    @Test
    def void test_offsetWithStringAfter() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'me = "before #{ref} after"', '''
	    	IDENTIFIER:me:0:1
	    	EQUAL:=:3:3
	    	LPAREN:(:5:4
	    	STRING:"before ":5:13
	    	PLUS:+:15:14
	    	IDENTIFIER:ref:15:17
	    	PLUS:+:18:17
	    	STRING:" after":18:25
	    	RPAREN:):24:23
	    	TERMINATOR::26:25
    	''')
    }

    @Test
    def void test_spaceAroundExpression() {
    	checkTokenPositions(
    	   //0123456789012345678901234567890
    		'"before #{ ref }"', '''
	    	LPAREN:(:0:-1
	    	STRING:"before ":0:8
	    	PLUS:+:10:9
	    	LPAREN:(:10:9
	    	INDENT:1:10:9
	    	IDENTIFIER:ref:11:13
	    	OUTDENT:1:14:13
	    	RPAREN:):13:12
	    	TERMINATOR::15:14
    	''')
    }

    def void checkTokenPositions(CharSequence input, CharSequence expected) {
    	val lexer = new Lexer(input)
    	val tokens = lexer.tokenize()
    	// The following line silently fails and no java file gets generated in xtend-gen
    	// val starts = tokens.map(t | t.startIndex)
    	val got = new ArrayList<String>()
    	for (t: tokens) {
    		if (t.channel == CommonToken::DEFAULT_CHANNEL) {
    			val s = Helper::getNameAndText(t) + ":" + t.startIndex + ":" + t.stopIndex
    			got.add(s)
    		}
    	}
    	Assert::assertEquals(expected.toString.trim, got.join('\n'))    	
    }
}
