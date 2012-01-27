package csep.tests.basic

import csep.tests.ParserTestBase
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

  /**
   * Unfortunately, checking is not called by the testing framework,
   * so this one will succeed no matter what.
   */
  @Test
  def void testReassign() {
    ok('''
      a = 2
      a = 3
    ''')	
  }
  
  @Test
  def void testErrorInRewriter() {
  	error('a = ]')
  }
  
  @Test
  /**
   * To really test it, change error to ok, and check if the error is in the second line
   */
  def void testErrorInScanner() {
  	error('''
  	  before = 1
  	  case = 2
  	  unreached = 3
  	''')	
  }
  
  @Test
  def void testDestructure() {
    ok('[a, b] = [1, 2]')	
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
    ok('a = ++b')
    ok('[a, b, c] = [1, 2, 3]')
  }
  
  @Test
  def void testUnassignable() {
  	error('1 = 2')
  	error('(a + b) = 2')
  	ok('foo.bar = 3')
  	error('foo.bar() = 3')
  }
  
  @Test
  def void testThisProperty() {
  	ok('@count = 1')
  	ok('@server.production.name = "local"')  	
  	ok('this.owner.age = 32')
  }
  
  @Test
  def void testProperty() {
  	ok('a.b = 1')
  	ok('Clazz::counter = 1')
  }
}
