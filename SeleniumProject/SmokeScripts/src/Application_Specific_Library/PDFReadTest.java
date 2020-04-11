package Application_Specific_Library;


import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class PDFReadTest {

	public static void main(String[] args) throws IOException {
		PDDocument pdf;
		pdf = PDDocument.load(new File("C:\\Users\\koushik.biswas\\Downloads\\TestPDFFile.pdf"));
		System.out.println("Number of PDF Pages: "+ pdf.getNumberOfPages());
		PDFTextStripper pdtext = new PDFTextStripper(); 
		System.out.println(pdtext.getText(pdf));
		
	}

}
