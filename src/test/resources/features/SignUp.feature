Feature: Sign Up Functionality

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