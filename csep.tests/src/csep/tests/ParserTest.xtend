package csep.tests

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class ParserTest extends ParserTestBase {
	
	@Test
	def void testSimple() {
		checkLine('4 * 3 - 2', '''
AdditiveOp-
  left: MathOp*
    left: NumberLiteral
      value: 4
    right: NumberLiteral
      value: 3
  right: NumberLiteral
    value: 2''')
	}
	
	@Test
	def void testAddVariables() {
		checkLine('foo + bar', '''
AdditiveOp+
  left: Id
    name: foo
  right: Id
    name: bar''')
	}
	
	@Test
	def void testCompare() {
		checkLine('(2 == 3) + (2 < 4)', '''
AdditiveOp+
  left: CompareOp==
    left: NumberLiteral
      value: 2
    right: NumberLiteral
      value: 3
  right: CompareOp<
    left: NumberLiteral
      value: 2
    right: NumberLiteral
      value: 4''')
	}	
	
	@Test
	def void testAssign() {
		checkLine('foo = bar + 1', '''
Assign
  left: Id
    name: foo
  right: AdditiveOp+
    left: Id
      name: bar
    right: NumberLiteral
      value: 1''')
	}
}
