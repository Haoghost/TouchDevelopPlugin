package edu.uta.cse.main;

//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.part.*;

import edu.uta.cse.util.Constant;
import edu.uta.cse.views.*;
import touchdevelopplugin.editors.*;

public class TypeStrategy{// extends CodeHandlerStrategy {

	private static String[] FieldType=new String[4];
	//@Override
	public static void doAnalysis() {
		// TODO Auto-generated method stub

		JAVAEditor jve = new JAVAEditor();
		//StringBuffer theWord= jve.getJavaConfiguration().getDoubleClickStrategy().getSelectedWord();
		StringBuffer theWord = JAVADoubleClickStrategy.SelectedWord;
		if (theWord.toString().equals("void") ){
			getFieldType()[0]="int";
			getFieldType()[1]="String";
			getFieldType()[2]="double";
			getFieldType()[3]="float";

		}else{
			getFieldType()[0]="123";
			getFieldType()[1]="";
			getFieldType()[2]="";
			getFieldType()[3]="";
		}

	}
	public static String[] getFieldType() {
		return FieldType;
	}
	public static void setFieldType(String[] fieldType) {
		FieldType = fieldType;
	}
	public void setListener(Object object) {
		// TODO Auto-generated method stub
		
	}

}
