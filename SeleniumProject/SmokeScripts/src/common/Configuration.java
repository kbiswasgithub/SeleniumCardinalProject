package common;

import java.util.Properties;

import org.openqa.selenium.security.UserAndPassword;

public class Configuration
{
	private static Properties props = DefaultParameter.getProps();
	
	public static UserAndPassword getTestUser( String userClass)
	{
		return props != null ? new UserAndPassword( props.getProperty("user"), props.getProperty("password")) : null;
	}
	
	public static String getAppUrl()
	{
		return props.getProperty("appurl");
	}
	
	public static String getDefaultBrowser()
	{
		return props.getProperty("defaultbrowser");
	}
	
	public static boolean isGridConfig()
	{
		String gridConfig = props.getProperty("grid");
		return gridConfig != null ? gridConfig.equalsIgnoreCase("yes") : false;
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
