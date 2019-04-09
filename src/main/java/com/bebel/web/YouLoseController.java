package com.bebel.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import request.SaveRequest;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {

  @GetMapping("/save/{username}/{type}")
  @ResponseBody
  public ResponseEntity<String> getSave(@PathVariable("username") final String username,
                                        @PathVariable(value = "type", required = false) final String type) {
    if (type == null) {
      return new ResponseEntity<>("Voici les sauvegardes de " + username, HttpStatus.OK);
    }else {
      return new ResponseEntity<>("Ceci est la sauvegarde " + type + " de " + username, HttpStatus.OK);
    }
  }

  @PostMapping(value = "/save")
  @ResponseBody
  public ResponseEntity<String> save(@RequestBody final SaveRequest request) {
    return new ResponseEntity<>("enregistrement de la sauvegarde " + request.getData() + "de type :" + request.getType() + " pour " + request.getUsername(), HttpStatus.CREATED);
  }
}
