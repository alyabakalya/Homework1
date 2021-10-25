package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import CucumberDriver.DriverManager;

public class HomePage extends BasePage {
	@FindBy(xpath = "//input[@name='searchTerm']")
	WebElement searchInputField;

	@FindBy(xpath = "//button[@aria-label='Search']")
	WebElement searchButton;

	public void enterSearchValue(String text) {
		Actions builder = new Actions(DriverManager.getDriver());
		Action seriesOfActions = builder
				.moveToElement(searchInputField)
				.click()
				.keyDown(searchInputField, Keys.SHIFT)
				.sendKeys(text)
				.keyUp(searchInputField, Keys.SHIFT)
				.moveToElement(searchButton)
				.click()
				.build();
		seriesOfActions.perform();
	}
}
