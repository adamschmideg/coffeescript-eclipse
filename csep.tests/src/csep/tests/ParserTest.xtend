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
		val expected = '''AdditiveOp
  left: MathOp
    left: NumberLiteral
      value: 4
    operator: *
    right: NumberLiteral
      value: 3
  operator: -
  right: NumberLiteral
    value: 2
'''.toString()
		val got = Helper::stringify(root)
		println("Got:\n" + got)
		Assert::assertEquals(expected, got)	
	}
	
	@Test
	def void testCompare() {
		val root = parser.parse('(2 == 3) + (2 < 4)')
		val expected = '''AdditiveOp
  left: CompareOp
    left: NumberLiteral
      value: 2
    operator: ==
    right: NumberLiteral
      value: 3
  operator: +
  right: CompareOp
    left: NumberLiteral
      value: 2
    operator: <
    right: NumberLiteral
      value: 4
'''.toString()
		val got = Helper::stringify(root)
		println("Got:\n" + got)
		Assert::assertEquals(expected, got)	
	}
	
}
