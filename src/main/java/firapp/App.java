package firapp;

import java.util.Date;

import components.emailSender.EmailSenderV1;
import components.emailSender.EmailSenderV2;
import components.excel.ExcelCreator;
import components.excel.ExcelCreatorImp;
import components.pdf.PdfCreator;
import components.pdf.PdfCreatorImp;

public class App {

	public static void main(String[] args) {

		Object fir = FIRClient.getFIRApp();

		EmailSenderV1 esV1 = (EmailSenderV1) fir;
		esV1.sendEmail("uit18130@rmd.ac.in", "First mail", "Hi Lalith, \n Welcome to My App");

		PdfCreator pc = new PdfCreatorImp();
		String pdfName = "sample" + new Date().getTime();
		pc.createPdf(pdfName, "Lalith pdf content");

		EmailSenderV2 esV2 = (EmailSenderV2) fir;
		esV2.sendEmail("uit18130@rmd.ac.in", "Second mail", "Hi Lalith, \n Welcome to My App", pdfName + ".pdf");

		Object[][] bookData = { { "Lalith", "student", 20 }, { "Raj Kumar", "dev", 22 }, { "bala", "student", 21 } };
		ExcelCreator ec = new ExcelCreatorImp();
		String excelName = "sample" + new Date().getTime();
		ec.createExcel(excelName, bookData);

		esV2.sendEmail("uit18130@rmd.ac.in", "Third mail", "Hi Lalith, \n Welcome to My App", excelName + ".xlsx");
	}
}
