package JavaScriptExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import CucumberDriver.DriverManager;


public class JSExecutor {
	private static JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();

	public static void executeHighlightingJavaScript(WebElement element) {
		jsExecutor.executeScript("arguments[0].style.background='yellow'", element);
	}

	public static void executeClickOnElementJavaScript(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public static String getPageURLJavaScript() {
		return jsExecutor.executeScript("return document.URL;").toString();
	}

	public static void scrollUntilElementIsVisible(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}
}
