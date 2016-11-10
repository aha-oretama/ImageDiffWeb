package aha.oretama.jp.controller;

import aha.oretama.jp.configuration.properties.ApplicationProperties;
import aha.oretama.jp.model.Kobold;
import aha.oretama.jp.service.DiffService;
import aha.oretama.jp.utils.KoboldUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {

  @Autowired
  private DiffService diffService;

  @Autowired
  private ApplicationProperties properties;

  @GetMapping(value = "/")
  public String index(@RequestParam(required = false) String path, Model model) {
    ApplicationProperties.KoboldProperty koboldProperty = properties.getKoboldProperty();

    Kobold kobold = KoboldUtils
        .getKoboldWithPath(diffService.createDiff(path), path, koboldProperty.getBuildPath(),
            koboldProperty.getApprovedPath(), koboldProperty.getHighlightPath());
    model.addAttribute("kobold", kobold);

    return "index";
  }
}
