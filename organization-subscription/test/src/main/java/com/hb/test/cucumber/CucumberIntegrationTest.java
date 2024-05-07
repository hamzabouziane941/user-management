package com.hb.test.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest
@CucumberOptions(plugin = {"pretty",
    "html:target/cucumber-report.html"}, features = "src/main/resources/cucumber")
public class CucumberIntegrationTest {

}
