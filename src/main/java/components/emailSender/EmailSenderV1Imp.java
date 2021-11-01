package components.emailSender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderV1Imp implements EmailSenderV1 {

	private String from = "lalithcodes@gmail.com";
	private String password = "lkcodes@123";


	@Override
	public void sendEmail(String to, String subject, String body) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully to " + to);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
