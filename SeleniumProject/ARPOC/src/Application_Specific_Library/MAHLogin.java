package Application_Specific_Library;

import java.io.IOException;

import org.openqa.selenium.By;

import common.BrowserDriver;
import common.PollingClick;
import common.readexcel;
import jxl.read.biff.BiffException;

public class MAHLogin extends WebContainer {
	private static String pageTitle = "Cardinal Health Enterprise Login Page";
	private static Verification verification = new Verification(VerificationType.TITLE, "Cardinal Health Enterprise Login Page");
	private static By usernameLocator = By.id("USER");
	private static By passwordLocator = By.id("PASSWORD");
	private static By loginButtonLocator = By.xpath("//span[contains(text(),'Sign In')]");
	
	public MAHLogin() throws Exception {
		super(verification, pageTitle);
	}

	public static HomePage loginAs(int ScriptRow) throws BiffException, IOException, InterruptedException {
		
		int TestCaseRow=ScriptRow;
		Thread.sleep(5000);
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		Thread.sleep(5000);
		return new HomePage();
		
	}

	
	
	
	

	}
