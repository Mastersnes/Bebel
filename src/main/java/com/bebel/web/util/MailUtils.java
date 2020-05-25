package com.bebel.web.util;

import com.bebel.soclews.util.Logger;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class MailUtils extends Thread {
    private static Logger LOGGER = new Logger(MailUtils.class);

    private String to;
    private String subject;
    private String message;

    public void sendMail(final String to, final String subject, final String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        start();
    }

    @Override
    public void run() {
        final String api = System.getenv("SENDGRID_API");
        final SendGrid sendgrid = new SendGrid(api);

        final SendGrid.Email email = new SendGrid.Email();

        email.addTo(to);
        email.setFrom("lesjeuxdebebel.contact@gmail.com");
        email.setSubject(subject);
        email.setHtml(message);

        try {
            final SendGrid.Response reponse = sendgrid.send(email);
            LOGGER.info("---Mail---");
            LOGGER.info("Mail envoye a  : " + to);
            LOGGER.info("Sujet : " + subject);
            LOGGER.info("Message : " + message);
            LOGGER.info("----------");
            if (reponse != null) {
                LOGGER.info(reponse.getCode() + " : " + reponse.getMessage());
            }
        } catch (final SendGridException e) {
            LOGGER.err("Exception lors de l'envoi du mail: ", e);
        }
    }
}
