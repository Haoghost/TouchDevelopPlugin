package edu.uta.cse.strategy;

import java.util.List;

import edu.uta.cse.views.ButtonsView;

/**
 * 
 * @author Bo Zhang
 *
 */
public class VariableStrategy extends CodeHandlerStrategy{

	@Override
	public String[] doAnalysis(String context, String selectedWord) {
		CodeExtractor extractor = new CodeExtractor(context);
		List<String> variables = extractor.getGlobalFields();
		variables.addAll(extractor.getLocalFields(ButtonsView.MethodName.toString()));
		variables.add(";");
		variables.add("+");
		variables.add("-");
		variables.add("/");
		variables.add("*");
		String[] buttontext = new String[variables.size()] ;
		for (int i = 0; i < variables.size(); i++) {
			buttontext[i] = variables.get(i);
		}
		return buttontext;
	}

}
