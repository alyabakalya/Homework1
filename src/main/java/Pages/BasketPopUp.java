package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPopUp extends BasePage {
	@FindBy(xpath = "//a[@data-default-localized-pattern='Basket / Checkout']")
	WebElement basketCheckoutButton;

	public void clickOnBasketCheckoutButton() {
		waitForElement(basketCheckoutButton, 10);
		clickElement(basketCheckoutButton);
	}
}
