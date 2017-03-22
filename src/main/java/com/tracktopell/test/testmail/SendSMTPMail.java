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


		// Recipient's email ID needs to be mentioned.
		String to = "tracktopell@gmail.com";

		// Sender's email ID needs to be mentioned
		final String username = "aestrada@perfumeriamarlen.com.mx";//"susymejia@realclean.com.mx";
		final String password = "Dh3rku61c"; // "mejiasusy"
		// Assuming you are sending email from perfumeriamarlen.com.mx
		String host = "mail.perfumeriamarlen.com.mx";//"smtp.realclean.com.mx";
		String port = "26";//"25";
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
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
			message.setFrom(new InternetAddress("Ing. Alfredo Estrada G.<aestrada@perfumeriamarlen.com.mx>"));
			//message.setFrom(new InternetAddress("AzucenaX MejiaX BadilloX <susymejia@realclean.com.mx>"));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("ANOTHER JAVA MAVEN MAIL AGAIN !");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// Now set the actual message
			message.setText("FACTURA:Fecha=" + sdf.format(new Date())+"\n" +
					"----------------------------------------------------------------------------------------------------------------------------\n"+
					"60	1RA	185	Producto 7502244767778	7502244767778 VERT LAVATRASTES / FORMULA DESENGRASANTE 900 ML	$ 15.21	$ 912.60\n" +
					"15	1RA	4	Producto 7502244767396	7502244767396 VERT LIMPIADOR MULTIUSOS / LAVANDA 3.78 ML	$ 24.97	$ 374.55\n" +
					"30	1RA	436	Producto 6926360987086	6926360987086 4 TODAY CEPILLO DENTAL EXHIBIDOR / T 300 - 2PZ 12.0 PZ	$ 95.00	$ 2,850.00\n" +
					"6	REG	43	Producto 6926360987086	6926360987086 4 TODAY CEPILLO DENTAL EXHIBIDOR / T 300 - 2PZ 12.0 PZ	$ 0.00	$ 0.00\n" +
					"120	1RA	165	Producto 7591083017830	7591083017830 COLGATE CEPILLO PACK / SLIM SOFT SUAVE MACIA 2.0 PZ	$ 35.32	$ 4,238.40\n" +
					"12	1RA	141	Producto 7506267902048	7506267902048 BLUMEN SHAMPOOO NIÑOS / FROZEN 3 EN 1 300 ML	$ 15.51	$ 186.12\n" +
					"12	1RA	79	Producto 7804915009300	7804915009300 BLUMEN SHAMPOO NIÑOS / MI VILLANO FAVORITO 3 EN 1 300 ML	$ 15.51	$ 186.12\n" +
					"12	1RA	133	Producto 7502244766818	7502244766818 BLUMEN SHAMPOO, NIÑOS / CAPITAN AMERICA 2 EN 1 300 ML	$ 15.51	$ 186.12\n" +
					"12	1RA	36	Producto 7509546061689	7509546061689 COLGATE CEPILLO PACK / SLIM SOFT BLACK 2.0 PZ	$ 35.32	$ 423.84\n" +
					"");
			
			
/*
60	1RA	185	Producto 7502244767778	7502244767778 VERT LAVATRASTES / FORMULA DESENGRASANTE 900 ML	$ 15.21	$ 912.60
15	1RA	4	Producto 7502244767396	7502244767396 VERT LIMPIADOR MULTIUSOS / LAVANDA 3.78 ML	$ 24.97	$ 374.55
30	1RA	436	Producto 6926360987086	6926360987086 4 TODAY CEPILLO DENTAL EXHIBIDOR / T 300 - 2PZ 12.0 PZ	$ 95.00	$ 2,850.00
6	REG	43	Producto 6926360987086	6926360987086 4 TODAY CEPILLO DENTAL EXHIBIDOR / T 300 - 2PZ 12.0 PZ	$ 0.00	$ 0.00
120	1RA	165	Producto 7591083017830	7591083017830 COLGATE CEPILLO PACK / SLIM SOFT SUAVE MACIA 2.0 PZ	$ 35.32	$ 4,238.40
12	1RA	141	Producto 7506267902048	7506267902048 BLUMEN SHAMPOOO NIÑOS / FROZEN 3 EN 1 300 ML	$ 15.51	$ 186.12
12	1RA	79	Producto 7804915009300	7804915009300 BLUMEN SHAMPOO NIÑOS / MI VILLANO FAVORITO 3 EN 1 300 ML	$ 15.51	$ 186.12
12	1RA	133	Producto 7502244766818	7502244766818 BLUMEN SHAMPOO, NIÑOS / CAPITAN AMERICA 2 EN 1 300 ML	$ 15.51	$ 186.12
12	1RA	36	Producto 7509546061689	7509546061689 COLGATE CEPILLO PACK / SLIM SOFT BLACK 2.0 PZ	$ 35.32	$ 423.84
*/

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace(System.err);
		}
	}
}
