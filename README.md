# Incubyte Automation Assignment

This repository contains an automation test suite developed as part of the Incubyte Software Developer in Testing assignment. The project automates the user sign-up and sign-in functionalities on the Magento demo website ([https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/)) using Behavior-Driven Development (BDD) and the Page Object Model (POM) design pattern.

## Project Overview

The project uses **Cucumber**, **Selenium WebDriver**, and **Java** to implement automated tests for the following scenarios:
1. **Successful Sign Up**: Registers a new user on the Magento website.
2. **Sign In Using Created Account**: Logs in with the credentials created during the sign-up process.

The test data is read from an Excel file (`testdata.xlsx`), and the framework is built using Maven for dependency management.

## Project Structure

- **`src/main/java/com/rishikesh/pages`**: Page Object classes for different pages of the Magento website.
  - `HomePage.java`
  - `CreateAccountPage.java`
  - `SignInPage.java`
- **`src/main/java/com/rishikesh/utils`**: Utility classes for test setup and data handling.
  - `DriverSetup.java`: Manages WebDriver initialization.
  - `ExcelReader.java`: Reads test data from Excel files.
  - `TestData.java`: Loads and provides test data for the test scenarios.
- **`src/test/java/com/rishikesh/stepdefinitions`**: Cucumber step definitions and hooks.
  - `StepDefinitions.java`: Defines the steps for the Cucumber scenarios.
  - `Hooks.java`: Contains setup and teardown logic (e.g., closing the browser).
- **`src/test/java/com/rishikesh/runner`**: Test runner for executing Cucumber tests.
  - `TestRunner.java`
- **`src/test/resources/features`**: Cucumber feature files defining the test scenarios.
  - `SignUp.feature`
- **`src/test/resources`**: Test data files.
  - `testdata.xlsx`: Contains test data for the sign-up and sign-in scenarios.

## Prerequisites

To run this project, ensure you have the following installed on your system:
- **Java Development Kit (JDK)**: Version 20 or later.
- **Maven**: For dependency management and building the project.
- **Git**: To clone the repository.
- **A Web Browser**: Chrome (default) or Firefox (configurable in `testdata.xlsx`).
- **IDE (Optional)**: IntelliJ IDEA, Eclipse, or VS Code for development.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/RishikeshMD/incubyte-automation-assignment.git
   cd incubyte-automation-assignment
