package touchdevelopplugin.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class JAVAWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
