package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;
import common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;

public class SFDCHomePage extends WebContainer {
	private static final String pageTitle = "SFDC HOME PAGE";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Single Sign-On Error | Salesforce");
	

	public SFDCHomePage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public String HomePageErrorMessage() throws IOException{
		final By ErrorMessageLocator = By.id("header");
		WebElement ErrorMessageElement = PollingClick.tryToWaitFor(visibilityOfElementLocated(ErrorMessageLocator), "SFDC Error MEssage");
		String errorMessage = ErrorMessageElement.getText();
		return errorMessage;
	}
	
}