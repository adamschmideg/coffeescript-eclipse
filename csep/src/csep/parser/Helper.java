package csep.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.reconstr.Serializer;

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
	public final static Set<String> IGNORE_PROP_NAMES = new HashSet<String>(Arrays.asList(new String[]{"class", "operator"}));

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

	protected static String stringify(Object obj, String indent) {
		if (obj == null) {
			return "null";
		}
		StringBuffer buf = new StringBuffer();
		Class<?> clazz = obj.getClass();
		Map<String, Object> props = getProperties(obj);
		Object maybeOperator = props.get("operator");
		String name = clazz.getSimpleName();
		if (maybeOperator != null) {
			name += "" + maybeOperator;
		}
		buf.append(name + "\n");
		for (Map.Entry<String,Object> entry : props.entrySet()) {
			Object child = entry.getValue();
			if (child != null && !IGNORE_PROP_NAMES.contains(entry.getKey())) {
				buf.append(indent + entry.getKey() + ": ");
				if (child instanceof EObject) {
					buf.append(stringify((EObject) child, indent + INDENT));
				} 
				else if (child instanceof List) {
					buf.append("\n");
					for (Object kid: (List<?>)child) {
						buf.append(indent + INDENT);
						buf.append(stringify(kid, indent + INDENT + INDENT));
					}
				} else {
					buf.append("" + child + "\n");
				}
			}
		}
		return buf.toString().replace("Impl", "");
	}

	/**
	 * Why do I have to write this function??!
	 */
	public static Map<String,Object> getProperties(Object obj) {
		Map<String,Object> props = new HashMap<String,Object>();
		for (Method m: obj.getClass().getMethods()) {
			int mod = m.getModifiers();
			if (Modifier.isPublic(mod) && 
					!Modifier.isStatic(mod) && 
					m.getParameterTypes().length == 0 &&
					m.getName().startsWith("get")) {
				String name = m.getName().substring(3,4).toLowerCase() + m.getName().substring(4);
				Object value = null;
				try {
					value = m.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				props.put(name, value);
			}
		}
		return props;
	}
}
