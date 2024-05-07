package com.hb.test.karate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;


public class KarateContext {

  private static KarateContext INSTANCE;

  private Map<String, String> urls;

  private KarateContext() {
  }

  public static KarateContext getInstance() throws IOException, JSONException {
    if (INSTANCE == null) {
      KarateContext karateContext = new KarateContext();
      InputStream inputStream = new FileInputStream(("src/main/resources/application.yml"));
      Yaml yaml = new Yaml();
      Map<String, Object> applicationConfiguration = yaml.load(inputStream);
      JSONArray urlJsonArray = new JSONObject(applicationConfiguration).getJSONObject("app-config").getJSONArray("url")
      for(Object url : urlJsonArray) {
        JSONObject urlJson = (JSONObject) url;
        karateContext.urls.put(urlJson.getString("name"), urlJson.getString("value"));
      }
      INSTANCE = karateContext;
    }
    return INSTANCE;
  }

  public Map<String, String> urls() {
    return urls;
  }
}
