package csep.scoping;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;

/**
 * To shup up linking problems for now
 * @author adam
 *
 */
public class DummyLinkingResource extends LazyLinkingResource {
	private final static Logger logger = Logger.getLogger(DummyLinkingResource.class);

	/**
	 * Sorry, but it only hides exceptions thrown by {@link LazyLinkingResource}
	 */
	@Override
	public synchronized EObject getEObject(String uriFragment) {
		EObject object = null;
		try {
			object = super.getEObject(uriFragment);
		}
		catch (WrappedException e) {
			// XXX: I don't what caused it
			logger.warn("Cannot resolve " + uriFragment);
			getErrors().clear();
		}
		return object;
	}
	
}
