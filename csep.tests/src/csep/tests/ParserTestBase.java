package csep.tests;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResource;

import csep.CoffeeScriptStandaloneSetup;
import csep.parser.Helper;

/**
 * Enable testing if a code snippet gets parsed as expected.
 * 
 * @author Adam Schmideg <adam@schmideg.net>
 */

public abstract class ParserTestBase extends AbstractXtextTests {
	private final static Logger logger = Logger.getLogger(ParserTestBase.class);

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(new CoffeeScriptStandaloneSetup());
	}

	/**
	 * XXX: Always return false, otherwise {@link AbstractXtextTests} will check whitespaces,
	 * so for example "a = 2" would fail, because it's not equal to its serialized version "a=2". 
	 */
	//@Override
	protected boolean shouldTestSerializer(XtextResource resource) {
		return false;
	}

	protected void expect(Object input, int errors) {
		try {
			EObject parseResult = getModelAndExpect(input.toString(), errors);
			if (logger.isDebugEnabled()) {
				logger.debug("Parsed '" + input + "'\n" +Helper.stringify(parseResult));
			}
		} catch (AssertionFailedError afe) {
			throw afe;
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Input can be parsed without syntax errors
	 * @param input typically a String or a multiline xtend String
	 */
	public void ok(Object input) {
		expect(input, 0);
	}

	/**
	 * Parsing input results in one syntax error
	 * @param input
	 */
	public void error(Object input) {
		expect(input, 1);
	}
	
	/**
	 * Indicate that a test case should parse, but it gives errors
	 */
	 public void shouldBeOk(Object input) {
		 try {
			 ok(input);
			 logger.warn("Expected an error, but parsed successfully '" + input + "'");
		 }
		 catch (AssertionFailedError afe) {
			 logger.warn("Expected to successfully parse '" + input + "', but " + afe.getMessage());
		 }
	 }
}