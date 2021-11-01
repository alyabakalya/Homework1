package cucumberStepsDefinitions;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.codeborne.selenide.Selenide.*;
import java.util.*;

import com.codeborne.selenide.*;

import DTO.UserDTO;
import Pages.*;
import io.cucumber.java.en.*;

public class DesktopCheckoutForGuestUserStepDefinitions {
	private BasePage basePage = new BasePage();
	private HomePage homePage = new HomePage();
	private SearchResultsPage searchResultsPage = new SearchResultsPage();
	private BasketPopUp basketPopUp = new BasketPopUp();
	private BasketPage basketPage = new BasketPage();
	private CheckoutPage checkoutPage = new CheckoutPage();
	private PaymentForm paymentForm = new PaymentForm();

	@Given("I am an anonymous customer with clear cookies")
	public void clearCookies() {
		Selenide.clearBrowserCookies();
	}

	@When("I open the Initial home page")
	public void openInitialPage() {
		PageNavigation.openPage("https://www.bookdepository.com/");
	}

	@And("^I search for ([\\w\\s]+)$")
	public void searchForProduct(String searchTerm) {
		homePage.enterSearchValue(searchTerm);
	}

	@And("^I am redirected to a ([\\w\\s]+)$")
	public void verifyUserIsRedirectedToSearchPageWithCorrectURL(String pageName) {
		basePage.assertCorrectPageURL(pageName);
	}

	@And("^Search results contain the following products$")
	public void verifyProductsInSearchResults(List<String> bookList) {
		for (String bookName : bookList) {
			$$(byXpath("//div[@class='tab search']//*[contains(text(),'" + bookName + "')]"))
					.shouldHave(sizeGreaterThan(0));
		}
	}

	@And("^I apply the following search filters$")
	public void applySearchFiltersAndClickOnRefineResultsButton(Map<String, String> filterNameAndValue) {
		for (Map.Entry<String, String> map : filterNameAndValue.entrySet()) {
			SelenideElement dropDown = $(byXpath(searchResultsPage.formatLocatorForYourSearchDropDown(map.getKey())));
			basePage.selectValueInDropDown(dropDown, map.getValue());
		}
		searchResultsPage.clickOnRefineResultsButton();
	}

	@Then("^Search results contain only the following products$")
	public void verifySearchResultsContainProducts(List<String> expectedBookList) {
		for (String bookName : expectedBookList) {
			$$(byXpath("//div[@class='tab search']//*[contains(text(),'" + bookName + "')]"))
					.shouldHave(sizeGreaterThan(0));
		}
		ElementsCollection booksOnPage = searchResultsPage.getAllBooksOnSearchPage();
		booksOnPage.shouldBe(sizeLessThanOrEqual(expectedBookList.size()));
	}

	@When("I click \"Add to basket\" button for product with name \"Thinking in Java\"")
	public void clickOnAddToBasketButtonForTestProduct() {
		searchResultsPage.clickOnAddToBasketButton();
	}

	@And("I select \"Basket \\ Checkout\" in basket pop-up")
	public void selectBasketCheckoutInBasketPopUp() {
		basketPopUp.clickOnBasketCheckoutButton();
	}

	@Then("^I am redirected to the ([\\w\\s]+)$")
	public void verifyUserIsRedirectedToBasketPageWithCorrectURL(String pageName) {
		basePage.assertCorrectPageURL(pageName);
	}

	@And("^Basket order summary is as following:$")
	public void verifyBasketOrderSummary(List<Map<String, String>> orderSummary) {
		assertSoftly(softAssertions -> {
			basketPage.assertTextOfDeliveryCostValue(orderSummary.get(0).get("Delivery cost"));
			basketPage.assertTextOfOrderCostValue(orderSummary.get(0).get("Total"));
		});
	}

	@When("I click \"Checkout\" button on Basket page")
	public void clickOnCheckoutButtonOnBasketPage() {
		basketPage.clickOnCheckoutButton();
	}

	@Then("^I am transferred to ([\\w\\s]+)$")
	public void verifyUserIsTransferredToCheckoutPage(String pageName) {
		basePage.assertCorrectPageURL(pageName);
	}

	@When("I click \"Buy now\" button")
	public void clickOnBuyNowButton() {
		checkoutPage.clickOnBuyNowButton();
	}

	@Then("^The following validation error messages are displayed on \"Delivery Address\" form:$")
	public void verifyErrorMessagesOnDeliveryAddressForm(List<Map<String, String>> listOfInputFieldsAndErrorMessages) {
		for (Map<String, String> map : listOfInputFieldsAndErrorMessages) {
			assertSoftly(softAssertions -> {
				checkoutPage.assertDeliveryAddressErrorMessageText(map.get("Form field name"), map.get("validation error message"));
			});
		}
	}

	@And("^The following validation error messages are displayed on 'Payment' form:$")
	public void verifyValidationErrorMessagesOnPaymentForm(List<String> listOfErrorMessages) {
		for (String errorMessage : listOfErrorMessages) {
			checkoutPage.assertPaymentFormErrorMessageText(errorMessage);
		}
	}

	@And("^Checkout order summary is as following:$")
	public void verifyCheckoutOrderSummary(List<Map<String, String>> orderSummary) {
		assertSoftly(softAssertions -> {
			checkoutPage.assertTextOfSubtotalValue(orderSummary.get(0).get("Sub-total"));
			checkoutPage.assertTextOfDeliveryValue(orderSummary.get(0).get("Delivery"));
			checkoutPage.assertTextOfTaxValue(orderSummary.get(0).get("VAT"));
			checkoutPage.assertTextOfOrderTotalValue(orderSummary.get(0).get("Total"));
		});
	}

	@And("^I checkout as a new customer with email ([a-zA-Z1-9_.]+@[a-z]+\\.[a-z]{3,5})$")
	public void checkoutAsANewCustomerWithEmail(String emailAddress) {
		checkoutPage.enterEmailAddress(emailAddress);
	}

	@When("^I fill delivery address information manually:$")
	public void fillDeliveryAddressInfo(List<Map<String, String>> userList) {

		for (Map<String, String> map : userList) {
			UserDTO user = UserDTO.transform(map);
			paymentForm.fillAddressForm(user);
		}
	}

	@Then("There are no validation error messages displayed on \"Delivery Address\" form")
	public void verifyAbsenceOfErrorMessage() {
		checkoutPage.assertThatErrorMessagesAreNotDisplayed();
	}

	@When("^I enter my card details$")
	public void enterCardDetails(Map<String, String> cardDetails) {
		for (Map.Entry<String, String> map : cardDetails.entrySet()) {
			switch (map.getKey()) {
				case "Card number":
					checkoutPage.enterCreditCardNumber(map.getValue());
					break;
				case "Expiry date":
					checkoutPage.enterExpiryDate(map.getValue());
					break;
				case "CVV":
					checkoutPage.enterCVV(map.getValue());
					break;
			}
		}
	}
}

