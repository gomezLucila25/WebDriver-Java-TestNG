package com.epam.tat.tests;

import com.epam.tat.driver.DriverManager;
import com.epam.tat.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}