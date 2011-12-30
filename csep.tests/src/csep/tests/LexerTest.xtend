package csep.tests

import csep.parser.Lexer
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import junit.framework.Assert

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class LexerTest {
	
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