package Application_Specific_Library;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class DashBoardPage extends WebContainer {
	private static final String pageTitle = "DashBoard page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Cardinal Health | Report Center");
	private static final By businessViewTabLocator = By.xpath(".//a[text()='Business Review']"); 
	private static final By resultDisplaySelector = By.id("bottomPag");
	private static final By nextPageLocator = By.id("next");

	public DashBoardPage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public DashBoardPage(Verification verification, String pageTitle) throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public purchaseReview navigateToTab(String TabName) throws IOException, InterruptedException{
		
		//String xpath = ".//a[text()="+TabName+"']";
		
		final By tabLocator = By.xpath(".//a[text()='Purchases Review ']");
		PollingClick.click(tabLocator, TabName);
		
		Thread.sleep(5000);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    return new purchaseReview();
		
	    		
	}
	
	
	public void selectOptionAndValidate(String option) throws IOException, InterruptedException{
		PollingClick.select(resultDisplaySelector, "All", "Items Per Page dropdown");
		Thread.sleep(5000);
		try{
			WebElement NextOption = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(nextPageLocator), "Next Page");
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "Not as per expectation", "FAILED");
		}catch (Exception e ){
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "As expected", "PASS");
		}
		
	}
	
	
	
}
