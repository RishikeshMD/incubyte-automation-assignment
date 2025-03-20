# incubyte-automation-assignment

This is an automation project using Behavior-Driven Development (BDD) and the Page Object Model (POM) to test the sign-up and login functionality of the Magento website (`https://magento.softwaretestingboard.com/`).

## Project Structure
- **src/main/java/com/rishikesh/pages**: Page Object classes (`HomePage.java`, `CreateAccountPage.java`, `SignInPage.java`).
- **src/main/java/com/rishikesh/utils**: Utility classes (`ExcelReader.java`, `TestData.java`).
- **src/test/java/com/rishikesh/stepdefinitions**: Cucumber step definitions (`StepDefinitions.java`).
- **src/test/java/com/rishikesh/runner**: Test runner (`TestRunner.java`).
- **src/test/resources/features**: Cucumber feature files (`SignUp.feature`).
- **src/test/resources/data**: Test data (`testdata.xlsx`).

## Setup
1. Clone the repository: