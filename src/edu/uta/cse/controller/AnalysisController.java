package edu.uta.cse.controller;

import touchdevelopplugin.editors.TDPEditor;
import edu.uta.cse.handler.CodeHandler;
import edu.uta.cse.views.ButtonsView;
import edu.uta.cse.views.ButtonsView.ISelectionChange;

public class AnalysisController {
	TDPEditor editor;
	ButtonsView view;
	ISelectionChange isc ;
	public AnalysisController(ISelectionChange isc,TDPEditor editor,ButtonsView view){
		this.editor = editor;
		this.view  = view;
		this.isc = isc;
	}
	public void excute(){
		CodeHandler handler = new CodeHandler(isc,editor,view);
		handler.findStrategy();
		handler.excute();
	}
}
