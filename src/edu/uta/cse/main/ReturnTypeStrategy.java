package edu.uta.cse.main;

import java.util.List;

public class ReturnTypeStrategy  extends CodeHandlerStrategy{

public String[] doAnalysis(CodeFileReader cfr, String contex,String selectedWord) {
	
	List<String> allTypes = cfr.getMethodReturnType(selectedWord);
	if (allTypes.contains(selectedWord))
		allTypes.remove(selectedWord);
	if(allTypes.contains("void"))
		allTypes.remove("void");
	// TODO Auto-generated method stub
	String[] buttontext = new String[allTypes.size()] ;
	for (int i = 0; i < allTypes.size(); i++) {
		buttontext[i] = allTypes.get(i);
	}
	return buttontext;
}

}
