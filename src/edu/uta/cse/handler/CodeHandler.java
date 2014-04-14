package edu.uta.cse.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import touchdevelopplugin.editors.TDPEditor;
import edu.uta.cse.strategy.AccessStrategy;
import edu.uta.cse.strategy.CodeHandlerStrategy;
import edu.uta.cse.strategy.InputStrategy;
import edu.uta.cse.strategy.TypeStrategy;
import edu.uta.cse.strategy.VariableStrategy;
import edu.uta.cse.util.Constant;
import edu.uta.cse.views.ButtonsView;
import edu.uta.cse.views.ButtonsView.ISelectionChange;

public class CodeHandler {
	TDPEditor editor;
	ButtonsView view;
	ISelectionChange isc ;
	CodeHandlerStrategy strategy ;
	String context;
	String selectedString;
	public CodeHandler(ISelectionChange isc,TDPEditor editor,ButtonsView view){
		this.editor = editor;
		this.view  = view;
		this.isc = isc;
		context = editor.getJavaConfiguration()
				.getDoubleClickStrategy().getfText().getDocument().get();
		
	}
	public void findStrategy(){
		selectedString = isc.getSelectedStringFormEditor(editor);
		// Get types.
		List<String> types = new ArrayList<String>() ;
	    for(int i = 0; i < Constant.basicFieldType.length; i++){
	    	types.add(Constant.basicFieldType[i]);
	    }
	    List<String> identifiers = new ArrayList<String>() ;
	    for(int i = 0; i < Constant.accessIdentifierType.length; i++){
	    	identifiers.add(Constant.accessIdentifierType[i]);
	    }
		if(selectedString.equals("=")){
			strategy =new VariableStrategy();
		}else if(identifiers.contains(selectedString)){
			ButtonsView.offetOfVariable= 0;
			strategy = new AccessStrategy();
		}else if(types.contains(selectedString)){
			ButtonsView.offetOfVariable= 0;
			strategy = new TypeStrategy();
		}else{
			ButtonsView.offetOfVariable= 0;
			strategy = new InputStrategy();
		}
	}
	public void excute(){
		ButtonsView.buttonText = strategy.doAnalysis(context, selectedString);
		view.getViewer().setContentProvider(view.getViewer().getContentProvider());
	}
}
