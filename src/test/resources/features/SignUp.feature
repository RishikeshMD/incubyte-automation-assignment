Feature: Sign Up and Sign In

  Scenario: Successful Sign Up
    Given I am on the home page with test case "TC_ID_01"
    When I click on Create New Account
    Then I should be on the Create Account page
    When I enter the first name from test data
    And I enter the last name from test data
    And I enter the email from test data
    And I enter the password from test data
    And I enter the confirm password from test data
    And I click on Submit
    Then I should see the account creation success message

  Scenario: Sign In Using Created Account
    Given I am on the home page with test case "TC_ID_02"
    When I click on Sign In
    Then I should be on the Sign In page
    When I enter the email from test data for sign in
    And I enter the password from test data for sign in
    And I click on Sign In button
    Then I should be logged in successfully