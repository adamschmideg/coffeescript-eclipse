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
public class SuppressingLinkingResource extends LazyLinkingResource {
	private final static Logger logger = Logger.getLogger(SuppressingLinkingResource.class);


}
