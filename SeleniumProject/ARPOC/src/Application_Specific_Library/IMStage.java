package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.BrowserDriver;
import common.PollingClick;

public class IMStage extends WebContainer {
	private static final String pageTitle = "Enterprise Login";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Enterprise Login");
	

	public IMStage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public String HomePageErrorMessage() throws IOException{
		final By forgotpwdlocator = By.id("forgotPasswordLink");
		PollingClick.tryToWaitFor(visibilityOfElementLocated(forgotpwdlocator), "ForGotPassword Link").click();
		String url =  BrowserDriver.getCurrentDriver().getCurrentUrl();
		return url;
	}
	
}
