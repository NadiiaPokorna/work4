package dev.pokorna.exception;

import dev.pokorna.command.SortToys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private static final String USERNAME = "danylo1302@gmail.com";
    private static final String PASSWORD = "qapb sbpm dids yifn";

    public static void sendEmail(String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nadiiaps2015@gmail.com"));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Помилка надсилання інформації про критичну помилку на електронну адресу");

        }
    }
}
