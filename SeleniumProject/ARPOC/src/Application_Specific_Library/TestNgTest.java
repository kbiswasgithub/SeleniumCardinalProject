package Application_Specific_Library;

//import static org.junit.Assert.assertTrue;

//import static org.testng.Assert.assertTrue;

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


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.BrowserDriver;
import common.Reporter;
import common.readexcel;
import jxl.read.biff.BiffException;
import common.BaseNavigations;
import common.BaseNavigations.Login;


public class TestNgTest {

	public static WebContainer currentPage = null;
	//private  static DashBoardPage dashBoardPage = null;
	private  static HomePage homePage = null;
	private  static ItemPage itmPage = null;
	public static MAHLogin loginPage=null;
	public static Login loginNav=null;
	//private static BusinessReview brPage = null;
	//private static SendMail sendMail = null;
	//private static purchaseReview preview = null;
	//private static String [] BRtabNames={"How To", "Dashboard", "Product Movement Report","Invoice Report","Unique Items Report","Inflation/Deflation Report", "Glossary"};
	public static int rowcounter= 1;
	//public static int initrowcounter=1;
	public static int totalRow;
	//int rowcounter;
	public  int Filecounter;
	//public static Logger logger = Logger.getLogger(Driver.class);
	public static List ErrorContainer = new ArrayList();
	
	@BeforeMethod
	public void beforeTest() throws BiffException, IOException{
		readexcel getRow =new readexcel();
		
		Filecounter=0;
		totalRow=getRow.getTotalUsedRow();
		 String sTestCaseName ="URLHealthCheck";
	     Reporter.scriptname=sTestCaseName;
	   
	     File dir = new File("C:\\windows\\TEMP\\" + sTestCaseName);
	     dir.mkdir();
	     String sFileName = "C:\\windows\\TEMP\\" + sTestCaseName + "\\Report.html";
	     Reporter.rep_Open_File(sFileName); 
	     
			BaseNavigations.Setrowcount(rowcounter);
			
			 loginNav=new Login();
			
			
			 String linkname =  readexcel.FetchValueFromExcel("TestCase", rowcounter).trim();
			 
			 String sectionName = readexcel.FetchValueFromExcel("URL", rowcounter).trim();
			 Reporter.rep_Insert_Section(sectionName);
			 System.out.println("Rowcounter value= " + rowcounter);
			 
			
	
	}
	@Test(priority=1)
	public void test(){
		
		try{
		
			loginPage = loginNav.navigate(null);
			homePage = loginPage.loginAs(rowcounter);
			itmPage=homePage.navigateToMenu();
			itmPage.searchByItem("1000116");						
			Reporter.rep_Close();
			rowcounter=rowcounter+1;		
					
			

		    	
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
		
	@Test(priority=2)
	public void test1(){
		
		try{
			
			
			loginPage = loginNav.navigate(null);
			homePage = loginPage.loginAs(rowcounter);
			itmPage=homePage.navigateToMenu();
			itmPage.searchByItem("1000116");						
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
	
	
/*
	@SuppressWarnings("unchecked")
	private static <T> T verifyPage(String message, Class<T> expectedPage) {
		//assertTrue(message, currentPage.getClass().isAssignableFrom(expectedPage));
		return (T) currentPage;
	}
*/

	@AfterMethod
	public static void afterScenario() {
		
		System.out.println("Shutting down browser");
		BrowserDriver.close();
		currentPage = null;
		
	}

}
