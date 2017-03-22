/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracktopell.test.testmail;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;

public class ReadingEmail {

	static final String username = "aestrada@perfumeriamarlen.com.mx";
	static final String password = "Dh3rku61c";
	static final String host     = "mail.perfumeriamarlen.com.mx";

	public static void main(String[] args) {
		System.out.println("-> read mail");
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", host);
		props.setProperty("mail.pop3.port", "110");
		String port = "26";
		props.setProperty("mail.smtp.socketFactory.port", port);
		props.setProperty("mail.smtp.port", port);		
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect(host, username, password);
			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_WRITE); // Folder.READ_ONLY
			int messageCount = inbox.getMessageCount();
			System.out.println("Total Messages:" + messageCount);
			int startMessage = 1;//messageCount - 5;
			int endMessage = messageCount;

			if (messageCount < 5) {
				startMessage = 0;
			}

			Message[] messages = inbox.getMessages(startMessage, endMessage);
			
			System.out.println("=========================>>>("+startMessage+","+endMessage+")");
			int j=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			for (Message message : messages) {

				boolean isMessageRead = true;

				for (Flags.Flag flag : message.getFlags().getSystemFlags()) {
					if (flag == Flags.Flag.SEEN) {
						isMessageRead = true;
						break;
					}
				}

				message.setFlag(Flags.Flag.SEEN, true);
				
				System.out.println("MAIL["+j+"]\tDATE:"+sdf.format(message.getSentDate())+",FROM:"+message.getFrom()[0]+", SUBJECT:"+message.getSubject() + " "
						+ (isMessageRead ? " [READ]" : " [UNREAD]"));
				j++;
			}

			inbox.close(true);
			System.out.println("Done....");
			store.close();
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}
