package common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.security.UserAndPassword;
import Application_Specific_Library.*;
import jxl.read.biff.BiffException;



public class BaseNavigations 
	


{
	public static int row;
	
	public static void Setrowcount(int rownumber){
		row= rownumber;
	}
	
	
	
	public abstract static class Navigation<T extends WebContainer, V extends WebContainer>
	{
		public abstract V navigate( T origin) throws Exception;
	}
	
	public static class Login extends Navigation<WebContainer, MAHLogin> 
	{
		@Override
		public MAHLogin navigate(WebContainer WebContainer) throws Exception
		{
			String URL;
			WebDriver driver = BrowserDriver.getCurrentDriver();
			readexcel excl= new readexcel();
			URL= excl.FetchValueFromExcel("URL",row).trim();
			System.out.println(URL);
			Reporter.initalizWebDriver(driver);			
			driver.get(URL);
			driver.manage().window().maximize();
			return new MAHLogin();
		}

		
		
		
			
		
		

	}
	
	
	
	}
