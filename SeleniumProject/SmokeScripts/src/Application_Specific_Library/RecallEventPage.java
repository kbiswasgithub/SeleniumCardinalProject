package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.BrowserDriver;
import common.PollingClick;

public class RecallEventPage extends WebContainer {
	private static final String pageTitle = "Enterprise Login";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Event Dashboard");
	final static By firstItemOftable = By.xpath(".//table[@class='listing']/tbody/tr[1]/td[1]/a");

	public RecallEventPage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	public String HomePageErrorMessage() throws IOException{
		final By forgotpwdlocator = By.id("forgotPasswordLink");
		PollingClick.tryToWaitFor(visibilityOfElementLocated(forgotpwdlocator), "ForGotPassword Link").click();
		String url =  BrowserDriver.getCurrentDriver().getCurrentUrl();
		return url;
	}
	
}
