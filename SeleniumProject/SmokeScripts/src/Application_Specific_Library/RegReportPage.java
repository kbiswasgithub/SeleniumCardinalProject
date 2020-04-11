package Application_Specific_Library;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.PollingClick;

public class RegReportPage extends WebContainer {
	private static final String pageTitle = "Reports page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Reports");
	public static final By processingHistorylink = By.xpath(".//span[text( )='Processing history']");
	private static final By selectStateIdentifier = By.xpath(".//select[contains(@id,'menuStateReport')]");
	private static final By retrieveHistory = By.xpath(".//input[contains(@id,'retrivehistory')]");
	private static final By tableIdentifier = By.xpath(".//table[contains(@id,'table1')]");
	private static final By tbodyIdentifier = By.xpath(".//*[@id='form11:table1']/tbody");
	
	public RegReportPage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	

	public WebElement  validateReportExistence(String valueToSelect) throws IOException{
		final WebElement tablebody;
		PollingClick.click(processingHistorylink, "Processing History Link");
		PollingClick.select(selectStateIdentifier, valueToSelect, "Select State");
		PollingClick.click(retrieveHistory, "Retrieve History Button");
		tablebody = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(tbodyIdentifier), "processing report table body ");
		return tablebody;
		
	}
	

}
