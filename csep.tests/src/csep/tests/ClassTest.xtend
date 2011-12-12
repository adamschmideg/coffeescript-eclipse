package csep.tests

import org.junit.Test

class ClassTest extends ParserTestBase {
	
	@Test
	def void testBasic() {
		ok('''
		  class NicePerson extends Person
		    firstName: "John"
		    lastName: "Doe"
		    
		    introduce: -> print "I am #{firstName} #{lastName}"
		    
		    greet: (other) ->
		      print "Hello #{other}"
		''')
	}
}