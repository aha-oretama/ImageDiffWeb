package aha.oretama.jp.model;

import lombok.Value;

import java.net.URI;

@Value
public class ImageUrl {

  private TYPE type;
  private URI approvedUrl;
  private Authentication approvedUrlAuth;
  private URI buildUrl;
  private Authentication buildUrlAuth;

  public enum TYPE {
    ZIP
  }

}
