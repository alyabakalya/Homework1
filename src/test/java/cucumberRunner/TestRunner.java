package cucumberRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-reports"},
		monochrome = true,
		tags = "@Regression",
		glue = "cucumberStepsDefinitions",
		features = "src/test/resources/CucumberFeatures"
)
public class TestRunner {
}
