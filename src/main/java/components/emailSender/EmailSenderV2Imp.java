package components.emailSender;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSenderV2Imp implements EmailSenderV2, Serializable {

	private String from;
	private String password;
	
	private void loadCreds() {
		try {
		Properties props = new Properties();
		props.load(new FileInputStream("gmail-creds.properties"));
		from = (String) props.get("email");
		password = (String) props.get("password");
		}catch(Exception e) {System.out.println("can't get Gmail Creds...");}
	}

	@Override
	public void sendEmail(String to, String subject, String body, String attachment) {
		loadCreds();
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			// create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText(body);

			// create new MimeBodyPart object and set DataHandler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			DataSource source = new FileDataSource(attachment);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(attachment);

			// create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// set the multiplart object to the message object
			message.setContent(multipart);

			// send message
			Transport.send(message);
			System.out.println("message sent successfully to " + to);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
