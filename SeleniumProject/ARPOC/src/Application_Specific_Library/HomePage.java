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



public class HomePage extends WebContainer {
	private static final String pageTitle = "MAH Home page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Manhattan Associates");
	private static final By mainmenubutton=By.xpath(".//span[@id[contains(.,'btnIconEl')]]");
	private static final By showAllbutton=By.xpath(".//span[@id[contains(.,'button-1080-btnEl')]]");
	private static final By MainMenu=By.xpath(".//span[@id[contains(.,'tab-1109-btnInnerEl')]]");
	private static final By SubMenu=By.xpath(".//div[@id[contains(.,'component-1099')]]/div[@name='4400094']");
	
	
	public HomePage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	
	public ItemPage navigateToMenu() throws IOException, InterruptedException{
		
		//String xpath = ".//a[text()="+TabName+"']";
		
		//final By tabLocator = By.xpath(".//a[text()='Purchases Review ']");
		PollingClick.click(mainmenubutton, "Main Menu Button");
		PollingClick.click(showAllbutton, "Show All Button");
		PollingClick.click(MainMenu, "WMOS");
		Thread.sleep(5000);
		PollingClick.click(SubMenu, "Item");		
		Thread.sleep(20000);
		
	    return new ItemPage();
		
	    		
	}
	
	/*
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
	*/
	
	
}
