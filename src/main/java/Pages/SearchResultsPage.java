package Pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


public class SearchResultsPage extends BasePage{
	SelenideElement refineResultsButton = $(byXpath("//button[contains(text(), 'Refine results')]"));
	SelenideElement testProductThinkingInJava= $(byXpath("//a[@data-isbn='9780131872486']"));
	ElementsCollection allBooksOnSearchPage = $$(byXpath("//div[@class='book-item']"));

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

	public ElementsCollection getAllBooksOnSearchPage() {
		return allBooksOnSearchPage;
	}
}
