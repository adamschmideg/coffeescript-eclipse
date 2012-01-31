package csep.tests.lexer

import csep.parser.Lexer
import csep.tests.ParserTestBase
import org.junit.Test
import junit.framework.Assert

class TokenTest extends ParserTestBase {
	
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
