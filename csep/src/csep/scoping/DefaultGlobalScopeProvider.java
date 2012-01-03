package csep.scoping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

public class DefaultGlobalScopeProvider extends
		ImportedNamespaceAwareLocalScopeProvider {
	public final static String[] BUILT_INS = new String[]{
		// TODO: get an exhaustive (?) list of built-in javascript/coffeescript/nodejs objects
		"console",
		"global",
		"module",
		"process",
		"require",
		"Array",
		"Boolean",
		"Date",
		"Math",
		"Number",
		"Object",
		"String",
		"RegExp",
	};
	
	@Override
	protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		List<ImportNormalizer> temp = new ArrayList<ImportNormalizer>();
		for (String name: BUILT_INS) {
			temp.add(new ImportNormalizer(QualifiedName.create(name), true, ignoreCase));
		}
		return temp;
	}
}
