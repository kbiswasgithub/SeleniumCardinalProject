package Application_Specific_Library;

import java.io.IOException;

import org.openqa.selenium.By;

import common.BrowserDriver;
import common.PollingClick;
import common.readexcel;
import jxl.read.biff.BiffException;

public class LoginPage extends WebContainer {
	private final static String pageTitle = "Cardinal Health Enterprise Login Page";
	private final static Verification verification = new Verification(VerificationType.IS_ID_DISPLAYED, "USER");
	private final static By usernameLocator = By.id("USER");
	private final static By passwordLocator = By.id("PASSWORD");
	private final static By loginButtonLocator = By.xpath("//span[text()='Sign In']");
	private final static By forgotPasswordLocator = By.id("forgotPasswordLink");

	public LoginPage() throws Exception {
		super(verification, pageTitle);
	}

	public static RegReportinHomePage loginAs(int ScriptRow) throws BiffException, IOException {
		
		
		int TestCaseRow=ScriptRow;
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		return new RegReportinHomePage();
	}

	
	public static GWSAHomePage GWSALogin(int ScriptRow) throws Exception {
		
		
		int TestCaseRow=ScriptRow;
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		
		return new GWSAHomePage();
	}
   public static ADCHomePage ADCLogin(int ScriptRow) throws Exception {
		
		
		int TestCaseRow=ScriptRow;
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		
		return new ADCHomePage();
	}
	public static XploreHomePage XploreLogin(int ScriptRow) throws BiffException, IOException{
		int TestCaseRow=ScriptRow;
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		
		return new XploreHomePage();
		
	}
	
	
	
	public static PmodMDAPrdWM PmodLogin(int ScriptRow) throws BiffException, IOException {
		
		
		int TestCaseRow=ScriptRow;
		PollingClick.enter(usernameLocator, readexcel.FetchValueFromExcel("USERNAME", TestCaseRow).trim(), "UserName Field");
		PollingClick.enter(passwordLocator, readexcel.FetchValueFromExcel("PASSWORD", TestCaseRow).trim(),"Password Field");
		PollingClick.click(loginButtonLocator ,"Sign In Button");
		
		return new PmodMDAPrdWM();
	}
	
	

	

	}
