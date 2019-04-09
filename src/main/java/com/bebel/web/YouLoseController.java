package com.bebel.web;

import enums.SaveType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import request.SaveRequest;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {

  @GetMapping("/saves/{username}")
  @ResponseBody
  public ResponseEntity<String> getSaves(@PathVariable("username") final String username) {
      return new ResponseEntity<>("Voici les sauvegardes de " + username, HttpStatus.OK);
  }

  @GetMapping("/save/{username}/{type}")
  @ResponseBody
  public ResponseEntity<String> getSave(@PathVariable("username") final String username,
                                        @PathVariable("type") final SaveType type) {
      return new ResponseEntity<>("Ceci est la sauvegarde " + type + " de " + username, HttpStatus.OK);
  }

  @PostMapping(value = "/save")
  @ResponseBody
  public ResponseEntity<String> save(@RequestBody final SaveRequest request) {
    return new ResponseEntity<>("enregistrement de la sauvegarde " + request.getData() + "de type :" + request.getType() + " pour " + request.getUsername(), HttpStatus.CREATED);
  }
}
