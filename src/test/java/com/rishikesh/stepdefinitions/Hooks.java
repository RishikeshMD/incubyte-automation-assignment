package com.rishikesh.stepdefinitions;

import com.rishikesh.utils.DriverSetup;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void tearDown() {
        DriverSetup.closeDriver();
    }
}