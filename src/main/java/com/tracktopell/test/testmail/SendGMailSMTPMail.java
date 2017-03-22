package com.tracktopell.test.testmail;

import static java.lang.ProcessBuilder.Redirect.to;
import static java.util.Date.from;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * com.tracktopell.test.testmail.SendGMailSMTPMail
 */
public class SendGMailSMTPMail {

	static String username = null;
	static String d_password = null;
	static String d_host = "smtp.gmail.com";
	static String d_port = "465";
	static String m_to = null;
	static String m_subject = null;
	static String m_text = null;

	public SendGMailSMTPMail(String gmailSender, String gmailPassword, String subject, String body, String filename,String emailRecipt) {
		Properties props = new Properties();

		username = gmailSender;
		d_password = gmailPassword;

		props.put("mail.smtp.user", gmailSender);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator(gmailSender, gmailPassword);

			Session session = Session.getInstance(props, auth);
			//session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(gmailSender));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipt));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(body);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			
			if(filename != null && filename.trim().length()>1 && !filename.equalsIgnoreCase("null")){
				// Part two is attachment
				messageBodyPart = new MimeBodyPart();			
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);
			}
			
			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.err.println("Sent message successfully....");
			
		} catch (Exception mex) {
			mex.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {
		if(args.length != 6){
			System.err.println("uasage:   java com.tracktopell.test.testmail.SendGMailSMTPMail   user@mail   password   subject   'body' filePathForAtach=null  destination@mail");
			System.exit(1);
		}
		SendGMailSMTPMail blah = new SendGMailSMTPMail(args[0], args[1], args[2], args[3], args[4], args[5]);
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		private String u;
		private String p;

		public SMTPAuthenticator(String u, String p) {
			this.u = u;
			this.p = p;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(u, p);
		}
	}
}
