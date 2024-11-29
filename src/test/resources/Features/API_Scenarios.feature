@ApiTests
Feature: Test Scenarios for Json Placeholder APIs

  Background:
    Given Authenticate to Api Service


  Scenario: A GET request to fetch a list of posts
    When Get All "posts"
    Then verify the status code is 200
    And Verify that every post contain "userId" and "id" and "title", "body"


  Scenario:  A GET request to fetch a single post by id
    When Get Single Post by endpoint is "posts" and ID is "/1"
    Then verify the status code is 200
    And verify the Response contains  "userId" and "id" and "title", "body"
    And Verify the Response id is 1