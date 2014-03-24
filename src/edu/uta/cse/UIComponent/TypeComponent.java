package edu.uta.cse.UIComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TypeComponent extends UIComponent{
	String[] component  ;
	private Properties propertiesForType;
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
		propertiesForType = new Properties();
		try {
			propertiesForType.load(new FileInputStream("type.properties"));
			identifyComponent = propertiesForType.getProperty("type");
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
