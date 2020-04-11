package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;

public class PmodMDAPrdWM extends WebContainer {
	private static final String pageTitle = "Home";
	private static final Verification verification = new Verification(VerificationType.TITLE, "JBoss Web/7.4.8.Final-redhat-4 - JBWEB000064: Error report");
	

	public PmodMDAPrdWM() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	

	public String HomePageErrorMessage() throws IOException{
		final By ErrorMessageLocator = By.xpath("html/body/p[2]/u");
		WebElement ErrorMessageElement = PollingClick.tryToWaitFor(visibilityOfElementLocated(ErrorMessageLocator), "Error Mesage");
		String errorMessage = ErrorMessageElement.getText();
		return errorMessage;
	}
	
}