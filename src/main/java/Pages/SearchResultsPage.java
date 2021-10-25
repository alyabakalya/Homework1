package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage{
	@FindBy(xpath = "//button[contains(text(), 'Refine results')]")
	WebElement refineResultsButton;

	@FindBy(xpath = "//a[@data-isbn='9780131872486']")
	WebElement testProductThinkingInJava;

	@FindBy(xpath = "//div[@class='book-item']")
	List<WebElement> allBooksOnSearchPage;

	String filterYourSearchDropDown = "//label[text()='%s']//following-sibling::select";

	public void clickOnRefineResultsButton() {
		clickElement(refineResultsButton);
	}

	public void clickOnAddToBasketButton() {
		clickElement(testProductThinkingInJava);
	}

	public String formatLocatorForYourSearchDropDown(String filterName) {
		return String.format(filterYourSearchDropDown, filterName);
	}

	public List<WebElement> getAllBooksOnSearchPage() {
		return allBooksOnSearchPage;
	}
}
