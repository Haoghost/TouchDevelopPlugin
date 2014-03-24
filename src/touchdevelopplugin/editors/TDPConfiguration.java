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

public class TDPConfiguration extends SourceViewerConfiguration {
	private TDPDoubleClickStrategy doubleClickStrategy;
	private TDPTagScanner tagScanner;
	private TDPScanner scanner;
	private TDPColorManager colorManager;

	public TDPDoubleClickStrategy getDoubleClickStrategy() {
		return doubleClickStrategy;
	}
	public void setDoubleClickStrategy(TDPDoubleClickStrategy doubleClickStrategy) {
		this.doubleClickStrategy = doubleClickStrategy;
	}
	public TDPTagScanner getTagScanner() {
		return tagScanner;
	}
	public void setTagScanner(TDPTagScanner tagScanner) {
		this.tagScanner = tagScanner;
	}
	public TDPScanner getScanner() {
		return scanner;
	}
	public void setScanner(TDPScanner scanner) {
		this.scanner = scanner;
	}
	public TDPColorManager getColorManager() {
		return colorManager;
	}
	public void setColorManager(TDPColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public TDPConfiguration(TDPColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			TDPPartitionScanner.JAVA_COMMENT,
			TDPPartitionScanner.JAVA_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new TDPDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected TDPScanner getJAVAScanner() {
		if (scanner == null) {
			scanner = new TDPScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(TDPColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected TDPTagScanner getJAVATagScanner() {
		if (tagScanner == null) {
			tagScanner = new TDPTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(TDPColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getJAVATagScanner());
		reconciler.setDamager(dr, TDPPartitionScanner.JAVA_TAG);
		reconciler.setRepairer(dr, TDPPartitionScanner.JAVA_TAG);

		dr = new DefaultDamagerRepairer(getJAVAScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		TDPNonRuleBasedDamagerRepairer ndr =
			new TDPNonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(TDPColorConstants.JAVA_COMMENT)));
		reconciler.setDamager(ndr, TDPPartitionScanner.JAVA_COMMENT);
		reconciler.setRepairer(ndr, TDPPartitionScanner.JAVA_COMMENT);

		return reconciler;
	}

}