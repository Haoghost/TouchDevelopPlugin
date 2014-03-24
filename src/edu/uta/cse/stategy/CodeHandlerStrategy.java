package edu.uta.cse.stategy;

import org.eclipse.jface.text.ITextViewer;

public abstract class CodeHandlerStrategy {
	String analysisString ;
	ITextViewer contextViewer;
	public void setContext(String analysisString,ITextViewer contextViewer){
		this.analysisString = analysisString;
		this.contextViewer = contextViewer;
	}
	public abstract String[] doAnalysis(CodeExtractor cfr, String contex, String selectedWord);
}