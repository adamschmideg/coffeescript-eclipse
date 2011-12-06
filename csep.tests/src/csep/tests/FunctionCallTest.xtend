package csep.tests

import org.junit.Test

class FunctionCallTest extends ParserTestBase {
	
	@Test
	def void testProperty() {
		ok('foo.bar')
		ok('"text".len')
		shouldBeOk('a.size + b.getSize()')
	}
	
	@Test
	def void testCallSimple() {
		ok('foo()')
		shouldBeOk('list.pop()')
		shouldBeOk('"text".trim()')
		shouldBeOk('list.pop().remove()')
	}
}