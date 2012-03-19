package csep.scoping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.parser.IParseResult;

import csep.coffeeScript.CoffeeScriptFactory;
import csep.coffeeScript.Id;

/**
 * To shup up linking problems for now
 * @author adam
 *
 */
public class SuppressingLinkingResource extends LazyLinkingResource {
	private final static Logger logger = Logger.getLogger(SuppressingLinkingResource.class);
	protected Map<String,EObject> implicitVariables = null;
	
	@Override
	public EList<Diagnostic> getErrors() {
		if (errors == null)
			errors = new DebugListImpl();
		return errors;
	}
	
	public class DebugListImpl extends NotifyingListImpl<Diagnostic> {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean add(Diagnostic diagnostic) {
			logger.debug("Adding error " + diagnostic);
			return super.add(diagnostic);
		}
		
        @Override
        protected boolean isNotificationRequired() {
           return SuppressingLinkingResource.this.eNotificationRequired();
        }

        @Override
        public Object getNotifier() {
          return SuppressingLinkingResource.this;
        }

        @Override
        public int getFeatureID() {
          return RESOURCE__ERRORS;
        }

	}
	
	/**
	 * Override @{link #getImplicitVariableNames} instead of this method
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
		return Collections.emptySet();
	}
	
	@Override
	protected void updateInternalState(IParseResult newParseResult) {
		getContents().addAll(getImplicitVariables().values());
		super.updateInternalState(newParseResult);
	}
}
