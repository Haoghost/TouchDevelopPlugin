package edu.uta.cse.config;
/**
 * @author zhangbo
 * this Config is a singleton class, invoking this class can load properties file
 *  which can help user create different UIcomponent.
 * @date 3/23/2014
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Config instance;
	private static Properties UIComponentMap;
	
	/**
	 * constructor for Config Class.
	 */
	private Config()
	{
		loadUIComponent();
	}
	
	/**
	 * singleton pattern for get instance of Config
	 * @return instance of Config
	 */
	public static Config getInstance() 
	{
        if (null == instance) 
        {
            synchronized (Config.class)
            {
            	  if (null == instance) 
	              {
	            	  instance = new Config();
	            	  
	              }
            }
        }
        return instance;
    }
	/**
	 * This method is used for load properties file
	 */
	public void loadUIComponent()
	{
		UIComponentMap = new Properties();
		try {
			UIComponentMap.load(new FileInputStream("uicomponentMap.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * get properties file
	 * @return get UIComponentMap
	 */
	public Properties getUIComponentMap()
	{
		return UIComponentMap;
	}
	
}
