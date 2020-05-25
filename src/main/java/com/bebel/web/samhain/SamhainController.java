package com.bebel.web.samhain;

import com.bebel.bdd.dao.SamhainDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.response.GeneralResponse;
import com.bebel.soclews.util.HashUtil;
import com.bebel.soclews.util.Logger;
import com.bebel.web.util.MailUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
@RequestMapping("/samhain")
public class SamhainController {
    private final Logger logger = new Logger(getClass());

    @Autowired
    private SamhainDao dao;

    @CrossOrigin(origins = "https://game302789.konggames.com")
    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<SamhainResponse> getSave(@RequestBody final KongregateRequest request) {
        final SamhainResponse response = new SamhainResponse();

        logger.info("Recuperation de la sauvegarde : " + request.getUsername());
        try {
            checkPass(request);
            final String data = dao.getSave(request.getUsername());
            if (StringUtils.isEmpty(data)) {
                response.setCodeRetour(HttpStatus.NO_CONTENT.value());
                response.setMessage("No Content");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCodeRetour(0);
                response.setMessage("OK");
                response.setData(data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (final BadCredentialException e) {
            response.setCodeRetour(HttpStatus.FORBIDDEN.value());
            response.setMessage("Accès refusé");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "https://game302789.konggames.com")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<GeneralResponse> save(@RequestBody final SamhainSaveRequest request) {
        final GeneralResponse response = new GeneralResponse();
        logger.info("Lancement de la sauvegarde : " + request.getUsername());
        try {
            checkPass(request);

            logger.info("Sauvegarde de : " + request.getData());
            dao.save(request.getUsername(), request.getData());

            response.setCodeRetour(0);
            response.setMessage("OK");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (final BadCredentialException e) {
            response.setCodeRetour(HttpStatus.FORBIDDEN.value());
            response.setMessage("Accès refusé");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

//    @CrossOrigin(origins = "https://game302789.konggames.com")
    @PostMapping(value = "/sendTrad")
    @ResponseBody
    public ResponseEntity<GeneralResponse> addTrad(@RequestBody final SamhainNewTradRequest request) {
        final GeneralResponse response = new GeneralResponse();
        logger.info("Ajout d'une nouvelle traduction");
        try {
            checkPass(request, true);

            final String encoded = request.getNewTrad();
            final String decoded = new String(Base64.getDecoder().decode(encoded.getBytes(UTF_8)));
            logger.info("Ajout de : " + decoded);

            final StringBuilder message = new StringBuilder()
                    .append("Une proposition de traduction a été envoyé pour les textes suivants :")
                    .append("</br>")
                    .append(decoded);

            final MailUtils mailUtils = new MailUtils();
            mailUtils.sendMail("lesjeuxdebebel.contact@gmail.com",
                    "Proposition d'une nouvelle traduction",
                    message.toString());

            response.setCodeRetour(0);
            response.setMessage("OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (final BadCredentialException e) {
            response.setCodeRetour(HttpStatus.FORBIDDEN.value());
            response.setMessage("Accès refusé");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    private void checkPass(final KongregateRequest request) throws BadCredentialException {
        checkPass(request, false);
    }
    private void checkPass(final KongregateRequest request, final boolean withoutUser) throws BadCredentialException {
        final String secretRequest = request.getSecretPass();
        String secret = "Samhain4842";
        if (!withoutUser) secret += request.getUsername();
        final String hashSecret = HashUtil.getInstance().hash(secret);

        logger.debug("Verification du code secret : " + secretRequest + " avec le code secret generé : " + hashSecret);

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
