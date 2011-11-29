package csep.tests;

import junit.framework.Assert;

import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import com.google.inject.Inject;

import csep.coffeeScript.Body;
import csep.coffeeScript.Line;
import csep.coffeeScript.Root;
import csep.parser.Helper;

/**
 * Enable testing if a code snippet gets parsed as expected.
 * @author Adam Schmideg <adam@schmideg.net>
 */

@InjectWith(InjectorProviderCustom.class)
public class ParserTestBase {
  
  @Inject
  private ParseHelper<Root> parser;
  private Diagnostician diagnostician = new Diagnostician();
  
	public void check(final Object snippet, final Object expected)
			throws Exception {
		if (expected != null && snippet != null) {
			Root root = parser.parse(snippet.toString());
			compare(root, expected);
		}
	}
	
	public void checkLine(final Object snippet, final Object expected) throws Exception {
		if (expected != null && snippet != null) {
			Body body = (Body)parser.parse(snippet.toString());
			Line line = body.getLines().get(0);
			compare(line, expected);
		}
	}	
	
	public void compare(EObject parseResult, Object expected) throws Exception {
		Diagnostic issues = diagnostician.validate(parseResult);
		if (issues.getSeverity() != Diagnostic.OK) {
			Assert.fail(issues.getMessage());
		}
		String parsed = Helper.stringify(parseResult).trim();
		String expectedStr = expected.toString().trim();
		Assert.assertEquals(expectedStr, parsed);
	}
}