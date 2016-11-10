package aha.oretama.jp.service;

import aha.oretama.jp.KoboldWrapper;
import aha.oretama.jp.configuration.properties.ApplicationProperties;
import aha.oretama.jp.model.Kobold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DiffService {

  @Autowired
  private ApplicationProperties properties;

  public Kobold createDiff(String path)  {
    ApplicationProperties.KoboldProperty koboldProperty = properties.getKoboldProperty();

    try {
      return new KoboldWrapper()
          .build(koboldProperty.getBuildPath())
          .approved(koboldProperty.getApprovedPath())
          .highlight(koboldProperty.getHighlightPath())
          .failOrphans().failAdditios().run(path);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot create image-diff.",e);
    }
  }
}
