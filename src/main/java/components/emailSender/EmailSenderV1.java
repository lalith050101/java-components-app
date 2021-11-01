package components.emailSender;

public interface EmailSenderV1 {

	public void sendEmail(String to, String subject, String body);

//	public void sendEmail(String[] to,String[] cc, String[] bcc, String subject,String body);

}
