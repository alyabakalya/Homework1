package Pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

import util.SelenideWaiter;


public class BasePage extends SelenideWaiter {

	public BasePage clickElement(SelenideElement element) {
		element.click();
		return this;
	}

	public BasePage sendValueToInputField(SelenideElement element, String value) {
		element.setValue(value);
		return this;
	}

	public void selectValueInDropDown(SelenideElement dropDown, String value) {
		dropDown.selectOptionContainingText(value);
	}

	private static Map<String, String> expectedUrlList;

	static {
		expectedUrlList = new HashMap<>();
		expectedUrlList.put("Search page",
				"https://www.bookdepository.com/search?searchTerm=THINKING+IN+JAVA&search=Find+book");
		expectedUrlList.put("Basket page", "https://www.bookdepository.com/basket");
		expectedUrlList.put("Checkout page", "https://www.bookdepository.com/payment/guest");
	}

	public static String getExpectedUrlValue(String pageName) {
		return expectedUrlList.get(pageName);
	}

	public void switchToFrame(WebElement frameName) {
		switchTo().frame(frameName);
	}

	public void exitFrame() {
		switchTo().defaultContent();
	}

	public void assertThatElementContainsExpectedText(SelenideElement element, String expectedText) {
		element.shouldHave(text(expectedText));
	}

	public void assertCorrectPageURL(String pageName) {
		webdriver().shouldHave(url(BasePage.getExpectedUrlValue(pageName)));
	}
}
