package Application_Specific_Library;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class RegReportinHomePage extends WebContainer {
	private static final String pageTitle = "Home";
	private static final Verification verification = new Verification(VerificationType.TITLE, "BulkUpload");
	public static final By reportbutton = By.id("form1:reports"); 

	public RegReportinHomePage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public RegReportinHomePage(Verification verification, String pageTitle) throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public RegReportPage navigateToReports() throws IOException {

		PollingClick.click(reportbutton, "Report Button");
		return new RegReportPage();
	}
	
	
	
	
	
}
