package Pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;



public class BasketPopUp extends BasePage {
	SelenideElement basketCheckoutButton = $(byXpath("//a[@data-default-localized-pattern='Basket / Checkout']"));

	public void clickOnBasketCheckoutButton() {
		waitUntilElementIsDisplayed(basketCheckoutButton, 10);
		clickElement(basketCheckoutButton);
	}

}
