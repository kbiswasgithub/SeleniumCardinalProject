package common;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;

public class Reporter {
	
	public static WebDriver objDrv;
	
	//Report Log File Name.
	public static String g_sFileName;
	//Store the screenshot.
	public static String screenshotPath;
	//Pass Count
	public static int g_iPass_Count=0;
	//Fail Count
	public static int g_iFail_Count=0;
	//Step Count
	public static int sStepNo=0;
	
	//Start Time
	public static Date g_tStart_Time;
	//End Time
	public static  Date g_tEnd_Time;
	//script name
	public static String scriptname;

	
	public static void initalizWebDriver(WebDriver driver){
		objDrv = driver;
	}
	
	
	public static void rep_Open_File(String sFileName) throws IOException{

		g_sFileName = sFileName;
		File o_File=new File(g_sFileName);
		BufferedWriter g_objReport = new  BufferedWriter(new FileWriter(o_File));
		Date date = new Date();
		g_tStart_Time=date;
	   
		g_objReport.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=90%>");
		g_objReport.write("<TR COLS=2><TD BGCOLOR=WHITE WIDTH=6%><IMG SRC='https://orderexpress.cardinalhealth.com/forms/RWO/cardinal_logo_small.gif'></TD><TD WIDTH=94% BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=NAVY SIZE=2><B>&nbsp;URL HEALTH CHECK MONITORING - " + g_tStart_Time + "</B></FONT></TD></TR></TABLE>");
		g_objReport.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=90%>");
		g_objReport.close();

	}
	
	public static void rep_Insert_Section(String sSection) throws IOException{

		File o_File=new File(g_sFileName);
		BufferedWriter g_objReport = new  BufferedWriter(new FileWriter(o_File,true));
		
		g_objReport.write("<TR><TD COLSPAN=5 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2 align=center><B>" + "URL NAME : "+sSection + "</B></FONT></TD></TR></center>");
		g_objReport.write("<TR COLS=5><TD BGCOLOR=#FFCC99 WIDTH=2%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step No</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Description</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Expected Behaviour</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Actual Behaviour</B></FONT></TD><TD BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Result</B></FONT></TD></TR>");
		g_objReport.close();
	}
	
	public static void rep_Insert_Step(String sDesc) throws IOException{

		File o_File=new File(g_sFileName);
		BufferedWriter g_objReport = new  BufferedWriter(new FileWriter(o_File,true) );
		g_objReport.write("<TR><TD BGCOLOR=#EEEEEE COLSPAN=5><FONT FACE=VERDANA SIZE=2><B>" + sDesc + "</B></FONT></TD></TR>");
		g_objReport.close();
	}
	
	public static void rep_Insert_Result(String sDesc, String sExpected, String sActual, String sResult) throws IOException{
			
		
		
		sStepNo=sStepNo+1;
		File o_File=new File(g_sFileName);
		BufferedWriter g_objReport = new  BufferedWriter(new FileWriter(o_File,true) );

		if(sResult=="PASS"){
		   g_iPass_Count = g_iPass_Count + 1;
		   getScreenshot(objDrv);
	       g_objReport.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=2%><FONT FACE=VERDANA SIZE=2>" + sStepNo + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sExpected + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><B><A href=\"" + screenshotPath + "\\"+ sStepNo + ".png\"><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>" + sResult + "</FONT></A></B></TD></TR>");
	       	
		}
	    else if(sResult=="FAILED"){
	       g_iFail_Count = g_iFail_Count + 1;
	       getScreenshot(objDrv);
	       g_objReport.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=2%><FONT FACE=VERDANA SIZE=2>" + sStepNo + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sExpected + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><B><A href=\"" + screenshotPath + "\\"+ sStepNo + ".png\"><FONT FACE=VERDANA SIZE=2 COLOR=RED>" + sResult + "</FONT></A></B></TD></TR>");

	    }
	    else{
	       g_objReport.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=2%><FONT FACE=VERDANA SIZE=2>" + sStepNo + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sDesc + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sExpected + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=15%><FONT FACE=VERDANA SIZE=2>" + sActual + "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><B><A href=\"" + screenshotPath + "\\"+ sStepNo + ".png\"><FONT FACE=VERDANA SIZE=2 COLOR=BROWN>" + sResult + "</FONT></A></B></TD></TR>");
	    }
	   
	   g_objReport.close();
	 }
	
	public static void rep_Close() throws IOException, ALMServiceException{

		//System.setProperty("jacob.dll.path", "D:\\jacob-1.17-M2-x86.dll");

		//LibraryLoader.loadJacobLibrary();
		File o_File=new File(g_sFileName);
		BufferedWriter g_objReport = new  BufferedWriter(new FileWriter(o_File,true) );
		Date date = new Date();
		g_tEnd_Time=date;
		int g_DateDiff=g_tEnd_Time.compareTo(g_tStart_Time);
		if(g_iFail_Count==0){
			g_objReport.write("<TR COLS=5><TD BGCOLOR=BLACK WIDTH=12%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Total Steps : " + sStepNo + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=28%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Overall Result : </B></FONT><FONT FACE=VERDANA COLOR=GREEN SIZE=2><B>PASS</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=17%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Pass Count : " + g_iPass_Count + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=17%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Fail Count : " + g_iFail_Count + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=25%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Execution Time : " + g_DateDiff + " Minutes</B></FONT></TD></TR>");
			g_objReport.write("</TABLE></BODY></HTML>");
			//Wrapper.updateResult("Pharmaceutical eCommerce Platform\\Weekly Production Smoke Testing\\CIM",
              //      "Weekly Production Testing CIM", 21501, scriptname, StatusAs.PASSED);
			
		}
		else{
			g_objReport.write("<TR COLS=5><TD BGCOLOR=BLACK WIDTH=12%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Total Steps : " + sStepNo + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=28%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Overall Result : </B></FONT><FONT FACE=VERDANA COLOR=RED SIZE=2><B>FAIL</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=17%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Pass Count : " + g_iPass_Count + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=17%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Fail Count : " + g_iFail_Count + "</B></FONT></TD><TD BGCOLOR=BLACK WIDTH=25%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Execution Time : " + g_DateDiff + " Minutes</B></FONT></TD></TR>");
			g_objReport.write("</TABLE></BODY></HTML>");
			//wrapper.updateResult("Pharmaceutical eCommerce Platform\\Weekly Production Smoke Testing\\CIM",
              //      "Weekly Production Testing CIM", 21501, scriptname, StatusAs.FAILED);
		}
	    g_objReport.close();

	}
	
	public static void ExportResultToHtml(String sDesc, String sExpected, String sActual, String sResult) throws IOException{
		rep_Insert_Result(sDesc, sExpected, sActual, sResult);	
	}
	
	public static void getScreenshot(WebDriver driver) {

		// generate screenshot as a file object
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Path path = Paths.get(g_sFileName);
		screenshotPath=path.getParent() + "\\Screenshot\\";
		String location = screenshotPath + sStepNo + ".png";
		try {
			// copy file object to designated location
			FileUtils.copyFile(scrFile, new File(location));
		} catch (IOException e) {
			System.out.println("Error while generating screenshot:\n" + e.toString());	
		}
	}
		
}
