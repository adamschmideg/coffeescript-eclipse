package csep.example.cake.scoping;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.google.inject.Inject;

/**
 * When {@link LinkingServiceWithNode.getLinkedObjects} is called, an {@link INode} instance is provided so we can calculate the name of the referred variable.
 * The problem is that the call to getScope doesn't pass that node,
 * so the underlying scopingprovider has no way to get that name.
 * XXX: This is a hack to solve this problem.
 * @author adam
 *
 */
public class LinkingServiceWithNode extends DefaultLinkingService {
	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;
	
	private INode linkNode;
	
	/**
	 * Basically a copy of super, only linkNode is remembered before calling getScope
	 */
	public List<EObject> getLinkedObjects(EObject context, EReference ref, INode node)
			throws IllegalNodeException {
		final EClass requiredType = ref.getEReferenceType();
		if (requiredType == null)
			return Collections.<EObject> emptyList();

		final String crossRefString = getCrossRefNodeAsString(node);
		if (crossRefString != null && !crossRefString.equals("")) {
			linkNode = node;
			final IScope scope = getScope(context, ref);
			linkNode = null;
			QualifiedName qualifiedLinkName =  qualifiedNameConverter.toQualifiedName(crossRefString);
			IEObjectDescription eObjectDescription = scope.getSingleElement(qualifiedLinkName);
			if (eObjectDescription != null) 
				return Collections.singletonList(eObjectDescription.getEObjectOrProxy());
		}
		return Collections.emptyList();
	}

	/**
	 * Mostly a copy of super
	 */
	protected IScope getScope(EObject context, EReference reference) {
		IScopeProvider scopeProvider = getScopeProvider();
		if (scopeProvider == null)
			throw new IllegalStateException("scopeProvider must not be null.");
		try {
			registerImportedNamesAdapter(context);
			if (scopeProvider instanceof IScopeProviderWithNode) {
				((IScopeProviderWithNode)scopeProvider).setNode(linkNode);
			}
			return scopeProvider.getScope(context, reference);
		} finally {
			unRegisterImportedNamesAdapter();
		}
	}

}
