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
  left: Body
    lines:
      CompareOp==
        left: NumberLiteral
          value: 2
        right: NumberLiteral
          value: 3
  right: Body
    lines:
      CompareOp<
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
	
	@Test
	def void testBlockAssign() {
	  checkLine('''
	  a =
	    42''', '''
Assign
  left: Id
    name: a
  right: NumberLiteral
    value: 42''')
	}
	
	@Test
	def void testWrongAssignment() {
		// TODO: this should fail, not just parse partially
		checkLine('1 = 2', '''
NumberLiteral
  value: 1''')
	}
	
	@Test
	def void testMoreLines() {
		check('''
		a = 3
		b = 4
		''', '''
Body
  lines:
    Assign
      left: Id
        name: a
      right: NumberLiteral
        value: 3
    Assign
      left: Id
        name: b
      right: NumberLiteral
        value: 4		
		''')
	}
}
