package com.epam.tat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComposePage extends AbstractPage {

    // Locator strategy: name
    @FindBy(name = "to")
    private WebElement toField;

    // Locator strategy: name
    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    // Locator strategy: css with aria-label (div inputs have no name/id)
    @FindBy(css = "div[aria-label='Message Body']")
    private WebElement bodyField;

    // Locator strategy: xpath - role + aria-label combination
    @FindBy(xpath = "//div[@role='button' and contains(@aria-label,'Send')]")
    private WebElement sendButton;

    public ComposePage fillMail(String to, String subject, String body) {
        type(waitForVisible(toField), to);
        toField.sendKeys(Keys.TAB);
        type(subjectField, subject);
        type(bodyField, body);
        return this;
    }

    public InboxPage saveAsDraft() {
        click(waitForVisible(By.cssSelector("button[aria-label='Save & close']")));
        waitForInvisible(By.cssSelector("div[aria-label='New Message']"));
        return new InboxPage();
    }

    public InboxPage sendMail() {
        click(waitForClickable(sendButton));
        waitForInvisible(By.cssSelector("div[aria-label='New Message']"));
        return new InboxPage();
    }

    public boolean isComposeWindowVisible() {
        return waitForVisible(By.cssSelector("div[aria-label='New Message']")).isDisplayed();
    }
}