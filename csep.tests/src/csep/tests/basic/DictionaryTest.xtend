package csep.tests.basic

import csep.tests.ParserTestBase
import org.junit.Test

class DictionaryTest extends ParserTestBase {

	@Test
	def void testBasic() {
		ok('obj = {name: "Jack", age: 32}')
	}
	
	@Test
	def void testYamlStyle() {
		ok('''
		  config = 
		  	name: "Jack"
		  	age: 32
		''')
		ok('''
		  config =
		    development:
		      server: 'localhost'
		      timeout: 10
		    
		    production:
		      server: 'dreamhost', port: 8000
		      timeout: 1000
		''')
	}	
}