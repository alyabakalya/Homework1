package Pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import CucumberDriver.DriverManager;
import util.WebDriverWaiter;


public class BasePage extends WebDriverWaiter {
	public BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public BasePage clickElement(WebElement element) {
		element.click();
		return this;
	}

	public BasePage sendValueToInputField(WebElement element, String value) {
		element.sendKeys(value);
		return this;
	}

	public void selectValueInDropDown(Select dropDown, String value) {
		dropDown.selectByVisibleText(value);
	}

	public Select getNewSelect(WebElement element) {
		return new Select(element);
	}

	public String getPageURL() {
		return DriverManager.getDriver().getCurrentUrl();
	}

	public String getTextValue(WebElement text) {
		return text.getText();
	}

	private static Map<String, String> expectedUrlList;

	static {
		expectedUrlList = new HashMap<>();
		expectedUrlList.put("Search page", "https://www.bookdepository.com/search?searchTerm=THINKING+IN+JAVA&search=Find+book");
		expectedUrlList.put("Basket page", "https://www.bookdepository.com/basket");
		expectedUrlList.put("Checkout page", "https://www.bookdepository.com/payment/guest");
	}

	public static String getExpectedUrlValue(String pageName) {
		return expectedUrlList.get(pageName);
	}

	public void switchToFrame(WebElement frameName) {
		DriverManager.getDriver().switchTo().frame(frameName);
	}

	public void exitFrame() {
		DriverManager.getDriver().switchTo().defaultContent();
	}
}
