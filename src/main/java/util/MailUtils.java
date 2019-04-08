package util;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class MailUtils extends Thread {
//	private Logger logger = new Logger(getClass());

    private String to;
    private String subject;
    private String message;
    private String from;

    public void sendNoReply(final String to, final String subject, final String message) {
        sendMail("noReply", to, subject, message);
    }
    public void sendMail(final String to, final String subject, final String message) {
        sendMail("lesjeuxdebebel.contact@gmail.com", to, subject, message);
    }
    public void sendMail(final String from, final String to, final String subject, final String message) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
        start();
    }

    @Override
    public void run() {
        final String userName = System.getenv("SENDGRID_USERNAME");
        final String password = System.getenv("SENDGRID_PASSWORD");
        final SendGrid sendgrid = new SendGrid(userName, password);

        final SendGrid.Email email = new SendGrid.Email();

        email.addTo(to);
        email.setFrom(from);
        email.setSubject(subject);
        email.setHtml(message);

//        logger.debug("---Mail---");
//        logger.debug("Mail envoye a : " + to);
//        logger.debug("Sujet : " + subject);
//        logger.debug("Message : " + message);
//        logger.debug("----------");

        try {
            final SendGrid.Response reponse = sendgrid.send(email);
//            logger.info("Le mail a bien ete envoye");
//            if (reponse != null) {
//                logger.info(reponse.getCode() + " : " + reponse.getMessage());
//            }
        } catch (final SendGridException e) {
//            logger.err("Erreur lors de l'envoi du mail : ", e);
        }
    }
}
