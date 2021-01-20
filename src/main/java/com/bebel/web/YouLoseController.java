package com.bebel.web;

import com.bebel.bdd.dao.YouLoseDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.util.HashUtil;
import com.bebel.soclews.util.Logger;
import com.bebel.youloseClient.enums.SaveType;
import com.bebel.youloseClient.request.GetSaveRequest;
import com.bebel.youloseClient.request.SaveRequest;
import com.bebel.youloseClient.response.GetSavesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {
    private final Logger logger = new Logger(getClass());

    @Autowired
    private YouLoseDao dao;

    @CrossOrigin(origins = {"https://game302789.konggames.com", "https://game287566.konggames.com"})
    @PostMapping("/getSaves")
    @ResponseBody
    public ResponseEntity<GetSavesResponse> getSaves(@RequestBody final KongregateRequest request) {
        final GetSavesResponse response = new GetSavesResponse();
        logger.info("Recuperation des sauvegardes de : " + request.getUsername());
        try {
            checkPass(request);
            final Map<SaveType, String> datas = dao.getSaves(request.getUsername());
            if (CollectionUtils.isEmpty(datas)) {
                response.setCodeRetour(HttpStatus.NO_CONTENT.value());
                response.setMessage("No Content");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCodeRetour(0);
                response.setMessage("OK");
                response.getSave().putAll(datas);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (final BadCredentialException e) {
            response.setCodeRetour(HttpStatus.FORBIDDEN.value());
            response.setMessage("Accès refusé");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = {"https://game302789.konggames.com", "https://game287566.konggames.com"})
    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<String> getSave(@RequestBody final GetSaveRequest request) {
        logger.info("Recuperation de la sauvegardes de : " + request.getUsername() + " de type " + request.getType());
        try {
            checkPass(request);
            final String response = dao.getSave(request.getUsername(), request.getType());
            if (StringUtils.isEmpty(response))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (final BadCredentialException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = {"https://game302789.konggames.com", "https://game287566.konggames.com"})
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final SaveRequest request) {
        logger.info("Sauvegardes de : " + request.getData() + " pour le type " + request.getType() + " Pour " + request.getUsername());
        try {
            checkPass(request);
            dao.save(request.getUsername(), request.getType(), request.getData());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (final BadCredentialException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private void checkPass(final KongregateRequest request) throws BadCredentialException {
        final String secretRequest = request.getSecretPass();
        final String secret = "YouLose1145" + request.getUsername();
        final String hashSecret = HashUtil.getInstance().hash(secret);

        logger.debug("Verification du code secret : " + secretRequest + " avec le code secret generé : " + hashSecret);

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
