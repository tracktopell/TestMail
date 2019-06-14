package com.tracktopell.test.testmail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class SendSMTPMail {

	public static void main(String[] args) {
		System.out.println("SEND MAIL");
		if(args.length != 9){
			System.err.println("usage: username password host port auth from to subject body");
			System.exit(1);
		}
		final String username = args[0];
		final String password = args[1];
		String host     = args[2];
		String port     = args[3];
		String auth     = args[4];
		String from     = args[5];
		String to       = args[6];
		String subject  = args[7];
		String body     = args[8];


		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", auth);
		properties.setProperty("mail.smtp.port", port);
		// Get the default Session object.
		Session session = null;

		try {
			
			System.out.println("before Session created:");
			
			session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			System.out.println("Session created....");
			
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// Now set the actual message
			message.setText(body+"["+sdf.format(new Date())+"]");

			Transport.send(message);

			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace(System.err);
		}
	}
}
