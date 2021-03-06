package com.api.petstore.application.test.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/features/", glue = "com.api.petstore.application.test.steps", tags = "@Smoke")
public class Runner {
}
