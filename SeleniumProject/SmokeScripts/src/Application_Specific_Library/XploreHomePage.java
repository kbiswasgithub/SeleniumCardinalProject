package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;

public class XploreHomePage extends WebContainer {
	private static final String pageTitle = "Home";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Cardinal Health Enterprise Login Page");
	

	public XploreHomePage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	

	public String GetAuthorizationMessage() throws IOException {
		String AuthMessage;
		final By authorizationError = By.className("alertText");
		AuthMessage = PollingClick.tryToWaitFor(visibilityOfElementLocated(authorizationError), "Auth Error Message").getText();
		return AuthMessage;
	}
	
	
	
	
}
