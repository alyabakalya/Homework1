package JavaScriptExecutor;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import com.codeborne.selenide.SelenideElement;

public class JSExecutor {

	public static void executeClickOnElementJavaScript(SelenideElement element) {
		executeJavaScript("arguments[0].click();", element);
	}

	public static void scrollUntilElementIsVisible(SelenideElement element) {
		executeJavaScript("arguments[0].scrollIntoView();", element);
	}
}
