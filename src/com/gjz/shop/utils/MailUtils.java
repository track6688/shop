package com.gjz.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	/**
	 * �����ʼ�
	 * @param to �ռ���
	 * @param code ������
	 */
	public static void sendMail(String to, String code)
	{
		/**
		 * 1�����һ��Session����
		 * 2������һ�������ʼ��Ķ���Message
		 * 3�������ʼ�Transport
		 */
		
		Properties props = new Properties();
		
		props.setProperty("mail.host", "localhost");
		
		Session session = Session.getInstance(props, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "111");
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
		
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			
			//����CC ���� BCC
			
			//�ʼ�����
			message.setSubject("����̫��̳ǹٷ������ʼ�");
			 
			String url = "http://192.168.0.104:8080/MyShop/user_active.action?code=" + code;
			//�ʼ�����
			message.setContent("<h1>̫��̳ǹٷ������ʼ���</h1>"
					+ "<h3>"
					+ "<a href='" + url + "'> " + url + "</a>"
					+ "</h3>", "text/html;charset=UTF-8");
			
			Transport.send(message);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		sendMail("aaa@shop.com", "1111111111111");
	}

}
