package components.pdf;

import java.io.Serializable;

public interface PdfCreator extends Serializable {

	public void createPdf(String fileName, String content);
}
