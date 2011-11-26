package csep.tests

import com.google.inject.Inject
import csep.coffeeScript.Root
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class BasicTest { 

	@Inject
	ParseHelper<Root> parser
	
	@Test
	def void testSimple() {
		val model = parser.parse('number = 42')
	}
	
}
