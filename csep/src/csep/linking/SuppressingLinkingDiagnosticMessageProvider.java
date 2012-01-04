package csep.linking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.diagnostics.DiagnosticMessage;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.linking.impl.LinkingDiagnosticMessageProvider;

import csep.scoping.DefaultGlobalScopeProvider;

/**
 * Suppress errors when reassigning a variable, and change errors to warnings when a reference cannot be resolved.
 * @author adam
 *
 */
public class SuppressingLinkingDiagnosticMessageProvider extends
		LinkingDiagnosticMessageProvider {
	private final static Set<String> BUILT_INS = 
			new HashSet<String>(Arrays.asList(DefaultGlobalScopeProvider.BUILT_INS));
	
	@Override
	/**
	 * XXX: Instead of properly handling built-ins, just catch diagnostic with a link to a built-in
	 * and don't forward it
	 */
	public DiagnosticMessage getUnresolvedProxyMessage(final ILinkingDiagnosticContext context) {
		boolean builtIn = isBuiltIn(context.getLinkText());
		if (builtIn) {
			return null;
		}
		else {
			DiagnosticMessage msg = super.getUnresolvedProxyMessage(context);
			DiagnosticMessage diagnostic = new DiagnosticMessage(msg.getMessage(), Severity.WARNING, msg.getIssueCode(), msg.getIssueData());
			return diagnostic;
		}
	}

	@Override
	public DiagnosticMessage getIllegalNodeMessage(ILinkingDiagnosticContext context, IllegalNodeException ex) {
		String message = ex.getMessage();
		return new DiagnosticMessage("oops " + message, Severity.ERROR, Diagnostic.LINKING_DIAGNOSTIC);
	}
	
	protected boolean isBuiltIn(String name) {
		return BUILT_INS.contains(name);
	}
}
