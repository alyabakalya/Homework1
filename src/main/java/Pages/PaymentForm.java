package Pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import DTO.UserDTO;

public class PaymentForm extends CheckoutPage {

	SelenideElement fullNameInputField = $(byXpath("//input[@name='delivery-fullName']"));
	SelenideElement addressLine1InputField = $(byXpath("//input[@name='delivery-addressLine1']"));
	SelenideElement addressLine2InputField = $(byXpath("//input[@name='delivery-addressLine2']"));
	SelenideElement deliveryCityTownInputField = $(byXpath("//input[@name='delivery-city']"));
	SelenideElement deliveryCountyStateInputField = $(byXpath("//input[@name='delivery-county']"));
	SelenideElement deliveryPostCode = $(byXpath("//input[@name='delivery-postCode']"));
	SelenideElement deliveryCountryDropDown = $(By.name("deliveryCountry"));

	public void setFullName(String fullName) {
		sendValueToInputField(fullNameInputField, fullName);
	}

	public void setCountry(SelenideElement dropDown, String deliveryCountry) {
		dropDown.selectOptionContainingText(deliveryCountry);
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
		setCountry(deliveryCountryDropDown, addressInfo.deliveryCountry);
		setAddressLine1(addressInfo.addressLine1);
		setAddressLine2(addressInfo.addressLine2);
		setDeliveryCityTown(addressInfo.deliveryTownOrCity);
		setDeliveryCountyState(addressInfo.deliveryCountyOrState);
		setDeliveryPostCode(addressInfo.postCode);
		return this;
	}

}
