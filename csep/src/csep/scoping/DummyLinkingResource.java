package csep.scoping;

import org.eclipse.xtext.linking.lazy.LazyLinkingResource;

/**
 * To shup up linking problems for now
 * @author adam
 *
 */
public class DummyLinkingResource extends LazyLinkingResource {

	@Override
	protected void addSyntaxErrors() {
		super.addSyntaxErrors();
	}
}
