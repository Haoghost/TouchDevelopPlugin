package touchdevelopplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class JAVAConfiguration extends SourceViewerConfiguration {
	private JAVADoubleClickStrategy doubleClickStrategy;
	private JAVATagScanner tagScanner;
	private JAVAScanner scanner;
	private ColorManager colorManager;

	public JAVAConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			JAVAPartitionScanner.JAVA_COMMENT,
			JAVAPartitionScanner.JAVA_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new JAVADoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected JAVAScanner getJAVAScanner() {
		if (scanner == null) {
			scanner = new JAVAScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IJAVAColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected JAVATagScanner getJAVATagScanner() {
		if (tagScanner == null) {
			tagScanner = new JAVATagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IJAVAColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getJAVATagScanner());
		reconciler.setDamager(dr, JAVAPartitionScanner.JAVA_TAG);
		reconciler.setRepairer(dr, JAVAPartitionScanner.JAVA_TAG);

		dr = new DefaultDamagerRepairer(getJAVAScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IJAVAColorConstants.JAVA_COMMENT)));
		reconciler.setDamager(ndr, JAVAPartitionScanner.JAVA_COMMENT);
		reconciler.setRepairer(ndr, JAVAPartitionScanner.JAVA_COMMENT);

		return reconciler;
	}

}