
package common;
import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class readexcel {
                
                public static String cellvalue;
    public static int row;
    
    /*
    ***********************************************************************************************************************
    Method Name :- excelreader
    Description :- Based on the script name it will search for test data 
     Parameters :- testname:- Script which will be executed
    Created By :- Rohit 
     Modified By :-  
     ***********************************************************************************************************************     
     */
                    
    public static int getTotalUsedRow() throws BiffException, IOException 
    
    	{
                //boolean flag=false;
	            File ExcelFile = new File("src/TestData.xls");
                Workbook objWork =  Workbook.getWorkbook(ExcelFile);
                System.out.print(objWork.getNumberOfSheets());
                Sheet objSheet=objWork.getSheet(0);
                //int cols=objSheet.getColumns();
                int TotalRow=objSheet.getRows();
                System.out.print("TotalRow " +TotalRow );
                
               for (row=0;row<TotalRow;row++)
                {
	                if (objSheet.getCell(0, row).getContents().isEmpty())
	                {
	                               break;
	                }
                }
                return row;         
    }
    
    public static String FetchValueFromExcel(String HeaderName,int row) throws BiffException, IOException 
    
    {
        File ExcelFile = new File("src/TestData.xls");
        Workbook objWork =  Workbook.getWorkbook(ExcelFile);
        Sheet objSheet=objWork.getSheet(0);

        int cols=objSheet.getColumns();
        //int rows=objSheet.getRows();
                    
            for (int col=0;col<cols;col++)
            {
            if (objSheet.getCell(col, 0).getContents().equalsIgnoreCase(HeaderName))
            {                              
                            cellvalue=objSheet.getCell(col, row).getContents();
                            break;

            }
            }
                    return cellvalue;
                }              
                
}
