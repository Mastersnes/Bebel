package com.bebel.web.util;

import com.bebel.soclews.util.Logger;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

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
        final String api = System.getenv("MAILGUN_API_KEY");
        final String domain = System.getenv("MAILGUN_DOMAIN");

        try {
            LOGGER.info("Envoi du mail (api) " + api);
            HttpResponse<JsonNode> request = Unirest.post("https://api.eu.mailgun.net/v3/" + domain + "/messages")
                    .basicAuth("api", api)
                    .field("from", "lesjeuxdebebel.contact@gmail.com")
                    .field("to", "lesjeuxdebebel.contact@gmail.com")
                    .field("subject", subject)
                    .field("text", message)
                    .asJson();

            LOGGER.info("---Mail---");
            LOGGER.info("Mail envoye a  : " + to);
            LOGGER.info("Sujet : " + subject);
            LOGGER.info("Message : " + message);
            LOGGER.info("----------");
            if (request != null) {
                LOGGER.info(request.getStatus() + " : " + request.getBody());
            }
        } catch (final UnirestException e) {
            LOGGER.err("Exception lors de l'envoi du mail: ", e);
        }
    }
}
