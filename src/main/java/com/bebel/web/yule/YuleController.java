package com.bebel.web.yule;

import com.bebel.bdd.dao.YuleDao;
import com.bebel.soclews.request.KongregateRequest;
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
        final String response = dao.getSave(request.getUsername());

        if (StringUtils.isEmpty(response))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody final YuleSaveRequest request) {
        dao.save(request.getUsername(), request.getData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public YuleDao getDao() {
        return dao;
    }
}
