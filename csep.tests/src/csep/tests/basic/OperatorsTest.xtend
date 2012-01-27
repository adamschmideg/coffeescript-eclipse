package csep.tests.basic

import csep.tests.ParserTestBase
import org.junit.Test

class OperatorsTest extends ParserTestBase { 

  @Test
  def void testBinary() {
    ok('3 + 2')
    ok('4+5')
    error('a +')
  }
  
  @Test
  def void testUnaryWithBinary() {
    ok('a*-b')  
    ok('a * -b')
    ok('a? + b')
    ok('a?+b')
    ok('++a - b')
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
  def void testBitwise() {
    ok('10 &   3')
    ok('10 |   3')
    ok('10 ^   3')
    ok('10 <<  3')
    ok('10 >>  3')
    ok('10 >>> 3')
    ok('num &=   3')
    ok('num |=   3')
    ok('num ^=   3')
    ok('num <<=  3')
    ok('num >>=  3')
    ok('num >>>= 3')
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

  @Test
  def void testExistential() {
    ok('a ? b')
    ok('nothing ? -1')
    ok('defined?')
    ok('!nothing?')
    ok('(1 or 0)?')
  }

  @Test
  def void testChainable() {
    ok('a < b < c')
  }

  @Test
  def void testCombinedComparisons() {
    ok('a < b > c')
    ok('10 < 20 > 2+3 is 5')
  }
  
  @Test
  def void testThisProperty() {
  	ok('@foo + this.bar')
  }
}
