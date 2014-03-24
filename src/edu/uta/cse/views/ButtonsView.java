package edu.uta.cse.views;


import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import edu.uta.cse.main.CodeExtractor;
import edu.uta.cse.main.TypeStrategy;
import touchdevelopplugin.editors.TDPDoubleClickStrategy;
import touchdevelopplugin.editors.TDPEditor;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ButtonsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "edu.uta.cse.views.ButtonsView";
	public static String[] buttonText = {"Start to analysis"};
	private TableViewer viewer;
	private Action action1;
	private Action action2;
	private CodeExtractor codeFileReader;
	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return ButtonsView.buttonText;
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public ButtonsView() {
		//ASTParser parser = ASTParser.newParser(AST.JLS4);
		codeFileReader = new CodeExtractor();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "TouchDevelopPlugin.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ButtonsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}
	public class IProperty implements IPropertyListener{
		private ButtonsView view;
		public IProperty(ButtonsView view){
			this.view = view;
		}
		@Override
		public void propertyChanged(Object arg0, int arg1) {
			// TODO Auto-generated method stub
			ButtonsView.buttonText = new String[]{"int","String","char"};
			view.viewer.setContentProvider(view.viewer.getContentProvider());
		}
	   	 
    }
	class ISelectionChange implements ISelectionChangedListener {
		TDPEditor editor;
		ButtonsView view;
		public String getSelectedStringFormEditor(TDPEditor editor){
			StringBuffer sb = new StringBuffer("");
			ITextViewer itv = editor.getJavaConfiguration().getDoubleClickStrategy().getfText();
			int caretPos = itv.getSelectedRange().x;
			char c;
			int startPos, endPos;
			IDocument doc = itv.getDocument();
			
			int pos = caretPos;
			
			try {
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
				for(int i=0; i<endPos-startPos-1;i++){
					sb.append(doc.getChar(startPos+1+i));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return sb.toString();
		}

		public ISelectionChange(TDPEditor editor,ButtonsView view){
			this.editor = editor;
			this.view = view;
		}
		@Override
		public void selectionChanged(SelectionChangedEvent arg0) {
			// TODO Auto-generated method stub
			
			/**
			 * add some strategy to decide ButtonsView.buttonText = new String[]{"int","String","char"};
			 */
			TypeStrategy ts = new TypeStrategy();
			
			String context = editor.getJavaConfiguration()
					.getDoubleClickStrategy().getfText().getDocument().get();
		
			if (codeFileReader != null) {
				codeFileReader.setContent(context);
				String s = this.getSelectedStringFormEditor(editor);

				
				ButtonsView.buttonText = ts.doAnalysis(codeFileReader, context, s);
			} else {
				ButtonsView.buttonText = new String[] { "codeFileReader is null" };
			}
			
			view.viewer.setContentProvider(view.viewer.getContentProvider());
		}
	}

	class IDoubleClick implements IDoubleClickListener{

	    ButtonsView view;
	    public IDoubleClick(ButtonsView view){
	    	this.view = view;
	    }
	    
	    public void doubleClick(DoubleClickEvent event) {
	    	TDPEditor editor =(TDPEditor)getSite().getPage().getActiveEditor();
	    	if(editor.getJavaConfiguration().getDoubleClickStrategy().getfText()!=null){
		     try {
				editor.addPropertyListener(new IProperty(this.view));
				editor.getJavaConfiguration().getDoubleClickStrategy().getfText().addTextListener(new ITextListener() {
					
					@Override
					public void textChanged(TextEvent arg0) {
						// TODO Auto-generated method stub
						
						/**
						 * add some strategy to decide ButtonsView.buttonText = new String[]{"int","String","char"};
						 */
						
						ButtonsView.buttonText = new String[]{"textChanged","String","char","char"};
						view.viewer.setContentProvider(view.viewer.getContentProvider());
					}
				});
				editor.getJavaConfiguration().getDoubleClickStrategy().getfText().getSelectionProvider().addSelectionChangedListener(new ISelectionChange(editor,view));
			
				
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     this.repalceMethodName("String");
		 }
		     
	    }
	    private void repalceMethodName(String s){
		     TDPEditor view = (TDPEditor)getSite().getPage().getActiveEditor();
		     int start = view.getJavaConfiguration().getDoubleClickStrategy().getStartCursor();
		     int end = view.getJavaConfiguration().getDoubleClickStrategy().getEndCursor();
		     IDocument doc =  view.getJavaConfiguration().getDoubleClickStrategy().getfText().getDocument();
		     String content = doc.get();
		     StringBuffer sb = new StringBuffer("");
		     sb.append(content.substring(0,start+1));
		     sb.append(s);
		     sb.append(content.substring(end, content.length()));
		     doc.set(sb.toString());
		     int offset = start + 1;
			 int length = s.length();
			 view.getJavaConfiguration().getDoubleClickStrategy().getfText().setSelectedRange(offset, length);
		}
	   
	}
	private void hookDoubleClickAction() {
		    viewer.addDoubleClickListener(new IDoubleClick(this));
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Button clicks",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	class ParseThread extends Thread{
		
	}
}