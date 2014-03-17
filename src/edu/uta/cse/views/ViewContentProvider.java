package edu.uta.cse.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import edu.uta.cse.main.TypeStrategy;
import edu.uta.cse.util.Constant;

public class ViewContentProvider implements IStructuredContentProvider,

	Listener {

    TypeStrategy input;
    //ListViewer viewer;
   
   public Object[] getElements(Object inputElement) {
		        //return Constant.basicFieldType;
	   			  return TypeStrategy.getFieldType();
		    }
		
		    public void dispose() {

		        if(input != null){
		            input.setListener(null);
		        }
		        input = null;
		
		    }
		
		    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		        viewer = ButtonsView.viewer;
		        input = (TypeStrategy)newInput;
		        input.setListener(this);
		
		    }

			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
				
			}
}
