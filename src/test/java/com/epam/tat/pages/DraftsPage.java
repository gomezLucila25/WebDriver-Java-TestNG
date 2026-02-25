package com.epam.tat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftsPage extends AbstractPage {

    // Locator strategy: css - folder header
    @FindBy(css = "h2.TI span.l2")
    private WebElement folderHeader;

    // Locator strategy: css - all mail rows
    @FindBy(css = "tr.zA")
    private List<WebElement> mailRows;

    public String getFolderName() {
        return waitForVisible(folderHeader).getText().trim();
    }

    public boolean isDraftPresent(String subject) {
        List<WebElement> matches = driver.findElements(
            By.xpath("//tr[contains(@class,'zA')]//span[contains(text(),'" + subject + "')]")
        );
        return !matches.isEmpty();
    }

    public int getDraftCount() {
        return mailRows.size();
    }

    public MailViewPage openDraftBySubject(String subject) {
        WebElement row = waitForVisible(
            By.xpath("//tr[contains(@class,'zA')]//span[contains(text(),'" + subject + "')]")
        );
        click(row);
        return new MailViewPage();
    }
}