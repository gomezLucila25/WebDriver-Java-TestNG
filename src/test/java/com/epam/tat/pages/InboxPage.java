package com.epam.tat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends AbstractPage {

    // Locator strategy: css with custom attribute gh='cm'
    @FindBy(css = "div[gh='cm']")
    private WebElement composeButton;

    // Locator strategy: css with aria-label (partial match)
    @FindBy(css = "a[aria-label*='Google Account']")
    private WebElement accountButton;

    public boolean isInboxDisplayed() {
        return waitForVisible(composeButton).isDisplayed();
    }

    public ComposePage clickCompose() {
        click(composeButton);
        return new ComposePage();
    }

    public DraftsPage goToDrafts() {
        // Locator strategy: By.cssSelector at call-site (shows variety)
        WebElement draftsLink = waitForVisible(By.cssSelector("a[href*='#drafts']"));
        click(draftsLink);
        return new DraftsPage();
    }

    public SentPage goToSent() {
        // Locator strategy: By.xpath at call-site (shows variety)
        WebElement sentLink = waitForVisible(By.xpath("//a[@href and contains(@href,'#sent')]"));
        click(sentLink);
        return new SentPage();
    }

    public LoginPage signOut() {
        click(accountButton);
        click(waitForVisible(By.cssSelector("a[href*='signout']")));
        return new LoginPage();
    }

    public String getTitle() {
        return getPageTitle();
    }
}