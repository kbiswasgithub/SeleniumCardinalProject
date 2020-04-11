package Application_Specific_Library;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail  {
	
 public static void SetAndSendMail(String Status)
 {	
	final String username = "kumar.rohit01@cardinalhealth.com";
    final String password = "India2030";
    String attachFile = "c:/Temp/URLHealthCheck/Report.html";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "outlook.office365.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("kumar.rohit01@cardinalhealth.com"));
        //
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("kumar.rohit01@cardinalhealth.com"));
        DataSource source = new FileDataSource(attachFile);
        message.setDataHandler(new DataHandler(source));
        message.setSubject("CAH URL Monitoring Execution status is "+Status);

        Transport.send(message);

        System.out.println("Mail is sent successfully");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}

}
