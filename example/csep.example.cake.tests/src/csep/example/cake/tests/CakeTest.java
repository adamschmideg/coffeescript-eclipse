package csep.example.cake.tests;

import java.io.InputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;

import csep.example.cake.CakefileStandaloneSetup;

public class CakeTest extends AbstractXtextTests {
	  @Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    with(new CakefileStandaloneSetup());
	  }
	
	  protected void check(CharSequence input) {
		  EObject parseResult = null;
		  EList<Diagnostic> errors = null;
		  EList<Diagnostic> warnings = null;
		  InputStream in = getAsStream("" + input);
		  URI uri = URI.createURI("mytestmodel." + getCurrentFileExtension());
		  try {
			XtextResource resource = doGetResource(in, uri);
			parseResult = getModel(resource);
			warnings = resource.getWarnings();
			errors = resource.getErrors();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  @Test
	  public void testValidate() {
		  check("task 'name', 'desc', ->" + "\t42");
	  }
}
