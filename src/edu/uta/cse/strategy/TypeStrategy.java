package edu.uta.cse.strategy;

//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import java.util.List;
/**
 * 
 * @author Hao Geng
 * <p>
 * Modified by: Hui Zhou
 *
 */
public class TypeStrategy extends CodeHandlerStrategy {

	@Override
	public String[] doAnalysis(String context,
			String selectedWord) {
		CodeExtractor extractor = new  CodeExtractor(context);
		List<String> allTypes = extractor.getMethodReturnType(selectedWord);
		if (allTypes.contains(selectedWord))
			allTypes.remove(selectedWord);
		/*if(allTypes.contains("null"))
			allTypes.remove("null");*/
		
		String[] buttontext = new String[allTypes.size()] ;
		for (int i = 0; i < allTypes.size(); i++) {
			buttontext[i] = allTypes.get(i);
		}
		return buttontext;
	}

}
