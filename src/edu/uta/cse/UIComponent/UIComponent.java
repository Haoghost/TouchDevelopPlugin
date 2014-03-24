package edu.uta.cse.UIComponent;

public abstract class UIComponent {
	public String identifyComponent;
	/**
	 * load content of UIComponent for file
	 */
	public void loadComponent(){};
	/**
	 * get UIComponent for uses
	 * @return
	 */
	public String[] getUIComponent(){
		return null;
	}
	/**
	 * user can set content of UIComponent
	 *
	 */
	public void setUIComponent(){};
	/**
	 * user can set dynamic content of UIComponent
	 * @param contentResult get form different strategies
	 */
	public void setDynamicUIComponent(String identifyComponent){};
	public String[] excute(){return "".split("");};
}
