package edu.uta.cse.main;

//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import java.util.ArrayList;

import org.eclipse.ui.part.*;

import edu.uta.cse.util.Constant;
import edu.uta.cse.views.*;
import touchdevelopplugin.editors.*;

public class TypeStrategy extends CodeHandlerStrategy {

	private static ArrayList<String> FieldType=new ArrayList<String>();
	//@Override
	public void doAnalysis() {
		// TODO Auto-generated method stub

		TDPEditor jve = new TDPEditor();
		//StringBuffer theWord= jve.getJavaConfiguration().getDoubleClickStrategy().getSelectedWord();
		StringBuffer theWord = TDPDoubleClickStrategy.SelectedWord;
		if (theWord.toString().equals("void") ){
			FieldType.clear();
			FieldType.add("int");
			FieldType.add("String");
			FieldType.add("double");
			FieldType.add("float");

		}else if(theWord.toString().equals("String")){
			FieldType.clear();
			FieldType.add("int");
			FieldType.add("void");
			FieldType.add("double");
			FieldType.add("float");
		}else if(theWord.toString().equals("int")){
			FieldType.clear();
			FieldType.add("String");
			FieldType.add("void");
			FieldType.add("double");
			FieldType.add("float");
		}else if(theWord.toString().equals("float")){
			FieldType.clear();
			FieldType.add("String");
			FieldType.add("void");
			FieldType.add("double");
			FieldType.add("int");
		}else if(theWord.toString().equals("doube")){
			FieldType.clear();
			FieldType.add("String");
			FieldType.add("void");
			FieldType.add("int");
			FieldType.add("float");
		}
	}
	public static ArrayList<String> getFieldType() {
		return FieldType;
	}
	public static void setFieldType(ArrayList<String> fieldType) {
		FieldType = fieldType;
	}
	public void setListener(Object object) {
		// TODO Auto-generated method stub
		
	}

}
