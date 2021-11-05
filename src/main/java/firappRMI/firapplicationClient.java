package firappRMI;

import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;

import components.emailSender.EmailSenderV1;
import components.emailSender.EmailSenderV2;
import components.excel.ExcelCreator;
import components.pdf.PdfCreator;

public class firapplicationClient {

	public static void main(String[] args) {
		try {
			MyApp obj = (MyApp) Naming.lookup("rmi://localhost:2000/lalith");

			System.out.println("got app obj........" + obj);

			EmailSenderV1 es = (EmailSenderV1) obj.getObject();
			es.sendEmail("uit18130@rmd.ac.in", "First mail", "Hi Lalith, \n Welcome to My App");
			System.out.println("email V1 sent........");

			EmailSenderV2 es2 = (EmailSenderV2) es;
			es2.sendEmail("uit18130@rmd.ac.in", "Second mail", "Hi Lalith, \n Welcome to My App", "abc.text");
			System.out.println("email V2 sent........");

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			Date date = new Date();
			String docName = formatter.format(date);
			System.out.println(docName);

			PdfCreator pc = (PdfCreator) es2;
			pc.createPdf(docName, "Hi this is a simple PDF");
			es2.sendEmail("uit18130@rmd.ac.in", "Third mail", "Hi Lalith, \n Welcome to My App", docName + ".pdf");
			System.out.println("email with PDF sent........");

			ExcelCreator ec = (ExcelCreator) pc;
			Object[][] bookData = { { "Lalith", "student", 20 }, { "Raj Kumar", "dev", 22 },
					{ "bala", "student", 21 } };
			ec.createExcel(docName, bookData);
			es2.sendEmail("uit18130@rmd.ac.in", "Fourth mail", "Hi Lalith, \n Welcome to My App", docName + ".xlsx");
			System.out.println("email with Excel sent........");

		} catch (Exception e) {
			System.out.println("got error at client" + e);
		}
	}
}
