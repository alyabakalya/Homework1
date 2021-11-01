package CucumberDriver;

import com.codeborne.selenide.Configuration;


public class DriverManager {
	public static void setUpBrowserConfigurations() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		Configuration.browser = "chrome";
		Configuration.browserSize = "1920x1080";
		Configuration.screenshots = true;
		Configuration.reportsFolder = "src/main/resources/reports";
	}
}

