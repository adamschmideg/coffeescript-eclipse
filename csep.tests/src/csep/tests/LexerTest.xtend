package csep.tests

import csep.parser.Lexer
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class LexerTest {
	
	@Test
	def void testBasic() {
		check('''
			number=42
			answer=42
		''')
	}

    @Test
    def void testInvalidToken() {
    	check('')
    	check('if ')
		check('if a ')
		check('''
		  a = 1
		    b
		   c
		  d''')
    }
    
	def void check(CharSequence input) {
		val lexer = new Lexer(input)
		val tokens = lexer.tokenize()
		println(tokens)
	}
	
}