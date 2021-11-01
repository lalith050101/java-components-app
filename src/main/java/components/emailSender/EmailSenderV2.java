package components.emailSender;

public interface EmailSenderV2 {

	public void sendEmail(String to, String subject, String body, String attachment);
}
