package com.rishikesh.utils;

import java.io.IOException;
import java.util.Map;

public class TestData {
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;
    private static String confirmPassword;
    private static String browser;
    private static String url;

    public static void loadTestData(String testCaseId) throws IOException {
        Map<String, String> testData = ExcelReader.getTestData(testCaseId);
        firstName = testData.get("First Name");
        lastName = testData.get("Last Name");
        String baseEmail = testData.get("email");
        String timestamp = String.valueOf(System.currentTimeMillis());
        email = baseEmail.replace("@", "+" + timestamp + "@"); // e.g., incubytetest+1234567890@demoincubytetesting.com
        password = testData.get("password");
        confirmPassword = testData.get("confirm password");
        browser = testData.get("Browser");
        url = testData.get("URL"); // Use the URL from test data
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getConfirmPassword() {
        return confirmPassword;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getUrl() {
        return url;
    }
}