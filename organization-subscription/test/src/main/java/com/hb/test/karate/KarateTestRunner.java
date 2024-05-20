package com.hb.test.karate;

import com.hb.test.TestApplication;
import com.hb.test.common.HttpConfiguration;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestApplication.class,
    HttpConfiguration.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class KarateTestRunner {

  @Autowired
  private HttpConfiguration httpConfiguration;

  @Karate.Test
  Karate testAll() {
    Karate karate = Karate.run("src/main/resources/karate");
    HttpConfiguration.urls().forEach(karate::systemProperty);
    return karate;
  }
}
