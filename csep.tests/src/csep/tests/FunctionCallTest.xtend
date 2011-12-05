package csep.tests

import org.junit.Test

class FunctionCallTest extends ParserTestBase {
	
	@Test
	def void testProperty() {
		ok('foo.bar')
		shouldBeOk('"text".len')
		ok('a.size + b.getSize()')
	}
	
	@Test
	def void testCallSimple() {
		shouldBeOk('foo()')
		ok('list.pop()')
		shouldBeOk('"text".trim()')
		ok('list.pop().remove()')
	}
}