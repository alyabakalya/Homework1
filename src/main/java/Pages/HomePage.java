package Pages;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.Keys;

import com.codeborne.selenide.SelenideElement;

public class HomePage extends BasePage {
	SelenideElement searchInputField = $(byXpath("//input[@name='searchTerm']"));
	SelenideElement searchButton = $(byXpath("//button[@aria-label='Search']"));

	public void enterSearchValue(String text) {
		actions()
				.moveToElement(searchInputField)
				.click()
				.keyDown(searchInputField, Keys.SHIFT)
				.sendKeys(text)
				.keyUp(searchInputField, Keys.SHIFT)
				.moveToElement(searchButton)
				.click()
				.build()
				.perform();
	}
}
