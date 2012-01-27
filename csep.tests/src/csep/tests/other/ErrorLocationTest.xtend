package csep.tests.other

import csep.tests.ParserTestBase
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.junit.Assert

class ErrorLocationTest extends ParserTestBase {
	
	@Test
	def void testAfterComment() {
		val source = '''
		  # Some comment
		  if yes then a = 1
		'''
		val in = getAsStream('' + source)
		val uri = URI::createURI('mytestmodel.' + currentFileExtension)
		val resource = doGetResource(in, uri)
		Assert::assertEquals('warnings ' + resource.warnings, 0, resource.warnings.size)
		Assert::assertEquals('errors ' + resource.errors, 0, resource.errors.size)
	}
}
