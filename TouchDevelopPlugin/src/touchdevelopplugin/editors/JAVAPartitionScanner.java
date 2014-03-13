package touchdevelopplugin.editors;

import org.eclipse.jface.text.rules.*;

public class JAVAPartitionScanner extends RuleBasedPartitionScanner {
	public final static String JAVA_COMMENT = "__xml_comment";
	public final static String JAVA_TAG = "__xml_tag";

	public JAVAPartitionScanner() {

		IToken xmlComment = new Token(JAVA_COMMENT);
		IToken tag = new Token(JAVA_TAG);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new TagRule(tag);

		setPredicateRules(rules);
	}
}
