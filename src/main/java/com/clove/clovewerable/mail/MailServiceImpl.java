package com.clove.clovewerable.mail;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.http.client.utils.URIUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author @Unascribed
 */
@Service
public class MailServiceImpl implements MailService, Serializable {

    private static final long serialVersionUID = 6024368516850157102L;

    @Autowired
    MailConfig mailSender;

    @Override
    public void sendEmail(final String user, String expLink) {

        MimeMessagePreparator preparator = getMessagePreparator(user, expLink);

        try {

            mailSender.getMailSender().send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final String user, String expLink) {

        try {

            final URI toSendLink = URIUtils.createURI("http", "localhost", 8081, "/Clovewerable/rest/clovewearable/validateReg", ("expToken=" + expLink), null);

            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {
                    mimeMessage.setFrom("clovercoder@gmail.com");
                    mimeMessage.setRecipient(Message.RecipientType.TO,
                            new InternetAddress("youremail@gmail.com"));
                    mimeMessage.setText("Dear User, Click on the link to login." + toSendLink.toString() + ".");
                    mimeMessage.setSubject("Your Login Link for Registration !");
                }
            };
            return preparator;
        } catch (URISyntaxException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
