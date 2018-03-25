package com.doublegaurd.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {
	public static void main(String[] args) {
		String emails[] = new String[2];
		emails[0] = "IDPSsystems@gmail.com";
		emails[1] = "prashantnagawade@gmail.com";
		// sendEmail(emails, "Sigma Help Desk - Forget Password", "");
		sendEmail(emails, "hi there", "test email !!!!!");
	}
	public static void sendEmail(String toUser[], String subject,
			String messageBody){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("IDPSsystems@gmail.com",
								"multitier");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("IDPSsystems@gmail.com"));
			
			for (String toEmail : toUser) {
				Address toAddress = new InternetAddress(toEmail);
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			
			
			message.setSubject(subject);
			message.setText(messageBody);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
