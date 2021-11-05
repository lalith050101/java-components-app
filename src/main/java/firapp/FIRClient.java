package firapp;

import components.emailSender.EmailSenderV1;
import components.emailSender.EmailSenderV1Imp;
import components.emailSender.EmailSenderV2;
import components.emailSender.EmailSenderV2Imp;
import components.excel.ExcelCreator;
import components.excel.ExcelCreatorImp;
import implementation.ImplementationFramework;

public class FIRClient {

	public static Object getFIRApp() {

		Object fir = new FIR();

		// creating implementation
		EmailSenderV1Imp eSender1 = new EmailSenderV1Imp();
		EmailSenderV2Imp eSender2 = new EmailSenderV2Imp();
		ExcelCreator e = new ExcelCreatorImp();
		// setting interfaces
		ImplementationFramework.setInterface(EmailSenderV1.class);
		ImplementationFramework.setInterface(EmailSenderV2.class);
//		ImplementationFramework.setInterface(ExcelCreator.class);

		// getting object
		fir = ImplementationFramework.getObject(fir, new Object[] { eSender1, eSender2, e });

		return fir;

	}

}
