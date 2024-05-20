package com.hb.test.karate.config;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ApplicationConfig {

  private List<Map<String, String>> url;
}
