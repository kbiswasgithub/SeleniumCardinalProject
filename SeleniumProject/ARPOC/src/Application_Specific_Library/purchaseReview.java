package Application_Specific_Library;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;
import common.Reporter;

public class purchaseReview extends WebContainer {
	private static final String pageTitle = "Purchase Review";
	private static final Verification verification = new Verification(VerificationType.TITLE, "purchases review _8.qvw");
	private static final By businessOverviewheader = By.xpath(".//td[text()='Business Overview']");
	private static final By exeDashBoardLocator = By.xpath(".//td[text()='Executive Dashboard']");
	private static final By POOverview = By.xpath(".//td[text()='Purchases Overview']");
	private static final By contractOverview = By.xpath(".//td[text()='Contracts Overview']");
	private static final By SLOverview = By.xpath(".//td[text()='Service Level Overview']");
	private static final By updatedAsof = By.xpath(".//td[contains(text(),'Updated as of: ')]");
	private static final By searchTextBox = By.xpath(".//input[@placeholder='Search']");
	private static final By totalSpendDollar =By.xpath(".//td[contains(@title,'Total purchase dollars')]");
	private static final By actSelectionBox = By.xpath(".//div[contains(@class,'QvOptional_LED_CHECK')]");
	private static final By ProdMovementSearchDropdown = By.xpath(".//button[contains(@class,'dynamic-dropdown')]");
	private static final By accountSelector = By.xpath(".//div[contains(@title,'Account Name: (') and @class='QvOptional']/div[2]/div[3]/div/div");
	private static final By searchBox = By.xpath(".//input[@placeholder='Search']");
	private static final By accountNameSelector = By.xpath(".//div[contains(@title,'Cvs Pharmacy') and @class='QvOptional']/div[1]");
	private static final By colItemsLocator = By.xpath(".//DIV[contains(@class,'QvFrame Document') and @objsubtype='CH']/div[2]/div[1]/div[1]/div[2]/div/div"); 
	private static final By colresizeLocator = By.xpath(".//DIV[contains(@class,'QvFrame Document') and @objsubtype='CH']/div[2]/div[1]/div[1]/div[2]/div/div[@class='QvColumnResizer']");
	private static final By downicon = By.xpath(".//DIV[contains(@class,'QvFrame Document') and @objsubtype='CH']/div[2]/div[1]/div[4]/span");
	private static final By accountSearch = By.xpath(".//div[contains(@title,'Cvs Pharmacy # 01030 340b S/T') and @class = 'QvOptional']/div[2]/div[4]/span[3]");
	
	
	
	public purchaseReview() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

		
	public void PR_UIValidation(String [] tabNames ) throws IOException{
		
		final By dashboardtab = By.xpath(".//span[text()='Dashboard']");
		for ( int i=0; i< tabNames.length;i++){
			try{
				String xpath = "//span[text()='"+tabNames[i]+"']";
				WebElement element= PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)), tabNames[i]);
				Reporter.ExportResultToHtml("Validation of Existence of "+tabNames[i], "tab "+tabNames[i] +"should be displayed", "tab "+tabNames[i] + "  exist", "PASS");
			}catch( Exception e){
				Reporter.ExportResultToHtml("Validation of Existence of "+tabNames[i], "tab "+tabNames[i] +"should be displayed", "tab "+tabNames[i] + " Doesnt exist", "FAILED");
			}
		}
		
		WebElement dashBoardtab = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(dashboardtab), "DashBoard");
		WebElement firstParent = dashBoardtab.findElement(By.xpath("./.."));
		WebElement parent = firstParent.findElement(By.xpath("./.."));
		System.out.println(parent.getAttribute("class"));
		if (parent.getAttribute("class").equalsIgnoreCase("selectedtab")) {
			Reporter.ExportResultToHtml("Validate that Dashboard tab is Selected ","Should be selected", "is Selected " , "PASS");
		}
			else
				Reporter.ExportResultToHtml("Validate that Dashboard tab is Selected ","Should be selected", "is Selected " , "FAILED");
		}
		
		
	
		


	public void validateColumnData() throws IOException, InterruptedException{
		PollingClick.click(By.xpath("//span[text()='Product Movement Report']"), "prod Movement report");
		PollingClick.enter(searchTextBox, "Cvs Pharmacy # 01030 340b S/T", "test");
	    PollingClick.click(accountSelector, "Account Name");
	    Thread.sleep(3000);
	    WebElement element = getDriver().findElement(accountSearch);

	    

	    ((Locatable) element).getCoordinates().inViewPort();
	    try {
	        element.click();
	    } catch (Exception e) {
	        new Actions(getDriver()).sendKeys(Keys.PAGE_DOWN).perform();
	        element.click();
	    }
	    
	    //PollingClick.click(accountSearch, "ss");
		
		/*
		getDriver().findElement(searchTextBox).sendKeys(Keys.ENTER);
	    getDriver().findElement(searchTextBox).sendKeys(Keys.ENTER);
	    */
	    Thread.sleep(3000);
	    
	    for (int j=0;j<=55;j++){
	    	PollingClick.click(downicon, "Down Arrow");
	    }
	    
	    
	    
	    
	    
	    
	    List<WebElement> totalColCount = getDriver().findElements(colItemsLocator);
		int totalColCount1 = totalColCount.size();
		int colRezieCount = PollingClick.tryToWaitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(colresizeLocator), "Table").size();
		System.out.println(totalColCount1);
		
	    for (int i =colRezieCount+2; i<=totalColCount1-colRezieCount; i=i+9){
	    	try{
	    	  String xpath = ".//DIV[contains(@class,'QvFrame Document') and @objsubtype='CH']/div[2]/div[1]/div[1]/div[2]/div/div"+ "["+ i +"]";
	    	//String xpath = ".//DIV[contains(@class,'QvFrame Document') and @objsubtype='CH']/div[2]/div[1]/div[1]/div[2]/div/div[10]";
	    	//String rowdata = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)), "ss").getAttribute("Title");
	    	String rowdata = getDriver().findElement(By.xpath(xpath)).getAttribute("Title");
	    	if (rowdata !="Cvs Pharmacy # 01030 340b S/T") {
	    		Reporter.ExportResultToHtml("Validation of table Data", "Should match", "doesnot match at" + (i/9), "Failed");
	    	}
	    	}catch (Exception e){
	    		System.out.println("Error");
	    	}
	    }
	    
	    
		
		
		
	}
}
	


