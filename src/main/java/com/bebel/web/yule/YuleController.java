package com.bebel.web.yule;

import com.bebel.bdd.dao.YuleDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.response.GeneralResponse;
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
    public ResponseEntity<YuleResponse> getSave(@RequestBody final KongregateRequest request) {
        final YuleResponse response = new YuleResponse();

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
    public ResponseEntity<GeneralResponse> save(@RequestBody final YuleSaveRequest request) {
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

    private void checkPass(final KongregateRequest request) throws BadCredentialException {
        final String secretRequest = request.getSecretPass();
        final String secret = "Yule5497" + request.getUsername();
        final String hashSecret = HashUtil.getInstance().hash(secret);

        logger.debug("Verification du code secret : " + secretRequest + " avec le code secret generé : " + hashSecret);

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
