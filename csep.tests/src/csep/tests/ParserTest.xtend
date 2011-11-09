package csep.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import com.google.inject.Inject
import org.junit.Test
import org.eclipse.xtext.junit4.util.ParseHelper
import junit.framework.Assert
import csep.coffeeScript.Model
import csep.coffeeScript.Assign
import java.util.*
import csep.parser.Lexer

@org.junit.runner.RunWith(typeof(XtextRunner))
@InjectWith(typeof(InjectorProviderCustom))
class ParserTest {
	
	@Inject
	ParseHelper<Model> parser
	
	@Test
	def void testSimple() {
		val model = parser.parse('''
			number=42
			answer=42
		''')
		println(model)
	}
	
}
