package aha.oretama.jp.configuration.properties;

import lombok.Data;

@Data
public class ApplicationProperties {
  private KoboldProperty koboldProperty = new KoboldProperty();

  @Data
  public class KoboldProperty{
    private String buildPath = "build";
    private String approvedPath = "approved";
    private String highlightPath = "highlight";
  }
}

