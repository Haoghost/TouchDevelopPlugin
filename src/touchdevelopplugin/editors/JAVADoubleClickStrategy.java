package touchdevelopplugin.editors;



import org.eclipse.jface.text.*;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;

import edu.uta.cse.views.ButtonsView;


public class JAVADoubleClickStrategy implements ITextDoubleClickStrategy {
	protected ITextViewer fText;
	//protected DialogTest dt;
	//int startP,endP;
	//String theWord;

	public void doubleClicked(ITextViewer part) {
		int pos = part.getSelectedRange().x;

		if (pos < 0)
			return;

		fText = part;
		
		if (!selectComment(pos)) {
			selectWord(pos);
			try {
				selectOneWord(part);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//dt = new DialogTest();
			//dt.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dt.setVisible(true);
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
	protected boolean selectWord(int caretPos) {

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
			
			return true;

		} catch (BadLocationException x) {
		}


		return false;
	}
	
	//this method select the word
	public void selectOneWord(ITextViewer part) throws BadLocationException{
		
		int currentPos = part.getSelectedRange().x;
		//int currentPos = position;
		//System.out.println(currentPos);
		int startPos, endPos;
		int startP=0;
		int endP=0;

		

			int pos = currentPos;
			char c;

			while (pos >= 0) {
				c = fText.getDocument().getChar(pos);
				if (!Character.isJavaIdentifierPart(c))
				{
					//System.out.println("!!!!!!!!"+c);
					break;
				}else{
					//System.out.println("########"+c);
					startPos = pos;
					startP = startPos;
					//System.out.println("$$$$$$$$"+startPos);
				     --pos;
				}
			}
			
			pos = currentPos;
			int length = fText.getDocument().getLength();

			while (pos < length) {
				c = fText.getDocument().getChar(pos);
				if (!Character.isJavaIdentifierPart(c))
				{
					break;
				}else{
					endPos = pos;
					endP = endPos;
					++pos;
				}
			}

			
//			System.out.println("start:   " + startP +"   "+"end:   "+ endP );
//			String selectedWord = fText.getDocument().get(startP,endP+1);
			String theWord="";
			for(int i = startP; i<=endP; i++){
			char selectedLetter = fText.getDocument().getChar(i);
			String selectedWord = "";
			selectedWord = selectedWord+selectedLetter;
			theWord += selectedWord;
			}
//			System.out.println(theWord);
			
		//System.out.println(abc);
		//return theWord;
			if(theWord.equals("class")){
				ViewContentProvider bv = new ViewContentProvider();

			}
		
	}

	private void selectRange(int startPos, int stopPos) {
		int offset = startPos + 1;
		int length = stopPos - offset;
		fText.setSelectedRange(offset, length);
		
	}
}