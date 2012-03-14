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
	
	def void check(CharSequence input, boolean expectError, boolean expectWarning) {
	   	val in = getAsStream("" + input)
	  	val uri = URI::createURI("mytestmodel." + getCurrentFileExtension())
		val resource = doGetResource(in, uri)
		val parseResult = getModel(resource)
		if (!expectError && !resource.errors.empty)
			throw new AssertionError("Errors: " + resource.errors)
		if (!expectWarning && !resource.warnings.empty)
			throw new AssertionError("Warnings: " + resource.warnings)
	}
	  
	def void ok(CharSequence input) {
		check(input, false, false)
	}
	
	def void warning(CharSequence input) {
		check(input, false, true)
	}
	
	@Test def testSingle() {
		ok('''task 'name', 'desc', ->
				   answer = 42''')			
	}
	
	@Test def testMultiple() {
		ok('''
		  task "first", "Do this first", ->
		    count = 1
		    
		  task "second", "Do it next", ->
		    count = 2
		''')
	}
	
	@Test def testExplicitOptions() {
		ok('''
			task "doit", "Do it", (opts) ->
				count = opts.count
		''')
	}
	
	@Test def testResolution() {
		warning('''
			task "doit", "Do it", ->
				count = unresolved
		''')
	}
	
	/**
	 * This is not valid in the original cakefiles,
	 * but we want to be able to use the variable "options" implicitly
	 */
	@Test def testImplicitOptions() {
		ok('''
			task "doit", "Do it", ->
				n = 1
				count = n + options
		''')
	}
}
