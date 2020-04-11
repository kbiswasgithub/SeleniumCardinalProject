package Application_Specific_Library;

import org.openqa.selenium.By;

import Application_Specific_Library.WebContainer.Verification;
import Application_Specific_Library.WebContainer.VerificationType;
import common.PollingClick;

public class GWSAHomePage extends WebContainer {
	private final static String pageTitle = "Cardinal Health Enterprise Login Page";
	private final static Verification verification = new Verification(VerificationType.TITLE, "GWSA Home - v5.7");
	private final static By itemLInk = By.id("GWSAForm:itemRebatesPageLink");
	
	

	public GWSAHomePage() throws Exception {
		super(verification, pageTitle);
	}

	
	public GWSAItemPage clickOnItemLink() throws Exception {
		PollingClick.click(itemLInk, "Item Link");
		return new GWSAItemPage();
		
	}
	
}
