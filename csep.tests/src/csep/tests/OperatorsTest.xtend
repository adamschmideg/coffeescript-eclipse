package csep.tests

import org.junit.Test

class OperatorsTest extends ParserTestBase { 

  @Test
  def void testBinary() {
    ok('3 + 2')
    ok('4+5')
    shouldWork('a*-b')
    error('a +')
  }

  @Test
  def void testCompound() {
    ok('1 + 2 * 3')
    ok('(1 + 2) * 3')
    ok('foo + bar')
    ok('(2 == 3) + (2 < 4)')
  }

  @Test
  def void testNewLine() {
    ok('''3 +
          5''')
  }

  @Test
  def void testMultipleOperators() {
    ok('- -1')
    error('--1')
  }

  @Test
  def void testInstanceof() {
    ok('new String instanceof String')
    ok('new Boolean instanceof Boolean')
    ok('new Number not instanceof String')
    ok('new Array not instanceof Boolean')
  }
  
}
