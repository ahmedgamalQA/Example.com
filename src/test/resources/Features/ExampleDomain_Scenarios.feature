@SmokeTests @ExampleDomain_WebTests
Feature: Test Scenarios for Example Domain website

  Background:
    Given User Navigate to URL

  @TC1
  Scenario: TestCase 1 : Verify the first page title
    When user get the value og page title and URL
    Then Verify the page title is "Example Domain"
    And Verify the Current URL is "https://example.com/"

  @TC2
  Scenario:  TestCase 2 : Verify the browser is redirected to the IANA website
    When user Click on the "More information..." Page.
    Then Verify the browser is redirected to "https://www.iana.org/help/example-domains"

  @TC3
  Scenario:  TestCase 2 : Navigate to about page then “TERMS OF SERVICE” page.
    When user Click on the "More information..." Page.
    And user Click on the "About" Page.
    Then Verify the browser is redirected to "https://www.iana.org/about"
    And user Click on the "Terms of Service" Page.
    Then Assert that the title of the Page is "TERMS OF SERVICE"
