package csep.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import com.google.inject.Inject
import org.junit.Test
import org.eclipse.xtext.junit4.util.ParseHelper
import junit.framework.Assert
import csep.coffeeScript.Root
import csep.coffeeScript.Assign
import java.util.*
import csep.parser.Lexer
import csep.parser.Helper

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class ParserTest {
	
	@Inject
	ParseHelper<Root> parser
	
	@Test
	def void testSimple() {
		val root = parser.parse('4 * 3 - 2')
		val expected = '''AdditionImpl
  left: MultiplicationImpl
    left: NumberLiteralImpl
      value: 4
    right: NumberLiteralImpl
      value: 3
  right: NumberLiteralImpl
    value: 2
'''.toString()
		val got = Helper::stringify(root)
		Assert::assertEquals(expected, got)	
	}
	
}
