package csep.scoping;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;

/**
 * To shup up linking problems for now
 * @author adam
 *
 */
public class SuppressingLinkingResource extends LazyLinkingResource {
	private final static Logger logger = Logger.getLogger(SuppressingLinkingResource.class);

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
}
