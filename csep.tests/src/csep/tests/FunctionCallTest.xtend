package csep.tests

import org.junit.Test

class FunctionCallTest extends ParserTestBase {
	
	@Test
	def void testProperty() {
		ok('foo.bar')
		ok('foo.bar.baz')
		ok('"text".len')
		ok('a.size + b.getSize()')
	}
	
	@Test
	def void testCallSimple() {
		ok('foo()')
		ok('list.pop()')
		ok('"text".trim()')
		ok('list.pop().remove()')
	}
	
	@Test
	def void testAssignProperty() {
		shouldBeOk('a.b.c = 1')
		shouldBeOk('f().a = 2')
		shouldBeOk('(1 + 2).len = 3')
	}
}