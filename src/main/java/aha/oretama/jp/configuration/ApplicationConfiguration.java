package aha.oretama.jp.configuration;

import aha.oretama.jp.configuration.properties.ApplicationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "application")
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }
}
