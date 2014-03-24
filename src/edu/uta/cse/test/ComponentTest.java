package edu.uta.cse.test;

import java.util.Properties;
import edu.uta.cse.UIComponent.UIComponent;
import edu.uta.cse.config.Config;;
public class ComponentTest {
	String identifyComponent="type";
	private UIComponent comp ;

	public void excute(){
		Properties p = Config.getInstance().getUIComponentMap();
		String partName = p.getProperty("type-component");
		String completeClassName = new StringBuilder("edu.uta.cse.UIComponent.").append(partName).toString(); 
		System.out.println("completeClassName"+completeClassName);
		try {
			comp = (UIComponent)Class.forName(completeClassName).newInstance();
			comp.excute();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			

}
