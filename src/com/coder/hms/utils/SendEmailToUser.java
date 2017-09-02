package com.coder.hms.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailToUser {

	private Message message;
	private Properties props;
	public SendEmailToUser() {
		
	}
	
	public void setReadyForEmail(String username, String password) {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		message = new MimeMessage(session);
	}
	
	public void setFrom(String from, String to) {
		try {
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void setEmailBody(String subject, String messageBody) {
		try {
			message.setSubject(subject);
			message.setText(messageBody);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTheEmail() {
		try {
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
