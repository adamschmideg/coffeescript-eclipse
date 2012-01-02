package csep.tests

import org.junit.Test

class ScopeTest extends ParserTestBase {
	
  	@Test
  	def void testAssignment() {
    	okNoWarning('''
      	  a = 1
      	  a = 2
      	''')
      	okNoWarning('''
      	  a = 0
      	  b = a + 1
    	''')
  	}

	@Test
	def void testLambdaParameter() {
		expect('''
		  fun = (x) ->
		    2 * x
		  fun y
		''', 0, 1)
		okNoWarning('''
		  fun = (x) ->
		    2 * x
		''')		
	}
}
