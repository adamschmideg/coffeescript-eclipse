package csep.example.cake.scoping;

import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.scoping.IScopeProvider;

public interface IScopeProviderWithNode extends IScopeProvider {

	void setNode(INode node);
}
