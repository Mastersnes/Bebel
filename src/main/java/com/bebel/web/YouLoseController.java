package com.bebel.web;

import com.bebel.bdd.dao.YouLoseDao;
import enums.SaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import request.GetSaveRequest;
import request.KongregateRequest;
import request.SaveRequest;
import response.GetSavesResponse;

import java.util.Map;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {
    @Autowired
    private YouLoseDao dao;

    @PostMapping("/getSaves")
    @ResponseBody
    public ResponseEntity<GetSavesResponse> getSaves(@RequestBody final KongregateRequest request) {
        final Map<SaveType, String> datas = dao.getSaves(request.getUsername());
        if (CollectionUtils.isEmpty(datas)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            final GetSavesResponse response = new GetSavesResponse();
            response.getSave().putAll(datas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/getSave")
    @ResponseBody
    public ResponseEntity<String> getSave(@RequestBody final GetSaveRequest request) {
        final String response = dao.getSave(request.getUsername(), request.getType());

        if (StringUtils.isEmpty(response))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final SaveRequest request) {
        dao.save(request.getUsername(), request.getType(), request.getData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public YouLoseDao getDao() {
        return dao;
    }
}
