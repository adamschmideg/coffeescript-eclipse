package csep.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import com.google.inject.Inject
import org.junit.Test
import org.eclipse.xtext.junit4.util.ParseHelper
import junit.framework.Assert
import csep.coffeeScript.Model
import csep.coffeeScript.Greeting

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class BasicTest { 

	@Inject
	ParseHelper<Model> parser
	
	@Test
	def void testParsing() {
		val model = parser.parse('''
		  Hello Baby
		''')
		val tree = model.eContents
		val greet = tree.get(0) as Greeting
		Assert::assertEquals("Baby", greet.name)
	}

}
