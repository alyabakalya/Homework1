package cucumberRunner;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import com.codeborne.selenide.junit5.ScreenShooterExtension;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@ExtendWith({ ScreenShooterExtension.class})
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-reports"},
		monochrome = true,
		tags = "@Regression",
		glue = "cucumberStepsDefinitions",
		features = "src/test/resources/CucumberFeatures"
)
public class TestRunner {
}
