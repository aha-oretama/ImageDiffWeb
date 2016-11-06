package aha.oretama.jp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {

  @GetMapping(value = "index")
  public String index(@RequestParam String path, Model model) {
    return "index";
  }

}
