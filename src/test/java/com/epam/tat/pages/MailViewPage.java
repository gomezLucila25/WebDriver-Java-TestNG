package com.epam.tat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailViewPage extends AbstractPage {

    // Locator strategy: css class - Gmail h2 for subject
    @FindBy(css = "h2.hP")
    private WebElement subjectHeader;

    // Locator strategy: css class - Gmail div for mail body
    @FindBy(css = "div.a3s.aiL")
    private WebElement mailBody;

    // Locator strategy: css aria-label
    @FindBy(css = "div[aria-label*='Send']")
    private WebElement sendButton;

    public String getSubject() {
        return waitForVisible(subjectHeader).getText().trim();
    }

    public String getRecipient() {
        // Locator strategy: By.xpath at call-site - email attribute
        return waitForVisible(By.xpath("//span[@email]")).getAttribute("email").trim();
    }

    public String getBody() {
        return waitForVisible(mailBody).getText().trim();
    }

    public InboxPage send() {
        click(waitForClickable(sendButton));
        waitForInvisible(By.cssSelector("div[aria-label='New Message']"));
        return new InboxPage();
    }
}