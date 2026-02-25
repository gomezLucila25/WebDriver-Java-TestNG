package com.epam.tat.tests;

import com.epam.tat.pages.InboxPage;
import com.epam.tat.pages.LoginPage;
import com.epam.tat.utils.TestDataConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "smoke")
public class LoginLogoutTest extends BaseTest {

    @Test(description = "User can log in and the Inbox is displayed")
    public void testSuccessfulLogin() {
        InboxPage inboxPage = loginPage.login(
            TestDataConstants.USER_EMAIL,
            TestDataConstants.USER_PASSWORD
        );
        Assert.assertTrue(inboxPage.isInboxDisplayed(),
            "Inbox should be displayed after login");
        Assert.assertTrue(inboxPage.getTitle().contains(TestDataConstants.INBOX_TITLE),
            "Page title should contain Inbox");
    }

    @Test(description = "User can sign out and is redirected to login page",
          dependsOnMethods = "testSuccessfulLogin")
    public void testSuccessfulLogout() {
        InboxPage inboxPage = loginPage.login(
            TestDataConstants.USER_EMAIL,
            TestDataConstants.USER_PASSWORD
        );
        LoginPage backToLogin = inboxPage.signOut();
        Assert.assertTrue(
            backToLogin.driver.getCurrentUrl().contains("google.com"),
            "After sign out URL should point to Google accounts"
        );
    }
}