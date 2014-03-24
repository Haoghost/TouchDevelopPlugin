package edu.uta.cse.main;

//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.part.*;

import edu.uta.cse.util.Constant;
import edu.uta.cse.views.*;
import touchdevelopplugin.editors.*;

public class TypeStrategy extends CodeHandlerStrategy {

	@Override
	public String[] doAnalysis(CodeFileReader cfr, String contex,
			String selectedWord) {
		List<String> allTypes = cfr.getMethodReturnType(selectedWord);
		if (allTypes.contains(selectedWord))
			allTypes.remove(selectedWord);
		if(allTypes.contains("null"))
			allTypes.remove("null");
		// TODO Auto-generated method stub
		String[] buttontext = new String[allTypes.size()] ;
		for (int i = 0; i < allTypes.size(); i++) {
			buttontext[i] = allTypes.get(i);
		}
		return buttontext;
	}

}
