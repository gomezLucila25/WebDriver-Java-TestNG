package com.epam.tat.pages;

import com.epam.tat.utils.TestDataConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    // Locator strategy: id - stable Google identifier
    @FindBy(id = "identifierId")
    private WebElement emailInput;

    // Locator strategy: id
    @FindBy(id = "identifierNext")
    private WebElement emailNextButton;

    // Locator strategy: name - semantic attribute for password field
    @FindBy(name = "Passwd")
    private WebElement passwordInput;

    // Locator strategy: id
    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    public LoginPage open() {
        driver.get(TestDataConstants.GMAIL_URL);
        return this;
    }

    public InboxPage login(String email, String password) {
        type(waitForVisible(emailInput), email);
        click(emailNextButton);

        type(waitForVisible(passwordInput), password);
        click(passwordNextButton);

        waitForUrlContains("/mail/");
        return new InboxPage();
    }
}