package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TermsPage extends BasePage{
    public TermsPage(WebDriver driver) {
        super(driver);
    }
    private final By PageTitleLocator = By.xpath("//h1[text()='TERMS OF SERVICE']");

    public String GetPageTitle() {
        return getText(PageTitleLocator);
    }
}
