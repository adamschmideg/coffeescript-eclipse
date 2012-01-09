package csep.tests.basic

import csep.tests.ParserTestBase
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
		ok('a.b = 1')
	}
	
	@Test
	def void testParameters() {
		ok('fun(2)')
		ok('fun(2,3)')
		ok('fun 4')
		ok('print "Hi, " + @name')
		ok('print @count + " pieces"')
		error('fun 4 5')
		ok('fun 4,5')
		ok('''
		  fun(
		    6
		    7
		  )''')
		ok('fun(named=42, 9)')
	}
}