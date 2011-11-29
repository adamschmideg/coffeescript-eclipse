package csep.tests

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class ControlFlowTest extends ParserTestBase {
	
	@Test
	def void testIf() {
		checkLine('''
		if a
		  1''', '''
IfBlock
  trueBlock: Body
    lines:
      NumberLiteral
        value: 1
  condition: Id
    name: a
		  ''')
	}

	@Test
	def void testIf_Else() {
		checkLine('''
		if a
		  1
		else
		  2''', '''
IfBlock
  trueBlock: Body
    lines:
      NumberLiteral
        value: 1
  condition: Id
    name: a
  falseBlock: Body
    lines:
      NumberLiteral
        value: 2		  
		  ''')
	}

	@Test
	def void testIf_ElseIf() {
		checkLine('''
		if a
		  1
		else if b
		  2''', '''
IfBlock
  trueBlock: Body
    lines:
      NumberLiteral
        value: 1
  condition: Id
    name: a
  elseIfBlocks:
    ElseIfBlock
      trueBlock: Body
        lines:
          NumberLiteral
            value: 2
      condition: Id
        name: b
		  ''')
	}

	@Test
	def void testIf_ElseIf_Else() {
		checkLine('''
		if a
		  1
		else if b
		  2
		else
		  3''', '''
IfBlock
  trueBlock: Body
    lines:
      NumberLiteral
        value: 1
  condition: Id
    name: a
  elseIfBlocks:
    ElseIfBlock
      trueBlock: Body
        lines:
          NumberLiteral
            value: 2
      condition: Id
        name: b
  falseBlock: Body
    lines:
      NumberLiteral
        value: 3
		  ''')
	}

}