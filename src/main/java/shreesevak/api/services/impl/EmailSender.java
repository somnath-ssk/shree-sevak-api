package shreesevak.api.services.impl;
 
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import java.util.Properties;
import org.springframework.stereotype.Service;
 
@Service
public class EmailSender {
 
    public boolean sendOtpEmail(String subject, String message, String to){
 
    	boolean f =false;
    	String fromEmail = "shreeseva975@gmail.com"; // your email
        String password = "angs feia fhtd sucq"; //  your email password
        String host ="smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
 
        Session session = Session.getInstance(props, new Authenticator() {
        	@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);  // Use a different variable name
        try {
        m.setFrom(fromEmail);
        m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        m.setSubject(subject);
//        m.setText(message);
        m.setContent(message, "text/html");
        System.out.println(fromEmail);
 
        Transport.send(m);
        f=true;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return f;
   }
}