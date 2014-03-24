package edu.uta.cse.pluginFactory;

import java.util.Properties;

import edu.uta.cse.UIComponent.UIComponent;
import edu.uta.cse.config.Config;

public class AccessComponentCreater implements PluginFactory{
	private UIComponent comp ;
	@Override
	public UIComponent createUIComponent() {
		// TODO Auto-generated method stub
		Properties p = Config.getInstance().getUIComponentMap();
		String partName = p.getProperty("access-component");
		String completeClassName = new StringBuilder("edu.uta.cse.UIComponent.").append(partName).toString(); 
		try {
			comp = (UIComponent)Class.forName(completeClassName).newInstance();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comp;
	}
}
