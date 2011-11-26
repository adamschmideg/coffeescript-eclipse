package csep.tests;

import junit.framework.Assert;

import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.util.ParseHelper;

import com.google.inject.Inject;

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
  
	public void check(final Object snippet, final Object expected)
			throws Exception {
		if (expected != null && snippet != null) {
			Root root = parser.parse(snippet.toString());
			String parsed = Helper.stringify(root).trim();
			String expectedStr = expected.toString().trim();
			Assert.assertEquals(expectedStr, parsed);
		}
	}
}