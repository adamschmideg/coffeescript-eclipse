package csep.tests

import csep.parser.Lexer
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class LexerTest {
	
	@Test
	def void testSimple() {
		val lexer = new Lexer('''
			number=42
			answer=42
		''')
		val tokens = lexer.tokenize()
		//println("tokens: " + tokens)
	}
	
}