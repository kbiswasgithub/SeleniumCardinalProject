package Application_Specific_Library;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import common.BrowserDriver;
import common.Reporter;
import common.readexcel;
import jxl.read.biff.BiffException;
import common.BaseNavigations;
import common.BaseNavigations.Login;


public class Driver {

	public static WebContainer currentPage = null;
	//private  static DashBoardPage dashBoardPage = null;
	private  static HomePage homePage = null;
	private  static ItemPage itmPage = null;
	//private static BusinessReview brPage = null;
	//private static SendMail sendMail = null;
	//private static purchaseReview preview = null;
	//private static String [] BRtabNames={"How To", "Dashboard", "Product Movement Report","Invoice Report","Unique Items Report","Inflation/Deflation Report", "Glossary"};
	public static int rowcounter;
	//public static Logger logger = Logger.getLogger(Driver.class);
	public static List ErrorContainer = new ArrayList();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//String log4jConfigFile = System.getProperty("user.dir")
				//+ File.separator + "log4j.xml";
		
		//System.out.println(log4jConfigFile);
		
		//DOMConfigurator.configure(log4jConfigFile);
		
		readexcel getRow =new readexcel();
		int totalRow;
		int rowcounter;
		int Filecounter;
		Filecounter=0;
		totalRow=getRow.getTotalUsedRow();
		 String sTestCaseName ="URLHealthCheck";
	     Reporter.scriptname=sTestCaseName;
	   
	     File dir = new File("C:\\windows\\TEMP\\" + sTestCaseName);
	     dir.mkdir();
	     String sFileName = "C:\\windows\\TEMP\\" + sTestCaseName + "\\Report.html";
	     Reporter.rep_Open_File(sFileName); 
	     //String sFileName = "C:\\TEMP\\" + sTestCaseName + "\\Report.html";
		for (rowcounter=1; rowcounter<totalRow; rowcounter++)
		{
			BaseNavigations.Setrowcount(rowcounter);
			
			 final Login loginNav=new Login();
			
			
			 String linkname =  readexcel.FetchValueFromExcel("TestCase", rowcounter).trim();
			 
			 String sectionName = readexcel.FetchValueFromExcel("URL", rowcounter).trim();
			 Reporter.rep_Insert_Section(sectionName);
			try{
				
				//**************BR 1st scneario 
				/*
						currentPage = loginNav.navigate(null);
						LoginPage loginPage = verifyPage("should be on login page", LoginPage.class);
						dashBoardPage = loginPage.loginAs(rowcounter);
						dashBoardPage.selectOptionAndValidate("All");
						preview = dashBoardPage.navigateToTab("Purchases Review");
						preview.PR_UIValidation(BRtabNames);
						Reporter.rep_Close();
						
						afterScenario();
					*/	
					
					
			
				/*
						LoginPage loginPage = loginNav.navigate(null);
						
						dashBoardPage = loginPage.loginAs(rowcounter);
						
						preview = dashBoardPage.navigateToTab("Purchases Review");
						preview.PR_UIValidation(BRtabNames);
						Reporter.rep_Close();
						
						afterScenario();
				*/	
				//LoginPage loginPage = loginNav.navigate(null);
				MAHLogin loginPage = loginNav.navigate(null);
				homePage = loginPage.loginAs(rowcounter);
				itmPage=homePage.navigateToMenu();
				itmPage.searchByItem("1000116");
				itmPage.verifyItem("1000116");				
				Reporter.rep_Close();
						
						
				

			    	
			} catch (Exception e){
				try{
				Reporter.ExportResultToHtml("IDM HEALTH CHECK", "Login Should Be Successfull", "Login is Not Successfull", "FAILED");
				}catch (Exception e1)
				{
					System.out.println("Home Page is not opened ");
					ErrorContainer.add("CAH URL CHECK IS FAILED");
				}
				
				afterScenario();
			}
				
		}		
				
			
		
	
	}	
	
		
	
	
	@SuppressWarnings("unchecked")
	private static <T> T verifyPage(String message, Class<T> expectedPage) {
		assertTrue(message, currentPage.getClass().isAssignableFrom(expectedPage));
		return (T) currentPage;
	}
	

	@After
	public static void afterScenario() {
		
		System.out.println("Shutting down browser");
		BrowserDriver.close();
		currentPage = null;
		
	}

}
