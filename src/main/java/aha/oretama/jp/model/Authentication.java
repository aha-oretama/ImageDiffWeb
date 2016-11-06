package aha.oretama.jp.model;

import lombok.Value;

@Value
public class Authentication {
  private String userId;
  private String password;
}
