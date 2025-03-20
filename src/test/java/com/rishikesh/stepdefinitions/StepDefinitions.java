package com.rishikesh.stepdefinitions;

import com.rishikesh.pages.CreateAccountPage;
import com.rishikesh.pages.SignInPage;
import com.rishikesh.utils.DriverSetup;
import com.rishikesh.utils.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class StepDefinitions {
    private WebDriver driver;
    private SignInPage signInPage;
    private CreateAccountPage createAccountPage;
    private Map<String, String> testData;
    private String reusedEmail;
    private String reusedPassword;

    public StepDefinitions() {
        this.driver = DriverSetup.getDriver();
        this.signInPage = new SignInPage(driver);
        this.createAccountPage = new CreateAccountPage(driver);
        this.testData = new HashMap<>();
    }

    private void loadTestData(String testCaseId) {
        Map<String, String> loadedData = TestData.getTestData(testCaseId);
        if (loadedData == null) {
            System.err.println("Test data not found for test case: " + testCaseId + ". Initializing empty map.");
            testData = new HashMap<>();
        } else {
            testData = loadedData;
        }
        System.out.println("Loaded test data for " + testCaseId + ": " + testData);

        if (testCaseId.equals("TC_ID_02")) {
            if (testData.get("email") == null || testData.get("password") == null) {
                if (reusedEmail == null || reusedPassword == null) {
                    System.err.println("No email or password available for TC_ID_02, and no reused data from TC_ID_01. Using default values.");
                    testData.put("email", "testuser_" + System.currentTimeMillis() + "@demoincubytetesting.com");
                    testData.put("password", "Rishikesh@123");
                } else {
                    testData.put("email", reusedEmail);
                    testData.put("password", reusedPassword);
                }
            }
        }
    }

    @Given("I am on the home page with test case {string}")
    public void iAmOnTheHomePageWithTestCase(String testCaseId) {
        loadTestData(testCaseId);
        String url = "https://magento.softwaretestingboard.com/";
        System.out.println("Navigating to URL: " + url);

        int maxAttempts = 3;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                driver.get(url);
                break;
            } catch (Exception e) {
                System.out.println("Failed to load URL on attempt " + attempt + ". Retrying...");
                if (attempt == maxAttempts) {
                    throw new RuntimeException("Failed to load URL after " + maxAttempts + " attempts", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @When("I click on Create New Account")
    public void iClickOnCreateNewAccount() {
        System.out.println("Clicking on Create New Account link");
        createAccountPage.clickCreateAccountLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Create New Customer Account"));
    }

    @Then("I should be on the Create Account page")
    public void iShouldBeOnTheCreateAccountPage() {
        System.out.println("Verifying Create Account page");
        createAccountPage.verifyPage();
    }

    @When("I enter the first name from test data")
    public void iEnterTheFirstNameFromTestData() {
        String firstName = testData.get("First Name");
        if (firstName == null) {
            throw new RuntimeException("First Name is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering first name: " + firstName);
        createAccountPage.enterFirstName(firstName);
    }

    @When("I enter the last name from test data")
    public void iEnterTheLastNameFromTestData() {
        String lastName = testData.get("Last Name");
        if (lastName == null) {
            throw new RuntimeException("Last Name is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering last name: " + lastName);
        createAccountPage.enterLastName(lastName);
    }

    @When("I enter the email from test data")
    public void iEnterTheEmailFromTestData() {
        String email = testData.get("email");
        if (email == null) {
            throw new RuntimeException("Email is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering email: " + email);
        createAccountPage.enterEmail(email);
        reusedEmail = email;
    }

    @When("I enter the password from test data")
    public void iEnterThePasswordFromTestData() {
        String password = testData.get("password");
        if (password == null) {
            throw new RuntimeException("Password is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering password: " + password);
        createAccountPage.enterPassword(password);
        reusedPassword = password;
    }

    @When("I enter the confirm password from test data")
    public void iEnterTheConfirmPasswordFromTestData() {
        String confirmPassword = testData.get("confirm password");
        if (confirmPassword == null) {
            throw new RuntimeException("Confirm Password is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering confirm password: " + confirmPassword);
        createAccountPage.enterConfirmPassword(confirmPassword);
    }

    @When("I click on Submit")
    public void iClickOnSubmit() {
        System.out.println("Clicking on Submit button");
        createAccountPage.clickSubmit();
    }

    @Then("I should see the account creation success message")
    public void iShouldSeeTheAccountCreationSuccessMessage() {
        System.out.println("Verifying account creation success message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//div[contains(@class, 'message-success')]")));
            System.out.println("Success message found: " + driver.findElement(
                    org.openqa.selenium.By.xpath("//div[contains(@class, 'message-success')]")).getText());
        } catch (Exception e) {
            try {
                WebElement errorMessage = driver.findElement(
                        org.openqa.selenium.By.xpath("//div[contains(@class, 'message-error')]"));
                if (errorMessage.isDisplayed()) {
                    throw new RuntimeException("Account creation failed with error: " + errorMessage.getText());
                }
            } catch (Exception errorNotFound) {
                throw new RuntimeException("Success message not found, and no error message detected.", e);
            }
        }
    }

    @When("I click on Sign In")
    public void iClickOnSignIn() {
        System.out.println("Clicking on Sign In link");
        signInPage.clickSignInLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Customer Login"));
    }

    @Then("I should be on the Sign In page")
    public void iShouldBeOnTheSignInPage() {
        System.out.println("Actual page title: " + driver.getTitle());
        signInPage.verifyPage();
    }

    @When("I enter the email from test data for sign in")
    public void iEnterTheEmailFromTestDataForSignIn() {
        String email = testData.get("email");
        if (email == null) {
            throw new RuntimeException("Email is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering email for sign in: " + email);
        signInPage.enterEmail(email);
    }

    @When("I enter the password from test data for sign in")
    public void iEnterThePasswordFromTestDataForSignIn() {
        String password = testData.get("password");
        if (password == null) {
            throw new RuntimeException("Password is not provided in test data for test case: " + testData.get("Test Case ID"));
        }
        System.out.println("Entering password for sign in: " + password);
        signInPage.enterPassword(password);
    }

    @When("I click on Sign In button")
    public void iClickOnSignInButton() {
        System.out.println("Clicking on Sign In button");
        signInPage.clickSignInButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        System.out.println("Verifying successful login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//span[contains(@class, 'logged-in')]")));
            System.out.println("Welcome message found: " + driver.findElement(
                    org.openqa.selenium.By.xpath("//span[contains(@class, 'logged-in')]")).getText());
        } catch (Exception e) {
            try {
                WebElement errorMessage = driver.findElement(
                        org.openqa.selenium.By.xpath("//div[contains(@class, 'message-error')]"));
                if (errorMessage.isDisplayed()) {
                    throw new RuntimeException("Login failed with error: " + errorMessage.getText());
                }
            } catch (Exception errorNotFound) {
                throw new RuntimeException("Welcome message not found, and no error message detected.", e);
            }
        }
    }
}