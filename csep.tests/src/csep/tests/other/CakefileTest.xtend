package csep.tests.other

import csep.tests.ParserTestBase
import org.junit.Test

class CakefileTest extends ParserTestBase {
	
	@Test
	def void testSimple() {
		ok('''
          task "doit", "describe", ->
            answer = 42''')
	}
}
