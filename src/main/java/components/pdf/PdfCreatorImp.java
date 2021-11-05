package components.pdf;

import java.io.IOException;
import java.io.Serializable;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfCreatorImp implements PdfCreator, Serializable {

	@Override
	public void createPdf(String fileName, String content) {

		PDDocument pdfdoc = new PDDocument();

		PDPage pdPage = new PDPage();

		pdfdoc.addPage(pdPage);

		try (PDPageContentStream pcs = new PDPageContentStream(pdfdoc, pdPage)) {

			pcs.beginText();

			pcs.newLineAtOffset(20, 750);
			pcs.setFont(PDType1Font.COURIER, 15);
			pcs.showText(content);

			pcs.endText();

		} catch (IOException e1) {
			System.out.println("got exception.... in adding text to pdf..... handle it.........");
		}

		try {
			// path where the PDF file will be store
			pdfdoc.save(fileName + ".pdf");
		} catch (IOException e) {
			System.out.println("got error in saving pdf.... add handler....");
		}
		// prints the message if the PDF is created successfully
		System.out.println("PDF created");
		// closes the document
		try {
			pdfdoc.close();
		} catch (IOException e) {
			System.out.println("got error in closing pdfdoc.... add handler.....");
		}
	}

}
