package components.emailSender;

import java.io.Serializable;

public interface EmailSenderV2 extends Serializable {

	public void sendEmail(String to, String subject, String body, String attachment);
}
