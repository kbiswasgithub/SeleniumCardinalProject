package Application_Specific_Library;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;

public class GWSAItemPage extends WebContainer {
	private final static String pageTitle = "Item rebates";
	private final static Verification verification = new Verification(VerificationType.TITLE, "ItemRebates");
	private final static By cinTextBox = By.id("GWSAForm:itemSearchViewFragment:cinText");
	final static By itemInfTable = By.xpath(".//table[contains(@id,'itemInformationGrid')]/tbody");
	final static By vendorLink = By.id("GWSAForm:vendorRebatesPageLink");
	final static By vendorTable = By.xpath(".//table[contains(@id,'vendorInformation')]/tbody");
	private final static By searchButton = By.xpath(".//input[contains(@id,'SearchButton')]");
	

	public GWSAItemPage() throws Exception {
		super(verification, pageTitle);
	}
	
	/*
	 * DESCRIPTION :: Below method will enter the value of CIN in search box and validate that Item Information Table is getting 
	 * displayed 
	 * RETURN TYPE : WEBELEMENT : WE ARE RETURNING THE TABLE BODY OF ITEM INFORMATION TABLE , CONSIDERING THAT IF SEARCH RESULT IS 
	 * RETURNING NULL VALUES THEN TABLE BODY WILL NOT BE DISPLAYED
	 *  
	 */
	
	public WebElement validateItemtablePresence (String itemnum) throws IOException{
		WebElement itemInformationTable = null;
		PollingClick.enter(cinTextBox, itemnum, "CIN Text Box");
		PollingClick.click(searchButton, "Search Button ");
		itemInformationTable = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(itemInfTable), "Item INformation table");
		return itemInformationTable;
		
	}
	/*
	 * DESCRIPTION :: Below method will click on Vendor link  and validate that Vendor Information Table is getting displayed 
	 * RETURN TYPE : WEBELEMENT : WE ARE RETURNING THE TABLE BODY OF Vendor INFORMATION TABLE , CONSIDERING THAT IF SEARCH RESULT IS 
	 * RETURNING NULL VALUES THEN TABLE BODY WILL NOT BE DISPLAYED
	 *  
	 */
	
	public WebElement navigateAndValidateVendorInfoTable() throws IOException{
		WebElement vendorInformationTable = null;
		PollingClick.click(vendorLink, "Vendor Link");
		vendorInformationTable = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(vendorTable), "Vendor Information table");
		return vendorInformationTable;
		
	}
	
	

}
