package Pages;
import static com.codeborne.selenide.Selenide.*;

public class PageNavigation extends BasePage {

	public static void openPage(String pageUrl) {
		open(pageUrl);
	}
}
