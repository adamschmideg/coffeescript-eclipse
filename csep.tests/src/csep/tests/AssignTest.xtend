package csep.tests

import org.junit.Test

class AssignTest extends ParserTestBase { 

  @Test
  def void testBasic() {
    ok('a = 3')
    ok('''
      a = 3
      b = 4
      ''')
  }

  @Test
  def void testBoolean() {
    ok('a or= 2')
    ok('a ||= 1')
    ok('a ?= b')
  }

  @Test
  def void testCompound() {
    ok('a = b = 1')
    shouldBeOk('[a, b, c] = [1, 2, 3]')
  }
}
