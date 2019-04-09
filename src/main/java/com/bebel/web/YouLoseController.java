package com.bebel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/youlose")
public class YouLoseController {

  @GetMapping("/getSaves")
  String getSave(@RequestParam String username) {
    return "Voici les sauvegarde de " + username;
  }

  @GetMapping("/getSave")
  String getSave(@RequestParam String username, @RequestParam String type) {
    return "Ceci est la sauvegarde " + type + " de " + username;
  }

  @PostMapping("/save")
  String save(@RequestParam String username, @RequestParam String type, @RequestParam String save) {
    return "enregistrement de la sauvegarde " + save + "de type :" + type + " pour " + username;
  }
}
