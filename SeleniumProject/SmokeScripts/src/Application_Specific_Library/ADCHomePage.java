package Application_Specific_Library;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.BrowserDriver;
import common.PollingClick;
import common.Reporter;

public class ADCHomePage extends WebContainer {
	private static final String pageTitle = "Cardinal Health Enterprise Login Page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Anti-Diversion Centralization: Dashboard");
	private final static By DEATextBox = By.xpath(".//*[@id='deanumBanner']");
	final static By customerResultsTable = By.xpath(".//table[contains(@id,'customerResults')]/tbody");
	final static By GoButton = By.xpath(".//*[@id='searchCustomerButton']");
	final static By deaDetails = By.xpath(".//a[contains(text()," + DEANum +")]");
	private final static By ViewCustomerLink = By.xpath(".//*[@id='jEditButton_0']");
	final static By ViewDocLink = By.xpath(".//table[contains(@id,'customerRelatedDocsGrid')]/tbody/tr[2]/td[1]/a[contains(text(),'View Document')]");
	final static By ADCCusSetUPChkList=By.xpath(".//*[@id='pageContainer1']/xhtml:div[2]/xhtml:div[1][contains(text(),'Anti-Diversion New Customer Set-Up Checklist')]");
	final static By DashboardLink=By.xpath(".//a[contains(text(),'Dashboard')]");
	final static By ApplyFilterLink=By.xpath(".//input[@id='openFilterDialogue' and @value='Apply Filter']");
	final static By CaseWorkerTab=By.xpath(".//a[contains(text(),'Case Worker')]");
	final static By selectAllChkBox=By.xpath(".//input[@id='checkAllQraOwner' and @name='checkAllQraOwner']");
	final static By searchbutton=By.xpath(".//*[@id='searchCustomerButton' and @value='Search']");
	final static By QRASearchResults=By.xpath(".//table[@id='customerCaseList']/tbody");
	
	public ADCHomePage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}
	
	public void validateCustomerSearchTablePresence (String DEANum) throws IOException{
		WebElement customerDetailsTable = null;
		WebElement deaDetailsPage = null;
		PollingClick.enter(DEATextBox, DEANum, "DEA Text Box");
		PollingClick.click(GoButton, "Go Button ");
		try{
			
			if(PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(customerResultsTable), "Customer Details Table")!=null)
			{ 
				System.out.println("Customer Details for " + DEANum +" displayed" );
				Reporter.ExportResultToHtml("customer details validation ", "customer details should be displayed", "Customer Details for " + DEANum + "displayed", "PASS");
				
			}
			
		}catch(Exception e){
			
			System.out.println("Customer Details for " + DEANum +" Not displayed" );
			Reporter.ExportResultToHtml("customer details validation ", "customer details should be displayed", "Customer Details for " + DEANum + "not displayed", "FAIL");
			
		}
		PollingClick.click(ViewCustomerLink, "View Customer Link ");
		try{
					
			if(PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(deaDetails), "DEA Details")!=null)
				{ 
					System.out.println("DEA Details for " + DEANum +" displayed" );
					Reporter.ExportResultToHtml("DEA Details validation ", "DEA Details should be displayed", "DEA Details for " + DEANum + "displayed", "PASS");
					
				}
			
			}catch(Exception e){
				
				System.out.println("DEA Details for " + DEANum +" Not displayed" );
				Reporter.ExportResultToHtml("DEA Details validation ", "DEA Details should be displayed", "DEA Details for " + DEANum + "not displayed", "FAIL");
				
			}		
		PollingClick.click(ViewDocLink, "View Document Link ");		
		PollingClick.click(DashboardLink, "Dashboard Link ");
		PollingClick.click(ApplyFilterLink, "Apply Filter Link ");
		PollingClick.click(CaseWorkerTab, "Case worker Tab ");
		PollingClick.click(selectAllChkBox, "Case worker Tab ");		
		PollingClick.click(searchbutton,"Search button");
		
		try{
			
			if(PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(QRASearchResults), "Search Results for QRA Customer")!=null)
				{ 
					System.out.println("Search result for QRA customer displayed" );
					Reporter.ExportResultToHtml("Verify Search result for QRA customer ", "Search result for QRA customer should be displayed", "Search result for QRA customer displayed", "PASS");
					
				}
			
			}catch(Exception e){
				
				System.out.println("Search result for QRA customer not displayed" );
				Reporter.ExportResultToHtml("Verify Search result for QRA customer ", "Search result for QRA customer should be displayed", "Search result for QRA customer not displayed", "FAIL");
				
			}
		
		
		
		//return deaDetailsPage;
		
	}

	
	
}
