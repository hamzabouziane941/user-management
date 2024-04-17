package com.hb.organizationsubscription.adapters.driven.keycloak.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
public class KeycloakConfig {

  private String baseUrl;
  private String clientId;
  private String clientSecret;
}
