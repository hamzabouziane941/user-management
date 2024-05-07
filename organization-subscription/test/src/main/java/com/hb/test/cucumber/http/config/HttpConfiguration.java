package com.hb.test.cucumber.http.config;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class HttpConfiguration {


  private final UrlProperties appConfig;


  @Bean
  public Map<String, String> urls() {
    return appConfig.getUrl();
  }

  @Bean(
      destroyMethod = "close"
  )
  public CloseableHttpClient httpClient() {
    return HttpClients.createDefault();
  }

}
