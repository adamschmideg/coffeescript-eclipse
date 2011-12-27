package csep.tests.parser

import junit.framework.Assert
import csep.parser.Helper
import org.junit.Test

class HelperTest {
	
	@Test
	def void testAssignmentBlock() {
		block('a =')
		block('  a =')
		block("\ta =")
		block('a = ')
		block('a=')
		block('a or=')
	}
	
	@Test
	def void testConditionalBlock() {
	 	block('if a')
	 	block('unless a')
	 	block('else')
	 	block('else if num == 42')
	 	noBlock('if zero then 0 else 1')
	 	noBlock('a = 0 if zero')
	 	noBlock('ifValid()')
	 	block('switch day')
	 	block('when "Monday"')
	 	noBlock('when "Sunday" then sleep()')
	}
	
	@Test
	def void testOtherBlock() {
		block('for a in [1..10]')
		noBlock('a = for a in [1..10]') // FIXME: next line should be indented
		block('fun = (x) ->')
		block('while x > 0')
		block('try')
		block('catch error')
		block('finally')
		block('class Foo')
		block('class Foo extends Bar')
		block('key:')
	}
	
	def void block(String line) {
		Assert::assertTrue(Helper::isBlockContainer(line))
	}

	def void noBlock(String line) {
		Assert::assertFalse(Helper::isBlockContainer(line))
	}
}