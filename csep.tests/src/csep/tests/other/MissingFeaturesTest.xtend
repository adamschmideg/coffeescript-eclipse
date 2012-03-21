package csep.tests.other

import csep.tests.ParserTestBase
import org.junit.Test

class MissingFeaturesTest extends ParserTestBase {
	
	@Test
	def void testPostIncrement() {
		shouldBeOk('a++ + b')
	}
	
	@Test
	def void testPropertyAssignment() {
		shouldBeOk('a.b.c = 1')
		// workaround instead
		ok('''
		  temp = a.b
		  temp.c = 1
		''')
		shouldBeOk('f().a = 2')
		shouldBeOk('(1 + 2).len = 3')		
	}
	
	@Test
	def void testForComprehensionWithBareRange() {
		// It parses, but into a wrong AST
		shouldBeOk('''
		  cnt = 0
		  cnt += 1 for [0..5]
		''')
		// workaround
		ok('''
		  cnt = 0
		  for i in [0..5]
		    cnt += 1
		''')
	}
	
	@Test
	def void testForComprehensionWithCompoundLoopVariable() {
		shouldBeOk('i+j for [i, j] in matrix')
		// workaround
		ok('''
		  for elem in matrix
		    [i, j] = elem
		''')
	}
	
	@Test
	def void testLoopWithExpression() {
		shouldBeOk('a = loop readInfo()')
		// workarounds
		ok('''
		  a = loop
		    readInfo()
		''')
		ok('a = readInfo() while true')
	}
	
	@Test
	def void testClass() {
		// deeply nested class name
		shouldBeOk('class a.b.c')
		// class declaration as expression
		shouldBeOk('c = class extends Foo') 
		// class declaration as expression
		shouldBeOk('class A extends B extends C')
		// class name as property
		shouldBeOk('class "str".length')
		// class extends any expression
		shouldBeOk('class A extends 3')
	}
	
	/**
	 * Lambda variable outside of its scoping shouldn't be resolved,
	 * thus it should give a warning
	 */
	@Test
	def void testLambdaScoping() {
		okNoWarning('''
		  fun = (x) ->
		    2 * x
		  x
		''')		
	}

	/**
	 * @see {NodesCoffeeTest.testOp}
	 */
	@Test
	def void testKeywordAsFeatureName() {
		shouldBeOk('foo.do = 1')
		shouldBeOk('bar.class = "Anything"')
	}
	
	/**
	 * @see {NodesCoffeeTest.testObj}
	 */
	@Test
	def void testCompoundAssignable() {
		shouldBeOk('(a or b).prop = 3')
	}
}