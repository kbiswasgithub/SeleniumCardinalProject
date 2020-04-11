package common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import Application_Specific_Library.Driver;


public class BrowserFactory extends Driver {
	
    

	
	public enum Browsers {

		FIREFOX,
		CHROME,
		SAFARI,
		INTERNET_EXPLORER;

		public static Browsers browserForName(String browser) throws IllegalArgumentException{
	        for(Browsers b: values()){
	    		if(b.toString().equalsIgnoreCase(browser)){
	    			return b;
	    		}
	        }
	        throw browserNotFound(browser);
	    }

	    private static IllegalArgumentException browserNotFound(String outcome) {
	    	ErrorContainer.add("CAH URL CHECK IS FAILED");
	    	//logger.info("CAH URL Health Check is Failed");
	        return new IllegalArgumentException(("Invalid browser [" + outcome + "]"));
	    }
	}
	
	private static File getFile(String filename) throws URISyntaxException {
        File file = new File(filename);
        if (!file.exists()){
        	file = new File(BrowserFactory.class.getClassLoader().getResource(filename).toURI());
        }
        return (file != null && file.exists() ? file : null);
    }
		
	private static Browsers getBrowserName()
	{
		final String BROWSER_PROP_KEY = "browser";
		
		String browserName = System.getProperty(BROWSER_PROP_KEY);
		if( browserName == null)	browserName = Configuration.getDefaultBrowser();
		
		return Browsers.browserForName(browserName);					
	}
	
	/**
	 * creates the browser driver specified in the system property "browser"
	 * if no property is set then a firefox browser driver is created.
	 * The allow properties are firefox, safari and chrome
	 * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
	 * @return WebDriver
	 * @throws Exception 
	 */
	private static WebDriver getDirectBrowser() throws Exception 
	{
		WebDriver driver;

		switch(getBrowserName())
		{
			case CHROME:
				driver = createChromeDriver();
				break;
			case SAFARI:
				driver = createSafariDriver();
				break;
			case INTERNET_EXPLORER:
				driver = createIeDriver();
				break;
			case FIREFOX:
			default:
				driver = createFirefoxDriver(getFirefoxProfile());
				break;
		}
		addAllBrowserSetup(driver);
		return driver;
	}
	
	private static RemoteWebDriver getGridHub()
	{
		DesiredCapabilities capability; 
		RemoteWebDriver driver = null;
		
		switch(getBrowserName())
		{
			case CHROME:
				capability = DesiredCapabilities.chrome();	    
				ChromeOptions options = new ChromeOptions();
			    options.addArguments("test-type");
			    capability.setCapability(ChromeOptions.CAPABILITY, options);
				break;
			case SAFARI:
				capability = DesiredCapabilities.safari();
				break;
			case INTERNET_EXPLORER:
				capability = DesiredCapabilities.internetExplorer();
				break;
			case FIREFOX:
			default:
				capability = DesiredCapabilities.firefox();
				break;
		}
		
		driver = new RemoteWebDriver(capability);
		addAllBrowserSetup( driver);
		
		return driver;
	}
	
	public static WebDriver getBrowser() throws Exception 
	{
		return Configuration.isGridConfig() ? getGridHub() : getDirectBrowser();
	}


	private static WebDriver createSafariDriver() {
		return new SafariDriver();
	}
	
	private static WebDriver createIeDriver() throws Exception
	{
		final String DRIVER_PROP = "webdriver.ie.driver";
		if( SystemUtils.IS_OS_WINDOWS)
		{
			File file = new File("src/windows/IEDriverServer.exe");
			System.setProperty( DRIVER_PROP, file.getAbsolutePath());
		}
		else
		{
			ErrorContainer.add("failed");
		//	logger.info("CAH URL Health Check is Failed");
			throw new Exception( "Unsupported OS");
		}

		System.out.println("Creating browser:  driver = " + System.getProperty(DRIVER_PROP));
		return new InternetExplorerDriver();
	}

	private static WebDriver createChromeDriver() throws Exception {
		final String DRIVER_PROP = "webdriver.chrome.driver";

		if( SystemUtils.IS_OS_MAC)
		{
			//System.setProperty( DRIVER_PROP, "src/windows/chromedriver");
			System.setProperty( DRIVER_PROP, "C:\\Windows\\Temp\\chromedriver.exe");
		}
		else if( SystemUtils.IS_OS_WINDOWS)
		{
			//System.setProperty( DRIVER_PROP, "src/windows/chromedriver.exe");
			System.setProperty( DRIVER_PROP, "C:\\Windows\\Temp\\chromedriver.exe");
		}
		else if( SystemUtils.IS_OS_LINUX)
		{
			System.setProperty( DRIVER_PROP, "src/main/resources/linux/chromedriver");
		}
		else
		{
			ErrorContainer.add("failed");
		//	logger.info("CAH URL Health Check is Failed");
			throw new Exception( "Unrecognized OS");
			
		}
		
		System.out.println("Creating browser:  driver = " + System.getProperty(DRIVER_PROP));
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("test-type");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);
		}
	
	private static WebDriver createFirefoxDriver(FirefoxProfile firefoxProfile) {
		System.setProperty("webdriver.firefox.marionette","C:\\Temp\\geckodriver.exe");
    	return new FirefoxDriver(firefoxProfile);
    }

	private static FirefoxProfile getFirefoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        try {
			firefoxProfile.addExtension(getFile("src/firebug/firebug-1.9.2.xpi"));
        } catch (IOException e) {
        	
            e.printStackTrace();
        } catch (URISyntaxException e){
        	e.printStackTrace();
        }

        //See http://getfirebug.com/wiki/index.php/Firebug_Preferences
        firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2");  // Avoid startup screen
        firefoxProfile.setPreference("extensions.firebug.script.enableSites", true);
        firefoxProfile.setPreference("extensions.firebug.console.enableSites", true);
        firefoxProfile.setPreference("extensions.firebug.allPagesActivation", true);
        firefoxProfile.setPreference("extensions.firebug.delayLoad", false);
        return firefoxProfile;
    }
	
	private static void addAllBrowserSetup(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        //java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        //driver.manage().window().setSize(dim);
	}

}
