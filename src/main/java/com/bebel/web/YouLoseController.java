package com.bebel.web;

import com.bebel.App;
import com.bebel.bdd.dao.YouLoseDao;
import com.bebel.exception.BadCredentialException;
import com.bebel.soclews.request.KongregateRequest;
import com.bebel.soclews.util.HashUtil;
import com.bebel.youloseClient.enums.SaveType;
import com.bebel.youloseClient.request.GetSaveRequest;
import com.bebel.youloseClient.request.SaveRequest;
import com.bebel.youloseClient.response.GetSavesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {
    @Autowired
    private YouLoseDao dao;

    @PostMapping("/getSaves")
    @ResponseBody
    public ResponseEntity<GetSavesResponse> getSaves(@RequestBody final KongregateRequest request) {
        try {
            checkPass(request);
            final Map<SaveType, String> datas = dao.getSaves(request.getUsername());
            if (CollectionUtils.isEmpty(datas)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else {
                final GetSavesResponse response = new GetSavesResponse();
                response.getSave().putAll(datas);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (final BadCredentialException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<String> getSave(@RequestBody final GetSaveRequest request) {
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

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final SaveRequest request) {
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

        if (secretRequest == null || !secretRequest.equals(hashSecret))
            throw new BadCredentialException();
    }
}
