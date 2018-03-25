package com.doublegaurd.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
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

public class Emailer {

	private static String fromEmail = "IDPSsystems@gmail.com";
	private static String password = "multitier";

	public static void main(String[] args) throws Exception {
		String emails[] = new String[2];
		emails[0] = "IDPSsystems@gmail.com";
		emails[1] = "IDPSsystems@gmail.com";
		// sendEmail(emails, "Sigma Help Desk - Forget Password", "");
		sendEmail(emails, "hi 5", "test email 123");
	}

	public static void sendEmail(String toUser[], String subject,
			String messageBody) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			Address address = new InternetAddress(fromEmail);
			message.setFrom(address);

			for (String toEmail : toUser) {
				Address toAddress = new InternetAddress(toEmail);
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}

			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart
					.setContent(
							messageBody
									+ "</h1><br><br><br><br><b>  Regards,<br> IDPS Administrator</b>",
							"text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Mail Sent to= " + arrayToString(toUser));

		} catch (MessagingException e) {
			e.printStackTrace();
			//throw e;
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}
	}

	public static String arrayToString(String toUser[]) {
		StringBuilder toStr = new StringBuilder("");
		if (null != toUser && toUser.length > 0) {
			for (String str : toUser) {
				toStr.append(str).append(", ");
			}
		}
		toStr.append(" at Time:").append(new Date());
		return toStr.toString();
	}
}
