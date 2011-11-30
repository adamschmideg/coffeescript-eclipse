package csep.tests

import org.junit.Test

class ControlFlowTest extends ParserTestBase {
	
	@Test
	def void testIf() {
    ok('''
      if a
        1
      ''')
    ok('''
      if a
        1
      else if b
        2
      ''')
    ok('''
      if a
        1
      else if b
        2
      else
        3
      ''')
	}

}
