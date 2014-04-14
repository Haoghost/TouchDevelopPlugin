package edu.uta.cse.strategy;

import java.util.List;
/**
 * 
 * @author Hao Geng
 * <p>
 * Modified by: Hui Zhou
 *
 */
public class ReturnTypeStrategy  extends CodeHandlerStrategy{

	public String[] doAnalysis(String context, String selectedWord) {
		CodeExtractor extractor = new CodeExtractor(context);
		List<String> allTypes = extractor.getMethodReturnType(selectedWord);
		if (allTypes.contains(selectedWord))
			allTypes.remove(selectedWord);
		if (allTypes.contains("void"))
			allTypes.remove("void");
		
		String[] buttontext = new String[allTypes.size()];
		for (int i = 0; i < allTypes.size(); i++) {
			buttontext[i] = allTypes.get(i);
		}
		return buttontext;
	}

}
