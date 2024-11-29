package StepDefinitions;

import Pages.IanaPage;
import Pages.TermsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static StepDefinitions.Hooks.driver;

import Pages.ExamplePage;
import org.testng.asserts.SoftAssert;

public class ExampleDomain_WebTests {
    String ActualFirstPageTitle;
    String ActualCurrentURL;
    ExamplePage examplepage = new ExamplePage(driver);
    IanaPage ianaPage = new IanaPage(driver);
    TermsPage termsPage = new TermsPage(driver);
    SoftAssert soft = new SoftAssert();

    @Given("User Navigate to URL")
    public void userNavigateToURL() {
    }

    @When("user get the value og page title and URL")
    public void userGetTheValueOgPageTitle() {
        ActualFirstPageTitle = examplepage.GetTextOfPageTitle();
        ActualCurrentURL = examplepage.GetCurrentURL();
    }

    @Then("Verify the page title is {string}")
    public void verifyThePageTitleIs(String ExpectedFirstPageTitle) {
        soft.assertTrue(ActualFirstPageTitle.equals(ExpectedFirstPageTitle));
        System.out.println(ActualFirstPageTitle);
        soft.assertAll();
    }

    @And("Verify the Current URL is {string}")
    public void verifyTheCurrentURLIs(String ExpectedCurrentURL) {
        soft.assertTrue(ActualCurrentURL.equals(ExpectedCurrentURL));
        System.out.println("The Actual Result of TC 1 is: " + ActualCurrentURL);
        soft.assertAll();
    }

    @When("user Click on the {string} Page.")
    public void userClickOnTheLink(String Link) {
        examplepage.ClickOnLinkName(Link);
    }

    @Then("Verify the browser is redirected to {string}")
    public void verifyTheBrowserIsRedirectedTo(String ExpectedAssertionToURL) {
        String ActualAssertionToURL = ianaPage.GetCurrentURL();
        soft.assertTrue(ActualAssertionToURL.equals(ExpectedAssertionToURL));
        System.out.println("The Actual Result of TC 2 is: " + ActualAssertionToURL);
        soft.assertAll();
    }

    @Then("Assert that the title of the Page is {string}")
    public void assertThatTheTitleOfThePageIs(String ExpectedTitleOfTermsPage) {
        String ActualTitleOfTermsPage = termsPage.GetPageTitle();
        soft.assertTrue(ActualTitleOfTermsPage.equals(ExpectedTitleOfTermsPage));
        System.out.println("The Actual Result of TC 3 is: " + ActualTitleOfTermsPage);
        soft.assertAll();
    }
}
