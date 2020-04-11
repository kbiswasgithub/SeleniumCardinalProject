package Application_Specific_Library;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import common.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ItemPage extends WebContainer {
	private static final String pageTitle = "MAH Items page";
	private static final Verification verification = new Verification(VerificationType.TITLE, "Manhattan Associates");
	public String itemframe=null;
	//private static final By iteminput=By.id("dataForm:ItemList_lv:ItemList_filterId:itemLookUpId");
	private static final By iteminput=By.xpath(".//input[contains(@id,'itemLookUpId')]");
	private static final By Applybutton=By.id("dataForm:ItemList_lv:ItemList_filterId:ItemList_filterIdapply");
	private static final By tableData=null;
	private static final By tableLocator = By.id("dataForm:ItemList_lv:dataTable_body");
	
	//"table[@id[contains[.,'dataForm:ItemList_lv:dataTable_body']]]/tr[1]/td[3]/div/span[text(),'" + itemName + "'"
	
	public ItemPage() throws IllegalStateException, IOException {
		super(verification, pageTitle);
	}

	
	public void searchByItem(String itemName) throws IOException, InterruptedException{
		
		//String xpath = ".//a[text()="+TabName+"']";
		
		//final By tabLocator = By.xpath(".//a[text()='Purchases Review ']");
		System.out.println(getDriver().findElement(By.tagName("iframe")).getAttribute("id"));
		String str=getDriver().findElement(By.tagName("iframe")).getAttribute("id");
		//System.out.println(getDriver().findElement(By.tagName("iframe")).getAttribute(getCurrentTitle()));
		itemframe=str;
		/*try{
		
			getDriver().switchTo().frame("uxiframe-1096-frame");
		}catch (Exception e){ 
			System.out.println(e.getMessage());
		}*/
		PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(By.id(itemframe)),"Item Window", 30);
		System.out.println("I am within Item Page");
		//waitForNewWindowAndSwitchToIt(mDriver);
		getDriver().switchTo().frame(itemframe);
		PollingClick.enter(iteminput, itemName, "Item Number");
		PollingClick.click(Applybutton, "Apply Button");			
		Thread.sleep(5000);
		
	    return;
		
	    		
	}
	
	/*
	public void verifyItem(String itemName) throws IOException, InterruptedException{
		PollingClick.select(resultDisplaySelector, "All", "Items Per Page dropdown");
		Thread.sleep(5000);
		try{
			WebElement NextOption = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(nextPageLocator), "Next Page");
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "Not as per expectation", "FAILED");
		}catch (Exception e ){
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "As expected", "PASS");
		}
		
	}
	*/
	
	public void verifyItem(String itemName) throws IOException{
		//System.out.println("within verifyItem page");
		System.out.println(itemframe);
		String str1=getDriver().findElement(By.tagName("iframe")).getAttribute("id");
		//System.out.println(getDriver().findElement(By.tagName("iframe")).getAttribute(getCurrentTitle()));
		itemframe=str1;
		System.out.println(itemframe);
		getDriver().switchTo().frame(itemframe);		
		/*
		int tblbody= getDriver().findElements(By.xpath(".//span[contains(@id,'dataForm:ItemList_lv:dataTable')]")).size();
		System.out.println("Testing1");
		System.out.println(tblbody);
		for ( int i =0; i < tblbody; i++){
			//string divpath=
			String path="dataForm:ItemList_lv:dataTable:" + i +":itemList_Link_NameText";
			System.out.println(getDriver().findElements(By.xpath(path)));
		//}*/
		
		 List<List<WebElement>> tblbody = populateSugggestedOrderTableBdody(tableLocator);
        for ( int i =2; i < tblbody.size(); i++){
               for(int j=1;j<tblbody.get(i).size(); j++){
                     System.out.println(tblbody.get(i).get(j).getText());
                     if (itemName.equalsIgnoreCase(tblbody.get(i).get(j).getText())) {
                    	 System.out.println("Item found");
                    	 Reporter.ExportResultToHtml("Item Number Verification", "Item number should match",itemName +" found" , "PASS");
                                        	 
                     }else{
                    	 System.out.println("Item not found");
                    	 Reporter.ExportResultToHtml("Item Number Verification", "Item number should match",itemName +" found" , "FAIL");
                     }
                     	
                    
                     
               }
              
        }
       
 }


 private List<List<WebElement>> populateSugggestedOrderTableBdody(By tablePath) throws IOException {
        List<List<WebElement>> tableBody = new ArrayList<List<WebElement>>();
        WebElement table = PollingClick.tryToWaitFor(visibilityOfElementLocated(tablePath), "Order table");
        try {
             
               for (WebElement row : table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
                     tableBody.add(row.findElements(By.tagName("td")));
                     
               }
              
       } catch (Exception e) {
            //  logger.error("Error getting tableBody", e);
       }
        System.out.println(tableBody.size());
        return tableBody;
 }
	
	
	
	
	/*
	public void selectOptionAndValidate(String option) throws IOException, InterruptedException{
		PollingClick.select(resultDisplaySelector, "All", "Items Per Page dropdown");
		Thread.sleep(5000);
		try{
			WebElement NextOption = PollingClick.tryToWaitFor(ExpectedConditions.visibilityOfElementLocated(nextPageLocator), "Next Page");
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "Not as per expectation", "FAILED");
		}catch (Exception e ){
			Reporter.ExportResultToHtml("Validate that Only One page is getting displayed After Selectin All from Result dropdown", "One page should be displayed", "As expected", "PASS");
		}
		
	}
	*/
 public static void waitForNewWindowAndSwitchToIt(WebDriver driver) throws InterruptedException {
     String cHandle = driver.getWindowHandle();
     String newWindowHandle = null;
     Set<String> allWindowHandles = driver.getWindowHandles();
     
     //Wait for 20 seconds for the new window and throw exception if not found
     for (int i = 0; i < 20; i++) {
         if (allWindowHandles.size() > 1) {
             for (String allHandlers : allWindowHandles) {
                 if (!allHandlers.equals(cHandle))
                 	newWindowHandle = allHandlers;
             }
             driver.switchTo().window(newWindowHandle);
             break;
         } else {
             Thread.sleep(1000);
         }
     }
     if (cHandle == newWindowHandle) {
         throw new RuntimeException("Time out - No window found");
     }
 }


	
}
