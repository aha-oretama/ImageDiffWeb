package aha.oretama.jp.controller;

import aha.oretama.jp.model.ImageUrl;
import aha.oretama.jp.model.Mapping;
import aha.oretama.jp.service.ImagesDownloadingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ImageRestController {

  @Autowired
  private ImagesDownloadingService imagesDownloadingService;

  @PostMapping(path = "/api/v1/images")
  public Mapping postImages(@RequestBody(required = false) ImageUrl imageUrl){

    if(!imageUrl.getType().equals(ImageUrl.TYPE.ZIP)){
      throw new IllegalArgumentException("Type must be zip.");
    }

    return imagesDownloadingService.downloadImagesFromZip(imageUrl);
  }

  private URI createUri(String path) {

    return URI.create("");
  }
}
