package com.rishikesh.stepdefinitions;

import com.rishikesh.pages.CreateAccountPage;
import com.rishikesh.pages.HomePage;
import com.rishikesh.utils.TestData;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

public class StepDefinitions {
    private WebDriver driver;

    @Given("I am on the home page with test case {string}")
    public void iAmOnTheHomePageWithTestCase(String testCaseId) throws IOException {
        TestData.loadTestData(testCaseId);
        String browser = TestData.getBrowser();
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.get(TestData.getUrl());
    }

    @When("I click on Create New Account")
    public void iClickOnCreateNewAccount() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCreateNewAccount();
    }

    @Then("I should be on the Create Account page")
    public void iShouldBeOnTheCreateAccountPage() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.verifyCreateAccountPage();
    }

    @When("I enter the first name from test data")
    public void iEnterTheFirstNameFromTestData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterFirstName(TestData.getFirstName());
    }

    @When("I enter the last name from test data")
    public void iEnterTheLastNameFromTestData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterLastName(TestData.getLastName());
    }

    @When("I enter the email from test data")
    public void iEnterTheEmailFromTestData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterEmail(TestData.getEmail());
    }

    @When("I enter the password from test data")
    public void iEnterThePasswordFromTestData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterPassword(TestData.getPassword());
    }

    @When("I enter the confirm password from test data")
    public void iEnterTheConfirmPasswordFromTestData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterConfirmPassword(TestData.getConfirmPassword());
    }

    @When("I click on Submit")
    public void iClickOnSubmit() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.clickSubmit();
    }

    @Then("I should see the account creation success message")
    public void iShouldSeeTheAccountCreationSuccessMessage() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.verifyAccountCreation();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}