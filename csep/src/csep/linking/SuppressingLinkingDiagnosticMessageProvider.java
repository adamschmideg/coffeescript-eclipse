package csep.linking;

import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.diagnostics.DiagnosticMessage;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.linking.impl.LinkingDiagnosticMessageProvider;

/**
 * Suppress errors when reassigning a variable, and change errors to warnings when a reference cannot be resolved.
 * @author adam
 *
 */
public class SuppressingLinkingDiagnosticMessageProvider extends
		LinkingDiagnosticMessageProvider {
	
	@Override
	public DiagnosticMessage getUnresolvedProxyMessage(final ILinkingDiagnosticContext context) {
		DiagnosticMessage msg = super.getUnresolvedProxyMessage(context);
		DiagnosticMessage diagnostic = new DiagnosticMessage(msg.getMessage(), Severity.WARNING, msg.getIssueCode(), msg.getIssueData());
		return null;
	}

	@Override
	public DiagnosticMessage getIllegalNodeMessage(ILinkingDiagnosticContext context, IllegalNodeException ex) {
		String message = ex.getMessage();
		return new DiagnosticMessage("oops " + message, Severity.ERROR, Diagnostic.LINKING_DIAGNOSTIC);
	}
}
