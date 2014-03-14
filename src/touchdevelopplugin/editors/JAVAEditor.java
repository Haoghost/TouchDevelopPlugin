package touchdevelopplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class JAVAEditor extends TextEditor {

	private ColorManager colorManager;

	public JAVAEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new JAVAConfiguration(colorManager));
		setDocumentProvider(new JAVADocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
