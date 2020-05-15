package main;

import cucumber.api.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "Demo/stepdefs",
        plugin = {
                "pretty", "json:reports/demo1/cucumber-html-reports/Cucumber.json",
                "junit:reports/demo1/cucumber-html-reports/Cucumber.xml",
                "html:reports/demo1/cucumber-html-reports"
        },
        tags = {"@Demo_with_issue"})
public class RunDemo_I extends CommonRunner {

}
