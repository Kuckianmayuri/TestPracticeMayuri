package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//TestNG is used to running the cucumber framword script
@CucumberOptions(features="src/test/java/Cucumber",glue="StepDefinition",monochrome=true,tags ="@ErrorValidation", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
