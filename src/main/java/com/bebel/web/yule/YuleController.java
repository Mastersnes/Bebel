package com.bebel.web.yule;

import com.bebel.bdd.dao.YuleDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/yule")
public class YuleController {
    @Autowired
    private YuleDao dao;

    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<String> getSave(@RequestBody final KongregateRequest request) {
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

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final YuleSaveRequest request) {
        try {
            checkPass(request);
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

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
