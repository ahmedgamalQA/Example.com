package Pages;

import org.openqa.selenium.WebDriver;

public class IanaPage extends BasePage {
    public IanaPage(WebDriver driver) {
        super(driver);
    }

    public String GetCurrentURL() {
        return driver.getCurrentUrl();
    }

}
