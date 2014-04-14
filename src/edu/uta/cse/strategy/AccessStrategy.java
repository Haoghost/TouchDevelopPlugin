package edu.uta.cse.strategy;

import java.util.List;

/**
 * 
 * @author Shuang Guo
 *
 */
public class AccessStrategy extends CodeHandlerStrategy{

	@Override
	public String[] doAnalysis(String contex, String selectedWord) {
		// There are only three kinds of modifiers.
		CodeExtractor extractor = new CodeExtractor(contex);
		List<String> allIdentifiers = extractor.getAccessIdentifierType(selectedWord);
		if (allIdentifiers.contains(selectedWord))  
			allIdentifiers.remove(selectedWord);
		String[] buttontext = new String[allIdentifiers.size()] ;
		for (int i = 0; i < allIdentifiers.size(); i++) {
			buttontext[i] = allIdentifiers.get(i);
		}
		return buttontext;
	}

}
