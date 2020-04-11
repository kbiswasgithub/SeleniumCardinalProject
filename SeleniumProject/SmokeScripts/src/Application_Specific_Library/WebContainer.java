package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import Application_Specific_Library.Driver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BrowserDriver;
import common.PollingClick;
import common.Reporter;

public abstract class WebContainer extends Driver {
	
	static Map<String, Class<? extends WebContainer>> viewClassForName = new HashMap<String, Class<? extends WebContainer>>();
	private final static By homePage = By.xpath("//a[.='Home']");

	protected enum VerificationType {
		XPATH,
		TITLE,
		IS_ID_DISPLAYED
	};

	protected static class Verification {
		private final VerificationType type;
		private final String verificationString;

		public Verification(VerificationType type, String verificationString) {
			this.type = type;
			this.verificationString = verificationString;
		}
	}

	protected final WebDriver driver;
	private final Verification verification;
	private final String pageTitle;

	public WebContainer(Verification verification, String pageTitle) throws IllegalStateException, IOException {
		this.driver = BrowserDriver.getCurrentDriver();
		this.verification = verification;
		this.pageTitle = pageTitle;
		this.verify();
		viewClassForName.put(pageTitle, this.getClass());
		System.out.println("Successfully instantiated " + pageTitle);
		Reporter.ExportResultToHtml("IDM HEALTH CHECK", "Login Page Should be Displayed", "Login Page Is Expected", "PASS");
	}

	// Method to verify that the current page contains the expected view
	private void verify() throws IllegalStateException, IOException {
		System.out.println("Verify:  type = " + verification.type + ", verificationString = " + verification.verificationString);
		try {
			switch (verification.type) {
			case TITLE:
				if (!verification.verificationString.equals(driver.getTitle())){
					
					ErrorContainer.add("CAH URL CHECK IS FAILED");
					System.out.println("This is not the " + pageTitle + " page");
					
					System.out.println("Cardinal Health IDM check FAILED");
					Reporter.ExportResultToHtml("IDM HEALTH CHECK", "Login Page Should be Displayed", "Login Page Title is not Expected", "FAILED");
					afterScenario();
					throw new IllegalStateException("This is not the " + pageTitle + " page");
				}
					break;
				
				
			case XPATH:
				driver.findElement(By.xpath(verification.verificationString));
				break;
			case IS_ID_DISPLAYED:
				if (!driver.findElement(By.id(verification.verificationString)).isDisplayed()){
					ErrorContainer.add("CAH URL CHECK IS FAILED");
					throw new IllegalStateException(pageTitle + " is not visible");
				}
				break;
			}
		} catch (NoSuchElementException e) {
			ErrorContainer.add("CAH URL CHECK IS FAILED");
			
			throw new IllegalStateException(pageTitle + " Page not found", e);
			
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public String getCurrentTitle() {
		return driver.getTitle();
	}

	
	protected void verify(boolean validate, String failMessage, String successMessage) throws Exception {
		if (validate) {
			System.out.println(successMessage);
		} else {
			ErrorContainer.add("CAH URL CHECK IS FAILED");
			throw new Exception(failMessage);
		}
	}

	protected void debugPage() {
		By paths[] = { By.xpath("//form"), By.xpath("//table"), By.xpath("//span"), By.xpath("//select"), By.xpath("//table/tbody/tr/td/span") };
		for (By xpath : paths) {
			for (WebElement we : driver.findElements(xpath)) {
				System.out.println(xpath.toString() + ":  " + we.getAttribute("id"));
			}
		}
		for (WebElement image : driver.findElements(By.xpath("//img"))) {
			System.out.println("img:  " + image.getAttribute("src"));
		}
		for (WebElement title : driver.findElements(By.xpath("/title"))) {
			System.out.println("title = " + title.getText());
		}
	}

	@After
	public static void afterScenario() {
		
		System.out.println("Shutting down browser");
		BrowserDriver.close();
		
	}


}
