package csep.tests.basic

import csep.tests.ParserTestBase
import org.junit.Test

class LambdaTest extends ParserTestBase {
	
	@Test
	def void testBasic() {
		ok('-> 2')
		ok('() -> 3')
		ok('(x) -> 2 * x')
		ok('(x, y) -> x * y')
		ok('(x=1, y) -> x * y')
	}
	
	@Test
	def void testMultiline() {
		ok('''
		  fun = ->
		    3''')
		ok('''
		  fun = (x) ->
		    print x
		    2 * x
		''')
	}
}