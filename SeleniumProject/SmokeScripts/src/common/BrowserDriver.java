package common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import Application_Specific_Library.Driver;

public class BrowserDriver extends Driver {
   
	private static WebDriver mDriver;
	
	private static WebDriver getDriver() throws Exception
	{
		if (mDriver==null) {
			try {
                mDriver = BrowserFactory.getBrowser();
	        } catch (UnreachableBrowserException e) {
	        	ErrorContainer.add("failed");
	            mDriver = BrowserFactory.getBrowser();
	        } catch (WebDriverException e) {
	        	ErrorContainer.add("failed");
	            mDriver = BrowserFactory.getBrowser(); 
	        }
			finally{
	        	Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
	        }
		}
        return mDriver;
	}
	
	public synchronized static WebDriver getCurrentDriver() {
		
		try {
			getDriver();
		} 
		catch (Exception e) 
		{
			ErrorContainer.add("failed");
			
			System.out.println("Failed to create WebDriver");
		}

		return mDriver;
    }
	
	public static void close() {
        if( mDriver != null) try {
            getCurrentDriver().quit();
            mDriver = null;
            System.out.println("closing the browser");
        } catch (UnreachableBrowserException e) {
        	ErrorContainer.add("failed");
        	
        	System.out.println("cannot close browser: unreachable browser");
        }
    }
	
	private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }
	
}

