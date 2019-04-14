package com.bebel.web.yule;

import com.bebel.bdd.dao.YuleDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.util.HashUtil;
import com.bebel.soclews.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/yule")
public class YuleController {
    private final Logger logger = new Logger(getClass());

    @Autowired
    private YuleDao dao;

    @CrossOrigin(origins = "https://game302789.konggames.com")
    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<String> getSave(@RequestBody final KongregateRequest request) {
        logger.info("Recuperation de la sauvegarde : " + request.getUsername());
        try {
            checkPass(request);
            final String response = dao.getSave(request.getUsername());
            if (StringUtils.isEmpty(response))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (final BadCredentialException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "https://game302789.konggames.com")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final YuleSaveRequest request) {
        logger.info("Lancement de la sauvegarde : " + request.getUsername());
        try {
            checkPass(request);

            logger.info("Sauvegarde de : " + request.getData());
            dao.save(request.getUsername(), request.getData());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (final BadCredentialException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private void checkPass(final KongregateRequest request) throws BadCredentialException {
        final String secretRequest = request.getSecretPass();
        final String secret = "Yule5497" + request.getUsername();
        final String hashSecret = HashUtil.getInstance().hash(secret);

        logger.debug("Verification du code secret : " + secretRequest + " avec le code secret generé : " + hashSecret);

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
