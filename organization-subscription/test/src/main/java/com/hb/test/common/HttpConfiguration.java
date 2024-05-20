package com.hb.test.common;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app-config")
@Getter
@Setter
public class HttpConfiguration {

  private static Map<String, String> staticUrl;

  private Map<String, String> url;

  @PostConstruct
  private void init() {
    url = url.entrySet().stream().map(this::removeConcatenatedNumbersInKeys)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    staticUrl = url;
  }

  public static String url(String apiUrlKey) {
    return staticUrl.get(apiUrlKey);
  }

  public static Map<String, String> urls() {
    return staticUrl;
  }

  public String getUrl(String apiUrlKey) {
    return url.get(apiUrlKey);
  }

  @Bean(
      destroyMethod = "close"
  )
  public CloseableHttpClient httpClient() {
    return HttpClients.createDefault();
  }

  private Entry<String, String> removeConcatenatedNumbersInKeys(Entry<String, String> entry) {
    String key = entry.getKey();
    int concatenatedNumberSeparatorIndex = key.indexOf('.');
    String updatedKey = key.substring(concatenatedNumberSeparatorIndex + 1);
    String value = entry.getValue();
    return Map.entry(updatedKey, value);
  }

}
