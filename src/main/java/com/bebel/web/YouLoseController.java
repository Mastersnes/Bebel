package com.bebel.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {

  @GetMapping("/getSaves")
  public ResponseEntity<String> getSave(@RequestParam String username) {
    return new ResponseEntity<>("Voici les sauvegarde de " + username, HttpStatus.OK);
  }

  @GetMapping("/getSave")
  public ResponseEntity<String> getSave(@RequestParam String username, @RequestParam String type) {
    return new ResponseEntity<>("Ceci est la sauvegarde " + type + " de " + username, HttpStatus.OK);
  }

  @PostMapping("/save")
  ResponseEntity save(@RequestParam String username, @RequestParam String type, @RequestParam String save) {
    return new ResponseEntity<>("enregistrement de la sauvegarde " + save + "de type :" + type + " pour " + username, HttpStatus.CREATED);
  }
}
