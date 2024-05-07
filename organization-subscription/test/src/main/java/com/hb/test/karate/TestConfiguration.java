package com.hb.test.karate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestConfiguration {

  public static ConfigurableApplicationContext applicationContext;

  public static KarateContext karateContext() {
    return applicationContext.getBean(KarateContext.class);
  }

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    TestConfiguration.applicationContext = (ConfigurableApplicationContext) applicationContext;
  }
}
