package edu.uta.cse.UIComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AccessComponent extends UIComponent{
	String[] component  ;
	private Properties propertiesForAccess;
	@Override
	public String[] getUIComponent() {
		return this.component;
	}

	@Override
	public void setUIComponent() {
		this.component = identifyComponent.split(",");
	}
	@Override
	public void setDynamicUIComponent(String identifyComponent) {
		// TODO Auto-generated method stub
		super.setDynamicUIComponent(identifyComponent);
		this.component = identifyComponent.split(",");
	}
	@Override
	public void loadComponent() {
		propertiesForAccess = new Properties();
		try {
			propertiesForAccess.load(new FileInputStream("access.properties"));
			identifyComponent = propertiesForAccess.getProperty("access");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public String[] excute() {
		// TODO Auto-generated method stub
		super.excute();
		this.loadComponent();
		this.setUIComponent();
		return this.getUIComponent();
	}
	
}

