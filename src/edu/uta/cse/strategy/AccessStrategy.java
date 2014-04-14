package edu.uta.cse.strategy;
/**
 * 
 * @author Hui Zhou
 *
 */
public class AccessStrategy extends CodeHandlerStrategy{

	@Override
	public String[] doAnalysis(String contex, String selectedWord) {
		// There are only three kinds of modifiers.
		String[] modifier = new String[]{"public","private","protect"};
		return modifier;
	}

}
