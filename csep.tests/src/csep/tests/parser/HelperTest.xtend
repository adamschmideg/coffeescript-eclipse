package csep.tests.parser

import junit.framework.Assert
import csep.parser.Helper
import org.junit.Test

class HelperTest {
	
	@Test
	def void testBlockContainer() {
		block('a =')
		block('  a =')
		block('a = ')
	}
	
	def void block(String line) {
		Assert::assertTrue(Helper::isBlockContainer(line))
	}

	def void noBlock(String line) {
		Assert::assertFalse(Helper::isBlockContainer(line))
	}
}