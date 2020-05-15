package main;

import cucumber.api.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "Demo/stepdefs",
        plugin = {
                "pretty", "json:reports/demo/cucumber-html-reports/Cucumber.json",
                "junit:reports/demo/cucumber-html-reports/Cucumber.xml",
                "html:reports/demo/cucumber-html-reports"
        },
        tags = {"@Demo"})
public class RunDemo extends CommonRunner {

}
