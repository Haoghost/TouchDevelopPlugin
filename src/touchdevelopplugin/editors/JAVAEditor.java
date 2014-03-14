package touchdevelopplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class JAVAEditor extends TextEditor {
	public static final String ID = "touchdevelopplugin.editors.JAVAEditor";
	private ColorManager colorManager;
	private JAVAConfiguration javaConfiguration;
	public JAVAEditor() {
		super();
		colorManager = new ColorManager();
		javaConfiguration = new JAVAConfiguration(colorManager);
		setSourceViewerConfiguration(javaConfiguration);
		setDocumentProvider(new JAVADocumentProvider());
		
	}
	public JAVAConfiguration getJavaConfiguration() {
		return javaConfiguration;
	}
	public void setJavaConfiguration(JAVAConfiguration javaConfiguration) {
		this.javaConfiguration = javaConfiguration;
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
