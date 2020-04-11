package Application_Specific_Library;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;
import common.Reporter;

public class BusinessReview extends WebContainer {
	private static final String pageTitle = "DashBoard page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Business Review");
	private static final By businessOverviewheader = By.xpath(".//td[text()='Business Overview']");
	private static final By exeDashBoardLocator = By.xpath(".//td[text()='Executive Dashboard']");
	private static final By POOverview = By.xpath(".//td[text()='Purchases Overview']");
	private static final By contractOverview = By.xpath(".//td[text()='Contracts Overview']");
	private static final By SLOverview = By.xpath(".//td[text()='Service Level Overview']");
	
	

	public BusinessReview() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

		
	public void BR_UIValidation() throws IOException{
		WebElement BOHeader = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(businessOverviewheader), "Header");
		WebElement ExecutiveDashBoard = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(exeDashBoardLocator), "Header");
		WebElement POOverView = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(POOverview), "Header");
		WebElement ContractOverview = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(contractOverview), "Header");
		WebElement SLOverView = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(SLOverview), "Header");
		
		
		
		
		if (BOHeader != null ){
			Reporter.ExportResultToHtml("Validation of Existence of Business OverView Header", "BO Header should be displayed", "BO header is getting displayed", "PASS");
		}else
			Reporter.ExportResultToHtml("Validation of Existence of Business OverView Header", "BO Header should be displayed", "BO header is getting displayed", "FAILED");
		
		if (ExecutiveDashBoard != null ){
			Reporter.ExportResultToHtml("Validation of Existence of Executive Dashboard Header", "Executive Dashboard should be displayed", "Executive Dashboard header is getting displayed", "PASS");
		}else
			Reporter.ExportResultToHtml("Validation of Existence of Executive Dashboard Header", "Executive Dashboard should be displayed", "Executive Dashboard header is not getting displayed", "FAILED");
		
		if (POOverView != null ){
			Reporter.ExportResultToHtml("Validation of Existence of Purchase Overview Header", "Purchase Overview header should be displayed", "PO header is getting displayed", "PASS");
		}else
			Reporter.ExportResultToHtml("Validation of Existence of Business OverView Header", "Purchase Overview Header should be displayed", "PO header is not getting displayed", "FAILED");
			
		if (ContractOverview != null ){
			Reporter.ExportResultToHtml("Validation of Existence of Business OverView Header", "Contract OverView Header should be displayed", "Contract header is getting displayed", "PASS");
		}else
			Reporter.ExportResultToHtml("Validation of Existence of Business OverView Header", "Contract Overview Header Header should be displayed", "Contract  header is not getting displayed", "FAILED");
	
		if (SLOverView != null ){
			Reporter.ExportResultToHtml("Validation of Existence of Service Level  OverView Header", "Service Level  Header should be displayed", "Service Level  header is getting displayed", "PASS");
		}else
			Reporter.ExportResultToHtml("Validation of Existence of Service Level  OverView Header", "Service Level  Header should be displayed", "Service Level  header is not getting displayed", "FAILED");
		
	}
	
	
	
	
	
}

