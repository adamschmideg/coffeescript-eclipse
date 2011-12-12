package csep.tests

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
		shouldBeOk('''
		  cnt = 0
		  cnt += 1 for [1..3]
		''')
	}
	
	@Test
	def void testForComprehensionWithCompoundLoopVariable() {
		shouldBeOk('i+j for [i, j] in matrix')
	}
}