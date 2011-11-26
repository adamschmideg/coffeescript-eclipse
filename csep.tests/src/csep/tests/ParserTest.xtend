package csep.tests

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class ParserTest extends ParserTestBase {
	
	@Test
	def void testSimple() {
		check('4 * 3 - 2', '''
AdditiveOp
  left: MathOp
    left: NumberLiteral
      value: 4
    operator: *
    right: NumberLiteral
      value: 3
  operator: -
  right: NumberLiteral
    value: 2''')
	}
	
	@Test
	def void testCompare() {
		check('(2 == 3) + (2 < 4)', '''
AdditiveOp
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
      value: 4''')
	}	
}
