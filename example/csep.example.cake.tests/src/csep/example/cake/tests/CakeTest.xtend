package csep.example.cake.tests

import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.junit.AbstractXtextTests
import org.junit.Test

import csep.example.cake.CakefileStandaloneSetup

public class CakeTest extends AbstractXtextTests {

	override void setUp() {
	    super.setUp()
	    with(new CakefileStandaloneSetup())
	}
	
	def void check(CharSequence input) {
	   	val in = getAsStream("" + input)
	  	val uri = URI::createURI("mytestmodel." + getCurrentFileExtension())
		val resource = doGetResource(in, uri)
		val parseResult = getModel(resource)
		val errors = resource.errors
		if (errors.size() > 0)
			throw new AssertionError("Errors: " + errors)
	}
	  
	@Test
	def void testValidate() {
		check('''task 'name', 'desc', ->
				   answer = 42''')			
	}
}
