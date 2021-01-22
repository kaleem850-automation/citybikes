package com.citybikes;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "classpath:features",
        glue = "com.citybikes.stepdefinitions",
        plugin = {"html:target/results.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
