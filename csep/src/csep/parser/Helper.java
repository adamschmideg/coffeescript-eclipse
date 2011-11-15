package csep.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.reconstr.Serializer;
import org.eclipse.xtext.resource.SaveOptions;

import com.google.inject.Guice;

import csep.CoffeeScriptRuntimeModule;

/**
 * Used mostly for debugging
 * 
 * @author Adam Schmideg <adam@schmideg.net>
 */
public class Helper {
	public final static String INDENT = "  ";
	private static Serializer serializer;

	public static Serializer getSerializer() {
		if (serializer == null) {
			serializer = Guice.createInjector(new CoffeeScriptRuntimeModule())
					.getInstance(Serializer.class);
		}
		return serializer;
	}

	/**
	 * Create a human-friendly string representation of a parse element
	 * 
	 * @param eobj
	 * @return
	 */
	public static String stringify(EObject eobj) {
		return stringify(eobj, INDENT);
	}

	protected static String stringify(EObject eobj, String indent) {
		if (eobj == null) {
			return "null";
		}
		try {
			StringBuffer buf = new StringBuffer();
			Class clazz = eobj.getClass();
			buf.append(clazz.getSimpleName() + "\n");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					String name = f.getName();
					buf.append(indent + name + ": ");
					String methodName = "get"
							+ name.substring(0, 1).toUpperCase()
							+ name.substring(1, name.length());
					Method method = clazz.getDeclaredMethod(methodName, null);
					Object child = method.invoke(eobj, null);
					if (child instanceof EObject) {
						buf.append(stringify((EObject) child, indent + INDENT));
					} else {
						buf.append("" + child + "\n");
					}
				}
			}
			return buf.toString().replace("Impl", "");
		} catch (Exception ex) { // fall back:
			ex.printStackTrace();
			return eobj.getClass().getSimpleName() + '@' + eobj.hashCode();
		}
	}
}
