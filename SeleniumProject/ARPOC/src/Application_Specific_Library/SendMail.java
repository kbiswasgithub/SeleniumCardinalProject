package Application_Specific_Library;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
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

//import org.apache.log4j.Logger;

public class SendMail {
	

	private final String to = "G-IAM-Proxy-OffShore@cardinalhealth.com";
	private final String from = "GMB-EIT-ProxyServices-CriticalNotifications@cardinalhealth.com";
	
	private final String SMTP_AUTHENTICATION_USER_NAME = null; 
//		"ravi.kansal";
	//sender's credentials - password
	private final String SMTP_AUTHENTICATION_USER_PASSWORD = null; 
//		"Columbus@123";
	private final TimeZone tz = TimeZone.getTimeZone("Ohio/Dublin");
	// SMTP Host Details
	private final String host = "dubintonlysmtp.cardinalhealth.net";
	private Properties props = new Properties();
	private Session session = null;
	//HTMLGenerator objhtmlwriter=new HTMLGenerator();
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void sendingMail(String msgHdr) {
		
		StringBuilder buffer = new StringBuilder();
		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		if(SMTP_AUTHENTICATION_USER_NAME == null || SMTP_AUTHENTICATION_USER_NAME.equals("") 
				||SMTP_AUTHENTICATION_USER_PASSWORD == null || SMTP_AUTHENTICATION_USER_PASSWORD.equals("")){
	    	session = Session.getDefaultInstance(props, null);
	    } else {
	    	props.put("mail.smtp.auth", "true");
	    	final Authenticator authenticator = new SMTPAuthenticator();
	    	session = Session.getDefaultInstance(props, authenticator);
	    }
		try {
			
			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			
			
				
					message.setSubject(msgHdr);
				
				String filename = "C:/Temp/URLHealthCheck/Report.html";
				DataSource source = new FileDataSource(filename);
				message.setDataHandler(new DataHandler(source));
				//messageBodyPart.setFileName(filename);
				//multipart.addBodyPart(messageBodyPart);
			
			
			//message.setContent(multipart,"text/html; charset=utf-8");
			Transport.send(message);
			
			
		} catch (MessagingException e) {
//			LOGGER.error("There is an error "+e.getMessage());
//			LOGGER.debug("There is an error "+e.getMessage());
			throw new RuntimeException(e);
		} catch(Exception e) {
//			LOGGER.error("There is an error "+e.getMessage());
//			LOGGER.debug("There is an error "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	class SMTPAuthenticator extends Authenticator
	{
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTHENTICATION_USER_NAME;
	        String password = SMTP_AUTHENTICATION_USER_PASSWORD;
	        return new PasswordAuthentication(username, password);
	    }
	}
}