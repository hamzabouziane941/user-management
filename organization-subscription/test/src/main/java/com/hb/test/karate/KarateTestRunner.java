package com.hb.test.karate;

import com.intuit.karate.junit5.Karate;

public class KarateTestRunner {

  @Karate.Test
  Karate testAll() {
    return Karate.run("src/main/resources/karate");
  }
}
