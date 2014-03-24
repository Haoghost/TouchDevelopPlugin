package touchdevelopplugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class TDPScanner extends RuleBasedScanner {

	public TDPScanner(TDPColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(TDPColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new TDPWhitespaceDetector());

		setRules(rules);
	}
}
