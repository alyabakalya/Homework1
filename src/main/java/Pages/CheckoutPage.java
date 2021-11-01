package Pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;


public class CheckoutPage extends BasePage {
	SelenideElement buyNowButton = $(byXpath("//button[@class='btn btn-primary full-width']"));
	SelenideElement errorMessagesAtPaymentForm = $(byXpath("//div[@class='buynow-error-msg']"));
	SelenideElement errorMessagesAtDeliveryAddressForm = $(
			byXpath("//div[@class='block-wrap delivery-address']//div[@class='error-block']"));
	SelenideElement inputFieldEmailAddress = $(byXpath("//input[@name='emailAddress']"));
	SelenideElement subtotal = $(byXpath("//strong[text() = 'Sub-total']//ancestor::dt//following-sibling::dd"));
	SelenideElement delivery = $(byXpath("//strong[text() = 'Delivery']//ancestor::dt//following-sibling::dd//strong"));
	SelenideElement orderTotal = $(byXpath("//dd[@class='text-right total-price']"));
	SelenideElement tax = $(byXpath("//dd[@class='text-right total-tax']"));
	SelenideElement form = $(byXpath("//div[@class='block-wrap delivery-address']"));
	SelenideElement cardNumberFrame = $(byXpath("//iframe[@id='braintree-hosted-field-number']"));
	SelenideElement cardNumber = $(byXpath("//input[@id='credit-card-number']"));
	SelenideElement expiryDateFrame = $(byXpath("//iframe[@id='braintree-hosted-field-expirationDate']"));
	SelenideElement expiryDate = $(byXpath("//input[@id='expiration']"));
	SelenideElement cvvFrame = $(byXpath("//iframe[@id='braintree-hosted-field-cvv']"));
	SelenideElement cvv = $(byXpath("//input[@id='cvv']"));

	String inputFieldAddressTemplate = ".//label[text()='%s']";
	String errorMessageLocator = ".//following-sibling::div[@class='form-control-wrap']/div[@class='error-block']";

	public void clickOnBuyNowButton() {
		clickElement(buyNowButton);
	}

	public void assertTextOfSubtotalValue(String expectedText) {
		assertThatElementContainsExpectedText(subtotal, expectedText);
	}

	public void assertTextOfDeliveryValue(String expectedText) {
		assertThatElementContainsExpectedText(delivery, expectedText);
	}

	public void assertTextOfTaxValue(String expectedText) {
		assertThatElementContainsExpectedText(tax, expectedText);
	}

	public void assertTextOfOrderTotalValue(String expectedText) {
		assertThatElementContainsExpectedText(orderTotal, expectedText);
	}

	public void assertDeliveryAddressErrorMessageText(String fieldName, String expectedText) {
		SelenideElement error = form.$(byXpath(String.format(inputFieldAddressTemplate, fieldName))).find(
				byXpath(errorMessageLocator));
		assertThatElementContainsExpectedText(error, expectedText);
	}

	public void enterEmailAddress(String emailAddress) {
		sendValueToInputField(inputFieldEmailAddress, emailAddress);
	}

	public void assertPaymentFormErrorMessageText(String expectedText) {
		assertThatElementContainsExpectedText(errorMessagesAtPaymentForm, expectedText);
	}

	public void enterCreditCardNumber(String creditCardNumber) {
		switchToFrame(cardNumberFrame);
		sendValueToInputField(cardNumber, creditCardNumber);
		exitFrame();
	}

	public void enterExpiryDate(String expiryDateValue) {
		switchToFrame(expiryDateFrame);
		sendValueToInputField(expiryDate, expiryDateValue);
		exitFrame();
	}

	public void enterCVV(String cvvValue) {
		switchToFrame(cvvFrame);
		sendValueToInputField(cvv, cvvValue);
		exitFrame();
	}

	public void assertThatErrorMessagesAreNotDisplayed() {
		errorMessagesAtDeliveryAddressForm.shouldNotBe(visible);
	}
}
