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







import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import atu.alm.wrapper.exceptions.ALMServiceException;
import common.BrowserDriver;
import common.Reporter;
import common.readexcel;
import jxl.read.biff.BiffException;
import common.BaseNavigations;
import common.BaseNavigations.Login;


public class Driver {
	
	public static WebContainer currentPage = null;
	public static RegReportinHomePage regReporthomePage = null;
	public static RegReportPage regreportPage = null;
	
	//for ADC
	
	public static ADCHomePage ADChomePage = null;
	//private static ADCPage Adcpage;
	//String DEANum = null;
	
	
	public static SendMail sendMail = null;
	public static PmodMDAPrdWM pmodMDA = null;
	
	public static String DEANum=null;
	public static int rowcounter=0;
	public static List ErrorContainer = new ArrayList();
	readexcel getRow =new readexcel();
	public static int totalRow=0;	
	public int Filecounter=0;
	
	
	
	
	@BeforeTest
	public void fetchdata() throws BiffException, IOException{
		
		totalRow=getRow.getTotalUsedRow();
		String sTestCaseName ="URLHealthCheck";
	    Reporter.scriptname=sTestCaseName;
	    File dir = new File("C:\\TEMP\\" + sTestCaseName);
	    dir.mkdir();
	    String sFileName = "C:\\TEMP\\" + sTestCaseName + "\\Report.html";
	    Reporter.rep_Open_File(sFileName); 
	    BaseNavigations.Setrowcount(rowcounter);
		
		 
		//
		
		 String linkname =  readexcel.FetchValueFromExcel("TestCase", rowcounter).trim();
		 
		 String sectionName = readexcel.FetchValueFromExcel("URL", rowcounter).trim();
		 //ADC DEA Number
		 DEANum = readexcel.FetchValueFromExcel("TestData", rowcounter).trim();
		 System.out.println("DEANUM " + DEANum);
		 Reporter.rep_Insert_Section(sectionName);
		
	}
	
	@Test
	public void test1()
	
	{
		final Login loginNav = new Login();	
		try{
			
							
			currentPage = loginNav.navigate(null);
			ADCHomePageValidation(rowcounter,"ADC");			
					
		    	
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
	
	@AfterTest
	public void closereport() throws IOException, ALMServiceException
	{
		afterScenario();
		Reporter.rep_Close();
	}

	
	
	
	private static void RegularReportPageValidation(int rowcounter, String PageName){
		try{
			LoginPage loginPage = verifyPage("should be on login page", LoginPage.class);
			regReporthomePage = loginPage.loginAs(rowcounter);
			regreportPage = regReporthomePage.navigateToReports();
			if (regreportPage.validateReportExistence("Ohio")!= null){ 
				System.out.println("Health Check is SuccessFull!!");
				Reporter.ExportResultToHtml("Validation of reports ", "Reports should get generated", "Report is getting generated", "PASS");
				afterScenario();
			}
		}catch (Exception e)
		{
			System.out.println("Health Check is UnSuccessFull!!");
			ErrorContainer.add("IDM Stage Failed");
			
			try{
				Reporter.ExportResultToHtml("Validation of reports ", "Reports should get generated", "Report is not getting generated", "FAIL");
			}catch (Exception e1)
			{
				System.out.println("Home Page is not opened ");
				ErrorContainer.add("IDM Stage Failed");
			}
			
			afterScenario();
		}
		}
		
		
	
		
	
	
	private static void ADCHomePageValidation(int rowcounter, String PageName){
		try{
			LoginPage loginPage = verifyPage("should be on ADC login page", LoginPage.class);
			ADChomePage = loginPage.ADCLogin(rowcounter);
			ADChomePage.validateCustomerSearchTablePresence(DEANum);
			afterScenario();
			
		}catch (Exception e)
		{
			System.out.println("Health Check is UnSuccessFull!!");
			ErrorContainer.add("IDM Stage Failed");
			
			try{
				Reporter.ExportResultToHtml("Validation of reports ", "Reports should get generated", "Report is not getting generated", "FAIL");
			}catch (Exception e1)
			{
				System.out.println("Home Page is not opened ");
				ErrorContainer.add("IDM Stage Failed");
			}
			
			afterScenario();
		}
		}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	private static <T> T verifyPage(String message, Class<T> expectedPage) {
		assertTrue(message, currentPage.getClass().isAssignableFrom(expectedPage));
		return (T) currentPage;
	}
	

	public static void afterScenario() {
		
		System.out.println("Shutting down browser");
		BrowserDriver.close();
		currentPage = null;
		
	}

}
