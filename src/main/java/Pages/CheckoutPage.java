package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class CheckoutPage extends BasePage {
	@FindBy(xpath = "//button[@class='btn btn-primary full-width']")
	WebElement buyNowButton;

	@FindBy(xpath = "//div[@class='buynow-error-msg']")
	WebElement errorMessagesAtPaymentForm;

	@FindBy(xpath = "//div[@class='block-wrap delivery-address']//div[@class='error-block']")
	WebElement errorMessagesAtDeliveryAddressForm;

	@FindBy(xpath = "//input[@name='emailAddress']")
	WebElement inputFieldEmailAddress;

	@FindBy(xpath = "//strong[text() = 'Sub-total']//ancestor::dt//following-sibling::dd")
	WebElement subtotal;

	@FindBy(xpath = "//strong[text() = 'Delivery']//ancestor::dt//following-sibling::dd//strong")
	WebElement delivery;

	@FindBy(xpath = "//dd[@class='text-right total-price']")
	WebElement orderTotal;

	@FindBy(xpath = "//dd[@class='text-right total-tax']")
	WebElement tax;

	@FindBy(xpath = "//div[@class='block-wrap delivery-address']")
	WebElement form;

	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-number']")
	WebElement cardNumberFrame;

	@FindBy(xpath = "//input[@id='credit-card-number']")
	WebElement cardNumber;

	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-expirationDate']")
	WebElement expiryDateFrame;

	@FindBy(xpath = "//input[@id='expiration']")
	WebElement expiryDate;

	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-cvv']")
	WebElement cvvFrame;

	@FindBy(xpath = "//input[@id='cvv']")
	WebElement cvv;

	String inputFieldAddressTemplate = ".//label[text()='%s']";

	String errorMessageLocator = ".//following-sibling::div[@class='form-control-wrap']/div[@class='error-block']";

	public void clickOnBuyNowButton() {
		clickElement(buyNowButton);
	}

	public String getSubtotalTextValue() {
		return getTextValue(subtotal);
	}

	public String getDeliveryTextValue() {
		return getTextValue(delivery);
	}

	public String getTaxTextValue() {
		return getTextValue(tax);
	}

	public String getOrderTotalTextValue() {
		return getTextValue(orderTotal);
	}

	public String getErrorMessageText(String fieldName) {
		WebElement inputField = form.findElement(By.xpath(String.format(inputFieldAddressTemplate, fieldName)));
		WebElement errorText = inputField.findElement(By.xpath(errorMessageLocator));
		return errorText.getText();
	}

	public void enterEmailAddress(String emailAddress) {
		sendValueToInputField(inputFieldEmailAddress, emailAddress);
	}

	public String getTextOfErrorMessagesAtPaymentForm() {
		return getTextValue(errorMessagesAtPaymentForm);
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

	public Boolean areErrorMessagesDisplayed() {
		return errorMessagesAtDeliveryAddressForm.isDisplayed();
	}

}
