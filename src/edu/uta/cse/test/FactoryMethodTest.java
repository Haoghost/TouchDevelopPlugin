package edu.uta.cse.test;

import edu.uta.cse.UIComponent.*;
import edu.uta.cse.pluginFactory.*;
import edu.uta.cse.pluginFactory.PluginFactory;

public class FactoryMethodTest {
	public void testFactoryMethod(){
		
		PluginFactory pf = new AccessComponentCreater();
		AccessComponent ac = (AccessComponent)pf.createUIComponent();
		pf = new TypeComponentCreater();
		TypeComponent tc = (TypeComponent)pf.createUIComponent();
		pf = new VariableComponentCreater();
		VariableComponent vc = (VariableComponent)pf.createUIComponent();
		this.printResult(ac.excute());
		this.printResult(tc.excute());
		this.printResult(vc.excute());
		
	}
	public void printResult(String[] result){
		for(String ss:result){
			System.out.println(ss);
		}
	}
	public static void main(String[] args) {
		FactoryMethodTest fmt = new FactoryMethodTest();
		fmt.testFactoryMethod();
	}
}
