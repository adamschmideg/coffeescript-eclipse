package csep.example.cake.scoping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.parser.IParseResult;

import csep.coffeeScript.CoffeeScriptFactory;
import csep.coffeeScript.Id;

public class CakefileLinkingResource extends LazyLinkingResource {

	protected Map<String,EObject> implicitVariables = null;
	
	/**
	 * @return a mapping, assuming that the key equals to the name of the value
	 */
	public Map<String,EObject> getImplicitVariables() {
		if (implicitVariables == null) {
			implicitVariables = new HashMap<String,EObject>();
			for (String name: getImplicitVariableNames()) {
				Id id = CoffeeScriptFactory.eINSTANCE.createId();
				id.setName(name);
				implicitVariables.put(name, id);
			}
		}
		return implicitVariables;
	}
	
	/**
	 * 
	 * @return names of all implicit variable names in any context
	 */
	public Set<String> getImplicitVariableNames() {
		return Collections.singleton("options");
	}
	
	@Override
	protected void updateInternalState(IParseResult newParseResult) {
		getContents().addAll(getImplicitVariables().values());
		super.updateInternalState(newParseResult);
	}
}
