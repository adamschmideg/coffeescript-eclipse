package csep.tests

import org.junit.Test

class ArrayTest extends ParserTestBase {
	
	@Test
	def void testBasic() {
		ok('[1, 2]')
		ok('a = [1, 2]')
		ok('a = []')
		ok('a = [1, 2].len')
	}
	
	@Test
	def void testLines() {
		ok('[1, 2,]')
		ok('[1, 2...]')
		error('[1,,]')
		shouldBeOk('''
		a = [
		  1
		  2
		]''')
		shouldBeOk('''
		b = [
		  3, 4,
		]
		''')
		shouldBeOk('''
		c = [
		  4...,
		  5,6
		    7
		]''')
	}
	
}