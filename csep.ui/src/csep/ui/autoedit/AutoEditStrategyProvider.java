package csep.ui.autoedit;

import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;
import org.eclipse.xtext.ui.editor.model.TerminalsTokenTypeToPartitionMapper;

/**
 * XXX: Instantiates a {@link IndentLineAutoEditStrategy} instead of using injection
 */
public class AutoEditStrategyProvider extends DefaultAutoEditStrategyProvider {
	private IndentLineAutoEditStrategy indentLineAutoEditStrategy = new IndentLineAutoEditStrategy();

	@Override
	protected void configureIndentationEditStrategy(IEditStrategyAcceptor acceptor) {
		acceptor.accept(indentLineAutoEditStrategy, IDocument.DEFAULT_CONTENT_TYPE);
		acceptor.accept(indentLineAutoEditStrategy, TerminalsTokenTypeToPartitionMapper.COMMENT_PARTITION);
		acceptor.accept(indentLineAutoEditStrategy, TerminalsTokenTypeToPartitionMapper.SL_COMMENT_PARTITION);
	}

}
