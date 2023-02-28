package heroku.huelladecarbono.service.NotificacionService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EnviarMail {

    private static final Properties prop = new Properties();
    private static final Session session;
    private static final String username = "tphcgrupo3@gmail.com";
    // GMAIL app specific password: https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor
    private static final String password = "ykatrutioevxttcd";

    static {
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void send(String to) throws MessagingException {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tphcgrupo3@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("Recomendaciones");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent("Se envía el siguiente link con recomendaciones " +
                    "para reducir su huella de carbono: ", "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            Transport.send(message);
    }
}
