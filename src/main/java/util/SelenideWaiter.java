package util;

import java.util.function.Predicate;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Waiter;

public class SelenideWaiter {

	public SelenideWaiter waitUntilElementIsDisplayed(SelenideElement element, long timeout) {
		getWebDriverWait().wait(element, SelenideElement::isDisplayed, timeout, 10);
		return this;
	}

	private Waiter getWebDriverWait() {
		return new Waiter();
	}
}
