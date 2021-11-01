package Pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import JavaScriptExecutor.JSExecutor;


public class BasketPage extends BasePage {
	SelenideElement deliveryCost = $(byXpath("//dl[@class='delivery-text']/dd"));
	SelenideElement orderTotal = $(byXpath("//dl[@class='total']/dd"));
	SelenideElement checkoutButton = $(byXpath("//a[@class='checkout-btn btn optimizely-variation-1 original-bucket']"));

	public void clickOnCheckoutButton() {
		JSExecutor.scrollUntilElementIsVisible(checkoutButton);
		JSExecutor.executeClickOnElementJavaScript(checkoutButton);
	}

	public void assertTextOfDeliveryCostValue(String expectedText) {
		assertThatElementContainsExpectedText(deliveryCost, expectedText);
	}

	public void assertTextOfOrderCostValue(String expectedText) {
		assertThatElementContainsExpectedText(orderTotal, expectedText);
	}
}
