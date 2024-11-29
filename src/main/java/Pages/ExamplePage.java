package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExamplePage extends BasePage {
    public ExamplePage(WebDriver driver) {
        super(driver);
    }

    private final By PageTitleLocator = By.tagName("h1");
    private By LinkNameLocator;

    public String GetTextOfPageTitle() {
        return getText(PageTitleLocator);
    }

    public String GetCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void ClickOnLinkName(String LinkName) {
        LinkNameLocator = By.xpath("//a[text()='" + LinkName + "']");
        Click(LinkNameLocator);
    }

}
