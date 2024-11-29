package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class Hooks {
    public static WebDriver driver;
    private final String URL = "https://example.com/";


    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            if (browserType.equalsIgnoreCase("chrome")) {
                //Create prefs map to store all preferences
                Map<String, Object> prefs = new HashMap<String, Object>();
                //Put this into prefs map to switch off browser notification
                prefs.put("profile.default_content_setting_values.notifications", 1);
                prefs.put("excludeSwitches", "disable-popup-blocking");
                //Create chrome options to set this prefs
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver();
            } else if (browserType.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserType.equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("profile.default_content_setting_values.notifications", 1);
                edgeOptions.addArguments("--start-maximized", "--disable-popup-blocking", "--disable-notifications");
                edgeOptions.setCapability("excludeSwitches", "disable-popup-blocking");
                edgeOptions.setCapability("unhandledPromptBehavior", "ignore");
                driver = new EdgeDriver();
            }
        }
        return driver;
    }

    @Before
    public void openUrl() {
        // Specify the browser type here: "chrome", "firefox", or "edge"
        driver = getDriver("edge");
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed ScreenShot", new ByteArrayInputStream(screenshot));
        }

        if (driver != null) {
            driver.quit(); // Use quit() to close the entire browser session.
            driver = null;
        }

    }
}
