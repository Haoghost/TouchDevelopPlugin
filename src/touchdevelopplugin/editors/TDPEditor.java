package touchdevelopplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class TDPEditor extends TextEditor {
	public static final String ID = "touchdevelopplugin.editors.JAVAEditor";
	private TDPColorManager colorManager;
	private TDPConfiguration javaConfiguration;
	public TDPEditor() {
		super();
		colorManager = new TDPColorManager();
		javaConfiguration = new TDPConfiguration(colorManager);
		setSourceViewerConfiguration(javaConfiguration);
		setDocumentProvider(new TDPDocumentProvider());
		
	}
	public TDPConfiguration getJavaConfiguration() {
		return javaConfiguration;
	}
	public void setJavaConfiguration(TDPConfiguration javaConfiguration) {
		this.javaConfiguration = javaConfiguration;
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
