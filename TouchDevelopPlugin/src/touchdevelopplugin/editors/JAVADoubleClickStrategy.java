package touchdevelopplugin.editors;

import javax.swing.JDialog;

import org.eclipse.jface.text.*;

import edu.uta.cse.views.DialogTest;

public class JAVADoubleClickStrategy implements ITextDoubleClickStrategy {
	protected ITextViewer fText;
	public static int StartCursor;
	public int getStartCursor() {
		return StartCursor;
	}
	public void setStartCursor(int startCursor) {
		StartCursor = startCursor;
	}
	public int getEndCursor() {
		return EndCursor;
	}
	public void setEndCursor(int endCursor) {
		EndCursor = endCursor;
	}

	public static int EndCursor;
	public ITextViewer getfText() {
		return fText;
	}
	public void setfText(ITextViewer fText) {
		this.fText = fText;
	}
	public void doubleClicked(ITextViewer part) {
		int pos = part.getSelectedRange().x;

		if (pos < 0)
			return;

		fText = part;
		
		if (!selectComment(pos)) {
			selectWord(pos);
			
			
		}
	}
	protected boolean selectComment(int caretPos) {
		IDocument doc = fText.getDocument();
		int startPos, endPos;

		try {
			int pos = caretPos;
			char c = ' ';

			while (pos >= 0) {
				c = doc.getChar(pos);
				if (c == '\\') {
					pos -= 2;
					continue;
				}
				if (c == Character.LINE_SEPARATOR || c == '\"')
					break;
				--pos;
			}

			if (c != '\"')
				return false;

			startPos = pos;

			pos = caretPos;
			int length = doc.getLength();
			c = ' ';

			while (pos < length) {
				c = doc.getChar(pos);
				if (c == Character.LINE_SEPARATOR || c == '\"')
					break;
				++pos;
			}
			if (c != '\"')
				return false;

			endPos = pos;

			int offset = startPos + 1;
			int len = endPos - offset;
			fText.setSelectedRange(offset, len);
			return true;
		} catch (BadLocationException x) {
		}

		return false;
	}
	/**
	
	system.out.print
	
	*/
	private void print(String selectedWord){
		System.out.print(selectedWord);
	}
	protected boolean selectWord(int caretPos) {
		StringBuffer sb = new StringBuffer("");
		IDocument doc = fText.getDocument();
		int startPos, endPos;

		try {
			int pos = caretPos;
			char c;

			while (pos >= 0) {
				c = doc.getChar(pos);
				if (!Character.isJavaIdentifierPart(c))
					break;
				--pos;
			}
			startPos = pos;
			pos = caretPos;
			int length = doc.getLength();

			while (pos < length) {
				c = doc.getChar(pos);
				if (!Character.isJavaIdentifierPart(c))
					break;
				++pos;
			}
			endPos = pos;
			selectRange(startPos, endPos);
			this.setStartCursor(startPos);
			this.setEndCursor(endPos);
			for(int i=0; i<endPos-startPos-1;i++){
				sb.append(doc.getChar(startPos+1+i));
			}
			System.out.println(sb);
			return true;

		} catch (BadLocationException x) {
		}
		
		return false;
	}

	private void selectRange(int startPos, int stopPos) {
		int offset = startPos + 1;
		int length = stopPos - offset;
		fText.setSelectedRange(offset, length);
		
	}
}