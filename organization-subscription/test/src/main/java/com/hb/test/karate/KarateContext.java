package com.hb.test.karate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hb.test.karate.config.ApplicationConfig;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

public class KarateContext {

  private static KarateContext INSTANCE;

  private final Map<String, String> urls = new HashMap<>();

  private KarateContext() {
  }

  public static KarateContext getInstance() throws IOException, JSONException {
    if (INSTANCE == null) {
      KarateContext karateContext = new KarateContext();
      ApplicationConfig applicationConfig = new ObjectMapper(new YAMLFactory())
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .readerFor(
              ApplicationConfig.class)
          .at("/app-config").readValue(new File("src/main/resources/application.yml"));
      INSTANCE = karateContext;
      for (Map<String, String> url : applicationConfig.getUrl()) {
        INSTANCE.urls.putAll(url);
      }
    }
    return INSTANCE;
  }

  public String url(String key) {
    return urls.get(key);
  }
}
