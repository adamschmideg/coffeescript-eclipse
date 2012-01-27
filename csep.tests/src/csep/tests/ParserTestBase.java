package csep.tests;

import java.io.InputStream;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResource;

import csep.CoffeeScriptStandaloneSetup;
import csep.parser.Helper;
import csep.parser.Lexer;

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
	@Override
	protected boolean shouldTestSerializer(XtextResource resource) {
		return false;
	}
	
	/**
	 * Parse and check for errors and warnings
	 * @param errorCount 1 means expect error(s)
	 * @param warningCount -1 means don't care about warnings
	 */
	protected void expect(CharSequence input, int errorCount, int warningCount) {
		List<String> tokens = null;
		//Assert::assertEquals('warnings ' + resource.warnings, 0, resource.warnings.size)
		//Assert::assertEquals('errors ' + resource.errors, 0, resource.errors.size)
		EObject parseResult = null;
		EList<Diagnostic> errors = null;
		EList<Diagnostic> warnings = null;
		try {
			Lexer lexer = new Lexer(input);
			tokens = lexer.tokenizeToStrings();
			InputStream in = getAsStream("" + input);
			URI uri = URI.createURI("mytestmodel." + getCurrentFileExtension());
			XtextResource resource = doGetResource(in, uri);
			parseResult = getModel(resource);
			errors = resource.getErrors();
			warnings = resource.getWarnings();
			if (errorCount == 0)
				assertEquals("Errors: " + errors, 0, errors.size());
			else if (errorCount > 0)
				assertTrue("No errors", errors.size() > 0);					
			if (warningCount >= 0) {				
				assertEquals("Warnings: " + warnings, warningCount, warnings.size());
			}			
		} catch (AssertionFailedError afe) {			
			if (logger.isDebugEnabled()) {
				String debug = "" + this.getClass().getSimpleName() + " input: \n" + input + "\n";
				debug += "Tokens: " + tokens + "\n" + Helper.stringify(parseResult);
				for (Diagnostic d: errors) {
					debug += "\tError: " + d + "\n";
				}
				for (Diagnostic d: warnings) {
					debug += "\tWarning: " + d + "\n";
				}
				logger.debug(debug);
			}
			throw new AssertionError(afe);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Input can be parsed without syntax errors
	 * @param input typically a String or a multiline xtend String
	 */
	public void ok(CharSequence input) {
		expect(input, 0, -1);
	}

	/**
	 * Parsing input results in one syntax error
	 * @param input
	 */
	public void error(CharSequence input) {
		expect(input, 1, -1);
	}
	
	/**
	 * Indicate that a test case should parse, but it gives errors
	 */
	 public void shouldBeOk(CharSequence input) {
		 String clazz = this.getClass().getSimpleName();
		 boolean wasOk = false;
		 try {
			 ok(input);	
			 wasOk = true;			
		 }
		 catch (AssertionError afe) {
			 logger.warn("Expected to successfully parse " + clazz + " '" + input + "', but " + afe.getMessage());
		 }
		 if (wasOk) {
			 fail("Expected an error, but parsed successfully '" + input + "'");
		 }
	 }
	 
	 /**
	  * It parses and has no warnings
	  * @param input
	  */
	 public void okNoWarning(CharSequence input) {
		 expect(input, 0, 0);
	 }
	 
	 public void shouldBeOkNoWarning(CharSequence input) {
		 String clazz = this.getClass().getSimpleName();
		 boolean wasOk = false;
		 try {
			 okNoWarning(input);	
			 wasOk = true;			
		 }
		 catch (AssertionError afe) {
			 logger.warn("Expected to parse without warning " + clazz + " '" + input + "', but " + afe.getMessage());
		 }
		 if (wasOk) {
			 fail("Expected a warning, but parsed successfully '" + input + "'");
		 }

	 }
}