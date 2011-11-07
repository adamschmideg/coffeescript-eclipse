package csep.tests;

import com.google.inject.Inject;
import csep.coffeeScript.Model;
import csep.tests.InjectorProviderCustom;
import junit.framework.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(InjectorProviderCustom.class)
public class BasicTest {
  
  @Inject
  private ParseHelper<Model> parser;
  
  @Test
  public void testParsing() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Hello Baby");
      _builder.newLine();
      Model _parse = this.parser.parse(_builder);
      final Model model = _parse;
      EList<EObject> _eContents = model.eContents();
      final EList<EObject> tree = _eContents;
      EObject _get = tree.get(0);
      String _string = _get.toString();
      Assert.assertEquals("Hello", _string);
    }
  }
}