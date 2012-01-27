package csep.tests.other

import csep.parser.Helper
import csep.parser.Lexer
import csep.tests.ParserTestBase
import org.junit.Test
import java.util.ArrayList
import junit.framework.Assert
import org.antlr.runtime.CommonToken

class LexerTest extends ParserTestBase {
	
	@Test
	def void testBasic() {
		check('''
			number = 42
			answer=42
		''',
		'''
IDENTIFIER:number
EQUAL:=
NUMBER:42
TERMINATOR:
IDENTIFIER:answer
EQUAL:=
NUMBER:42
TERMINATOR:
''')
	}

    @Test
    def void testIncomplete() {
    	checkCount('', 0)
    	checkCount('if ', 2)
		checkCount('if a ', 3)
	}
	
	@Test
	def void testMoreOutdents() {
		checkCount('''
		  a = 1
		    b
		  c
		  ''', 9)
    }
    
    @Test
    def void testErrorInRewriter() {
    	check('''
    	  before = 0
    	  tooManyParens = )
    	  unreached = 42
    	''', '''
    	  IDENTIFIER:before
    	  EQUAL:=
    	  NUMBER:0
    	  TERMINATOR:
    	  IDENTIFIER:tooManyParens
    	  EQUAL:=
    	''')

    	check('''
    	  before = 0
    	  unclosedParen = (
    	  unreached = 42
    	''', '''
    	  IDENTIFIER:before
    	  EQUAL:=
    	  NUMBER:0
    	  TERMINATOR:
    	  IDENTIFIER:unclosedParen
    	  EQUAL:=
    	''')
    }
    
    @Test
    def void testErrorInScanner() {
    	check('''
    	  before = 0
    	  case = 1
    	  unreached = 42
    	''', '''
    	  IDENTIFIER:before
    	  EQUAL:=
    	  NUMBER:0
    	  TERMINATOR:
    	''')
    	check('unclosedCurlyBrace = "before #{ interpolation "', '''
    	  IDENTIFIER:unclosedCurlyBrace
    	  EQUAL:=
    	''')
    	check('unfinishedString = "before #{ interpolation', '''
    	  IDENTIFIER:unfinishedString
    	  EQUAL:=
    	''')
    }
    
    @Test
    def void testPostfixIf() {
    	check('num = 2 if even', '''
IDENTIFIER:num
EQUAL:=
NUMBER:2
POST_IF:if
IDENTIFIER:even
TERMINATOR:
''')
    }
    
    @Test
    def void testHerecomment() {
    	check('''
before
###
Comment
###
after''', '''
IDENTIFIER:before
TERMINATOR:
HERECOMMENT:Comment

TERMINATOR:
IDENTIFIER:after
TERMINATOR:
''')
    }
    
    @Test
    def void testComment() {
    	check('''
    	  	# remark
    	  	a = 1
    		''', '''
			IDENTIFIER:a
			EQUAL:=
			NUMBER:1
			TERMINATOR:    	
    		''')
    	check('''
    	  	#### Important remark, not herecomment
    	  	a = 1
    		''', '''
			IDENTIFIER:a
			EQUAL:=
			NUMBER:1
			TERMINATOR:    	
    		''')    		
    }
    
    @Test
    def void testStringInterpolation() {
    	check('me = "I am #{name}"','''
IDENTIFIER:me
EQUAL:=
LPAREN:(
STRING:"I am "
PLUS:+
IDENTIFIER:name
RPAREN:)
TERMINATOR:
    	''')	
    }
    
    @Test
    def void testFirstLineIndented() {
    	// Check if token offsets don't fail with any assertions in the Lexer
    	ok('  a = 3')	
    	ok('a = 3  ')
    }

    @Test
    def void testTokenPositionsInStringInterpolation() {
    	val input =
    	//0123456789012345678901234567890
    	 'me = "before #{ref}"'
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
    	val expected = '''
    	IDENTIFIER:me:0:1
    	EQUAL:=:3:3
    	LPAREN:(:5:5
    	STRING:"before ":5:12
    	PLUS:+:13:13
    	IDENTIFIER:ref:15:17
    	RPAREN:):18:18
    	TERMINATOR::20:20
    	'''  	
    	Assert::assertEquals(expected.toString, got.join('\n'))
    }

    
	def void check(CharSequence input, CharSequence expectedStr) {
		val lexer = new Lexer(input)
		val tokens = lexer.tokenizeToStrings()
		val tokensStr = tokens.join("\n")
		Assert::assertEquals(expectedStr.toString().trim(), tokensStr)
	}
	
	def void checkCount(CharSequence input, int expectLength) {
		val lexer = new Lexer(input)
		val tokens = lexer.tokenizeToStrings()
		Assert::assertEquals(expectLength, tokens.size())
	}
	
}