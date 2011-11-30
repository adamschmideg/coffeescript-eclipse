package csep.tests

import org.junit.Test

class ControlFlowTest extends ParserTestBase {
	
	@Test
	def void testBasicConditionals() {
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

    ok('''
      unless a
        1
      ''')
    ok('''
      unless a
        1
      else unless b
        2
      ''')
    ok('''
      unless a
        1
      else unless b
        2
      else
        3
      ''')
    error('if a b')
    error('if a: b')
    error('''
      if a
      b
      ''')
    ok('''
      if a
        1
      else unless b
        2
    ''')
  }

  @Test
  def void testSingleLineConditional() {
    ok('if something then 1 else 2')
    ok('unless nothing then 1 else 2')
  }

}
