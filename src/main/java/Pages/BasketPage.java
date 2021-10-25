package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import JavaScriptExecutor.JSExecutor;

public class BasketPage extends BasePage {
	@FindBy(xpath = "//dl[@class='delivery-text']/dd")
	WebElement deliveryCost;

	@FindBy(xpath = "//dl[@class='total']/dd")
	WebElement orderTotal;

	@FindBy(xpath = "//a[@class='checkout-btn btn optimizely-variation-1 original-bucket']")
	WebElement checkoutButton;

	public String getDeliveryCostTextValue() {
		JSExecutor.executeHighlightingJavaScript(deliveryCost);
		return getTextValue(deliveryCost);
	}

	public String getOrderTotalTextValue() {
		JSExecutor.executeHighlightingJavaScript(orderTotal);
		return getTextValue(orderTotal);
	}

	public void clickOnCheckoutButton() {
		JSExecutor.scrollUntilElementIsVisible(checkoutButton);
		JSExecutor.executeClickOnElementJavaScript(checkoutButton);
	}
}
