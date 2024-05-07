package com.hb.test.cucumber.http.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app-config")
@Getter
@Setter
public class UrlProperties {

  private Map<String, String> url;
}
