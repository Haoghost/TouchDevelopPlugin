package touchdevelopplugin.analysis;

import org.eclipse.jface.text.ITextViewer;

public abstract class CodeHandlerStrategy {
	String analysisString ;
	ITextViewer contextViewer;
	public void setContext(String analysisString,ITextViewer contextViewer){
		this.analysisString = analysisString;
		this.contextViewer = contextViewer;
	}
	public abstract void doAnalysis();
}
