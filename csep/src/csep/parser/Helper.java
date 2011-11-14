package csep.parser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.reconstr.Serializer;

import com.google.inject.Guice;

import csep.CoffeeScriptRuntimeModule;

/**
 * Used mostly for debugging
 * @author Adam Schmideg <adam@schmideg.net>
 */
public class Helper {
	private static Serializer serializer;
	
	public static Serializer getSerializer() {
		if (serializer == null) {
			serializer = Guice.createInjector(new CoffeeScriptRuntimeModule()).getInstance(Serializer.class);
		}
		return serializer;
	}

	/**
	 * Create a human-friendly string representation of a parse element
	 * @param eobj
	 * @return 
	 */
	public static String stringify(EObject eobj) {
		if (eobj == null) {
			return "null";
		}
		try {
			Serializer s = getSerializer();
			return s.serialize(eobj);
		} 
		catch (Exception ex) { // fall back:
			ex.printStackTrace();
			return eobj.getClass().getSimpleName() + '@' + eobj.hashCode();
		}
	}
}
