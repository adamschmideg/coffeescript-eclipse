package csep.tests.basic

import csep.tests.ParserTestBase
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
		ok('''
indent = [1
   2]''')
		ok('''
		[
		  1
		  2
		]''')
		ok('''
		[
		  3, 4,
		]
		''')
		ok('''
		c = [
		  4...,
		  5,6
		    7
		]''')
	}
	
	@Test
	def void testRangeVsArray() {
		ok('a = [1..3]')
	}
}