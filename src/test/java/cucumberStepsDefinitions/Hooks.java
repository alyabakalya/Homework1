package cucumberStepsDefinitions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import CucumberDriver.DriverManager;
import io.cucumber.java.*;

public class Hooks extends DriverManager {

	@Before
	public void executeBeforeScenario() {
		DriverManager.setUpBrowserConfigurations();
	}

	@After
	public void executeAfterScenario() {
		WebDriverRunner.closeWindow();
	}
}
