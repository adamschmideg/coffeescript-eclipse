package csep.scoping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

import com.google.inject.Inject;

public class DefaultGlobalScopeProvider extends
		ImportedNamespaceAwareLocalScopeProvider {
	
	@Inject
	protected CoffeescriptBuiltins builtins;
	
	@Override
	protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		List<ImportNormalizer> temp = new ArrayList<ImportNormalizer>();
		for (String name: builtins.getPrefixes()) {
			temp.add(new ImportNormalizer(QualifiedName.create(name), true, ignoreCase));
		}
		return temp;
	}
}
