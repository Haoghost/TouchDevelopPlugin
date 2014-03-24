package touchdevelopplugin.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class TDPWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
