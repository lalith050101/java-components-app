package components.emailSender;

import java.io.Serializable;

public interface EmailSenderV1 extends Serializable {

	public void sendEmail(String to, String subject, String body);

//	public void sendEmail(String[] to,String[] cc, String[] bcc, String subject,String body);
}
