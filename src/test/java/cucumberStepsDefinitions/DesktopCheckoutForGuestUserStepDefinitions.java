package cucumberStepsDefinitions;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import CucumberDriver.DriverManager;
import DTO.UserDTO;
import JavaScriptExecutor.JSExecutor;
import Pages.*;
import io.cucumber.datatable.DataTable;
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
		DriverManager.getDriver().manage().deleteAllCookies();
	}

	@When("I open the Initial home page")
	public void openInitialPage() {
		PageNavigation.openPage(DriverManager.getDriver(), "https://www.bookdepository.com/");
	}

	@And("^I search for ([\\w\\s]+)$")
	public void searchForProduct(String searchTerm) {
		homePage.enterSearchValue(searchTerm);
	}

	@And("^I am redirected to a ([\\w\\s]+)$")
	public void verifyUserIsRedirectedToSearchPageWithCorrectURL(String pageName) {
		Assert.assertEquals("Search page URL is incorrect", BasePage.getExpectedUrlValue(pageName),
				JSExecutor.getPageURLJavaScript());
	}

	@And("^Search results contain the following products$")
	public void verifyProductsInSearchResults(List<String> bookList) {
		for (String bookName : bookList) {
			List<WebElement> books = DriverManager.getDriver().findElements(
					By.xpath("//div[@class='tab search']//*[contains(text(),'" + bookName + "')]"));
			assertSoftly(softAssertions -> {
				softAssertions.assertThat(books).isNotEmpty();
			});
		}
	}

	@And("^I apply the following search filters$")
	public void applySearchFiltersAndClickOnRefineResultsButton(Map<String, String> filterNameAndValue) {
		for (Map.Entry<String, String> map : filterNameAndValue.entrySet()) {
			Select dropDown = new Select(DriverManager.getDriver().findElement(
					By.xpath(searchResultsPage.formatLocatorForYourSearchDropDown(map.getKey()))));

			basePage.selectValueInDropDown(dropDown, map.getValue());
		}
		searchResultsPage.clickOnRefineResultsButton();
	}

	@Then("^Search results contain only the following products$")
	public void verifySearchResultsContainProducts(List<String> expectedBookList) {
		for (String bookName : expectedBookList) {
			List<WebElement> books = DriverManager.getDriver().findElements(
					By.xpath("//div[@class='tab search']//*[contains(text(),'" + bookName + "')]"));
			assertSoftly(softAssertions -> {
				softAssertions.assertThat(books).isNotEmpty();
			});
		}
		List<WebElement> booksOnPage = searchResultsPage.getAllBooksOnSearchPage();
		Assert.assertEquals(expectedBookList.size(), booksOnPage.size());
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
		Assert.assertEquals("Basket page URL is incorrect", BasePage.getExpectedUrlValue(pageName), basePage.getPageURL());
	}

	@And("^Basket order summary is as following:$")
	public void verifyBasketOrderSummary(List<Map<String, String>> orderSummary) {
		assertSoftly(softAssertions -> {
			softAssertions.assertThat(basketPage.getDeliveryCostTextValue()).as("Delivery cost is incorrect").isEqualTo(
					orderSummary.get(0).get("Delivery cost"));
			softAssertions.assertThat(basketPage.getOrderTotalTextValue()).as("Order total is incorrect").isEqualTo(
					orderSummary.get(0).get("Total"));
		});
	}

	@When("I click \"Checkout\" button on Basket page")
	public void clickOnCheckoutButtonOnBasketPage() {
		basketPage.clickOnCheckoutButton();
	}

	@Then("^I am transferred to ([\\w\\s]+)$")
	public void verifyUserIsTransferredToCheckoutPage(String pageName) {
		Assert.assertEquals("Checkout page URL is incorrect", BasePage.getExpectedUrlValue(pageName), basePage.getPageURL());
	}

	@When("I click \"Buy now\" button")
	public void clickOnBuyNowButton() {
		checkoutPage.clickOnBuyNowButton();
	}

	@Then("^The following validation error messages are displayed on \"Delivery Address\" form:$")
	public void verifyErrorMessagesOnDeliveryAddressForm(List<Map<String, String>> listOfInputFieldsAndErrorMessages) {
		for (Map<String, String> map : listOfInputFieldsAndErrorMessages) {
			assertSoftly(softAssertions -> {
				softAssertions.assertThat(checkoutPage.getErrorMessageText(map.get("Form field name"))).as(
						"Validation error message is incorrect at Address form").isEqualTo(
						map.get("validation error message"));
			});
		}
	}

	@And("^The following validation error messages are displayed on 'Payment' form:$")
	public void verifyValidationErrorMessagesOnPaymentForm(List<String> listOfErrorMessages) {
		for (String errorMessage : listOfErrorMessages) {
			Assert.assertEquals("Validation error message is incorrect at Payment form", errorMessage,
					checkoutPage.getTextOfErrorMessagesAtPaymentForm().replaceAll("[\\t\\n\\r]", " "));
		}
	}

	@And("^Checkout order summary is as following:$")
	public void verifyCheckoutOrderSummary(List<Map<String, String>> orderSummary) {
		assertSoftly(softAssertions -> {
			softAssertions.assertThat(checkoutPage.getSubtotalTextValue()).as("Subtotal is incorrect").isEqualTo(
					orderSummary.get(0).get("Sub-total"));
			softAssertions.assertThat(checkoutPage.getDeliveryTextValue()).as("Delivery is incorrect").isEqualTo(
					orderSummary.get(0).get("Delivery"));
			softAssertions.assertThat(checkoutPage.getTaxTextValue()).as("Tax is incorrect").isEqualTo(
					orderSummary.get(0).get("VAT"));
			softAssertions.assertThat(checkoutPage.getOrderTotalTextValue()).as("Order total is incorrect").isEqualTo(
					orderSummary.get(0).get("Total"));
		});
	}

	@And("^I checkout as a new customer with email ([a-zA-Z1-9_.]+@[a-z]+\\.[a-z]{3,5})$")
	public void checkoutAsANewCustomerWithEmail(String emailAddress) {
		checkoutPage.enterEmailAddress(emailAddress);
	}

	@When("^I fill delivery address information manually:$")
	public void fillDeliveryAddressInfo(DataTable table) {
		List<Map<String, String>> userList = table.asMaps(String.class, String.class);
		for (Map<String, String> map : userList) {
			UserDTO user = UserDTO.transform(map);
			paymentForm.fillAddressForm(user);
		}
	}

	@Then("There are no validation error messages displayed on \"Delivery Address\" form")
	public void verifyAbsenceOfErrorMessage() {
		Assert.assertFalse(checkoutPage.areErrorMessagesDisplayed());
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