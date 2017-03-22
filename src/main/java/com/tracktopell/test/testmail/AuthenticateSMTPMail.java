package com.tracktopell.test.testmail;

import java.net.ConnectException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

/**
 * Hello world!
 *
 */
public class AuthenticateSMTPMail {

	public static void main(String[] args) {
		System.out.println("AUTHEHTICATE MAIL");

		// Sender's email ID needs to be mentioned
		final String username = "aestrada@perfumeriamarlen.com.mx";
		final String password = "Dh3rku61c";
		
		String host = "mail.perfumeriamarlen.com.mx";
		String port = "26";
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		//properties.put		  ("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.socketFactory.port", port);
		//properties.put        ("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//properties.put        ("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", port);		
		
		Session session = null;
		
		try {			
			System.out.println("trying:1");
			session = Session.getDefaultInstance(properties);
			System.out.println("Session created....");
			Transport tr = session.getTransport("smtp");
			
			tr.connect(host, username, password);			
			System.out.println("Connected....");			
		} catch (MessagingException me) {
			//me.printStackTrace(System.err);
			System.out.println("FAIL1:"+me.getClass()+", cause?"+me.getCause().getClass());
			
			if(me instanceof javax.mail.MessagingException && me.getCause()!=null && me.getCause() instanceof java.net.ConnectException){
				
				try {
					System.out.println("trying:2");
					properties.setProperty("mail.smtp.port", "26");	
					session = Session.getDefaultInstance(properties);
					System.out.println("Session created....");
					Transport tr = session.getTransport("smtp");

					tr.connect(host, username, password);			
					System.out.println("Connected....");			
					
				} catch (MessagingException me2) {
					System.out.println("FAIL1:"+me2.getClass()+", cause?"+(me2.getCause()!=null?me2.getCause().getClass():""));
					//me2.printStackTrace(System.err);
				}
			} else {
				
			}
		}
	}
}
