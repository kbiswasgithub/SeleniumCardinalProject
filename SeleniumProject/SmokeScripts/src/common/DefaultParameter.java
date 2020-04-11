package common;

import java.io.IOException;
import java.util.Properties;

import Application_Specific_Library.Driver;

public class DefaultParameter extends Driver
{
  //  private static final Logger logger = LogManager.getLogger();

	private final static String propFileName = "defaults.properties";
	private static Properties defaultProps = null;
	
	public static Properties getProps()
	{
		if( defaultProps == null)
		{
			defaultProps = new Properties();
			try
			{
				defaultProps.load(DefaultParameter.class.getClassLoader().getResourceAsStream(propFileName));
			} catch( IOException e)
			{
				ErrorContainer.add("CAH URL CHECK IS FAILED");
				System.out.println("Failed to open " + propFileName);
			}
		}
		
		return defaultProps;
	}
	
	private final boolean isIndex;
	private final String value;
		
	public DefaultParameter(String defaultValue, boolean isSelectBoxIndex) {
		isIndex = isSelectBoxIndex;
		value = defaultValue;
	}

	public boolean isIndex()
	{
		return isIndex;
	}

	public String getValue()
	{
		if( value == null)	System.out.println( "WARNING:  returning default value of null");
		
		return value;
	}
}
