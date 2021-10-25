package cucumberStepsDefinitions;

import CucumberDriver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks extends DriverManager {

	@Before
	public void executeBeforeScenario() {
		DriverManager.setUpDriver();
	}

	@After
	public void executeAfterScenario() {
		DriverManager.quitDriver();
	}

}