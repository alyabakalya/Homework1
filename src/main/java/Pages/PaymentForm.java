package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import CucumberDriver.DriverManager;
import DTO.UserDTO;

public class PaymentForm extends CheckoutPage {

	@FindBy(xpath = "//input[@name='delivery-fullName']")
	WebElement fullNameInputField;

	@FindBy(xpath = "//input[@name='delivery-addressLine1']")
	WebElement addressLine1InputField;

	@FindBy(xpath = "//input[@name='delivery-addressLine2']")
	WebElement addressLine2InputField;

	@FindBy(xpath = "//input[@name='delivery-city']")
	WebElement deliveryCityTownInputField;

	@FindBy(xpath = "//input[@name='delivery-county']")
	WebElement deliveryCountyStateInputField;

	@FindBy(xpath = "//input[@name='delivery-postCode']")
	WebElement deliveryPostCode;

	@FindBy(name="deliveryCountry")
	WebElement deliveryCountryDropDown;

	public void setFullName(String fullName) {
		sendValueToInputField(fullNameInputField, fullName);
	}

	public void setCountry(Select dropDown, String deliveryCountry) {
		dropDown.selectByVisibleText(deliveryCountry);
		exitFrame();
	}

	public void setAddressLine1(String addressLine1) {
		sendValueToInputField(addressLine1InputField, addressLine1);
	}

	public void setAddressLine2(String addressLine2) {
		sendValueToInputField(addressLine2InputField, addressLine2);
	}

	public void setDeliveryCityTown(String cityOrTown) {
		sendValueToInputField(deliveryCityTownInputField, cityOrTown);
	}

	public void setDeliveryCountyState(String countyOrState) {
		sendValueToInputField(deliveryCountyStateInputField, countyOrState);
	}

	public void setDeliveryPostCode(String postCode) {
		sendValueToInputField(deliveryPostCode, postCode);
	}

	public PaymentForm fillAddressForm(UserDTO addressInfo) {
		setFullName(addressInfo.fullName);
		setCountry(getNewSelect(deliveryCountryDropDown), addressInfo.deliveryCountry);
		setAddressLine1(addressInfo.addressLine1);
		setAddressLine2(addressInfo.addressLine2);
		setDeliveryCityTown(addressInfo.deliveryTownOrCity);
		setDeliveryCountyState(addressInfo.deliveryCountyOrState);
		setDeliveryPostCode(addressInfo.postCode);
		return this;
	}

}
