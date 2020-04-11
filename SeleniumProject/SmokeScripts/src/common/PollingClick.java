package common;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.BrowserConfigurationOptions;

import Application_Specific_Library.Driver;

public class PollingClick extends Driver{
	

	public static void resetImplicitWaitToDefault() {
		setImplicitWait(BrowserConfigurationOptions.DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
	}

	public static void setImplicitWait(int time, TimeUnit units) {
		WebDriver driver = BrowserDriver.getCurrentDriver();
		driver.manage().timeouts().implicitlyWait(time, units);
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			;
		}
	}

	public static <T> T tryToWaitFor(final ExpectedCondition<T> condition, final String elementName) throws IOException {
		return tryToWaitFor(condition, elementName, 15);
	}

	public static <T> T tryToWaitFor(final ExpectedCondition<T> condition, final String elementName, final int secondsToWait) throws IOException {
		WebDriver driver = BrowserDriver.getCurrentDriver();
		//System.out.println(driver.getTitle());
		T result = null;
		try {
			result = (new WebDriverWait(driver, secondsToWait)).until(condition);
		} catch (final Exception e) {
			System.out.println(String.format("Timed Out After " + secondsToWait + " Seconds While Waiting For " + elementName));
			Reporter.ExportResultToHtml("Validation of existence of Object   " + elementName, " Object  " +elementName+ " should exist ","Object " +elementName +" does not exist" , "FAILED");;
			ErrorContainer.add("CAH URL CHECK IS FAILED");
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	public static void click(By path , String ElementName) throws IOException {
		tryToWaitFor(elementToBeClickable(path), ElementName).click();
		
		Reporter.ExportResultToHtml("Perform Click on  " + ElementName, "User should be able to click " +ElementName, "Element "+ElementName+ " is clicked", "PASS");
	}

	public static void select(By path, String value, String ElementName) throws IOException {
		tryToWaitFor(elementToBeClickable(path), path.toString()).click();
		WebElement link = tryToWaitFor(elementToBeClickable(path), path.toString());
		Select select = new Select(link);
		select.selectByVisibleText(value);
		Reporter.ExportResultToHtml("Perform Select  on  " + ElementName, "User should be select  " +value, "Value "+value+ "from "+ElementName+ " is selected", "PASS");
	}

	public static void select(By path, int index, String ElementName) throws IOException {
		tryToWaitFor(elementToBeClickable(path), path.toString()).click();
		WebElement link = tryToWaitFor(elementToBeClickable(path), path.toString());
		Select select = new Select(link);
		select.selectByIndex(index);
		Reporter.ExportResultToHtml("Perform Select  on  " + ElementName, "User should be select  " +index, "Value "+index+ "from "+ElementName+ " is selected", "PASS");
	}

	public static void enter(By path, String text, String ElementName) throws IOException {
		WebElement textInput = tryToWaitFor(elementToBeClickable(path), ElementName);
		textInput.clear();
		//tryToWaitFor(elementToBeClickable(textInput), path.toString()).click();
		tryToWaitFor(elementToBeClickable(textInput), ElementName).click();
		textInput.sendKeys(text);
		tryToWaitFor(textToBePresentInElementValue(textInput, text), ElementName);
		if (path.toString().contains("PASSWORD")){
			Reporter.ExportResultToHtml("Perform Enter  on  " + ElementName, "User should be able to enter value ********** in" +ElementName, " Value ********** is entered in "+ElementName, "PASS");
		}
		else{
		Reporter.ExportResultToHtml("Perform Enter  on  " + ElementName, "User should be able to enter value "+text+ " in  " +ElementName, " Value "+text+ " is entered in "+ElementName, "PASS");
		}
	}
	
	public static void enter(WebElement textBox, String text,String ElementName) throws IOException {
		textBox.clear();
		textBox.sendKeys(text);
		tryToWaitFor(textToBePresentInElementValue(textBox, text), "text in input");
		Reporter.ExportResultToHtml("Perform Enter  on  " + ElementName, "User should be able to enter value "+text+ " in  " +ElementName, " Value "+text+ " is entered in "+ElementName, "PASS");
	}
}
