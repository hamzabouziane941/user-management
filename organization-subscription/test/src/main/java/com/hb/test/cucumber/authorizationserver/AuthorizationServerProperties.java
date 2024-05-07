package com.hb.test.cucumber.authorizationserver;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app-config.authorization-server")
@Getter
@Setter
public class AuthorizationServerProperties {

  private AuthorizationServerType type;
  private String baseUrl;
  private String clientId;
  private String clientSecret;

}
