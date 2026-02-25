package com.epam.tat.tests;

import com.epam.tat.pages.*;
import com.epam.tat.utils.TestDataConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "regression")
public class DraftMailTest extends BaseTest {

    @Test(description = "Draft is saved and its content matches the composed mail")
    public void testCreateAndVerifyDraft() {
        InboxPage inboxPage = loginPage.login(
            TestDataConstants.USER_EMAIL,
            TestDataConstants.USER_PASSWORD
        );
        Assert.assertTrue(inboxPage.isInboxDisplayed(),
            "Inbox should be visible before composing");

        ComposePage composePage = inboxPage.clickCompose();
        Assert.assertTrue(composePage.isComposeWindowVisible(),
            "Compose window should open");

        composePage.fillMail(
            TestDataConstants.RECIPIENT,
            TestDataConstants.MAIL_SUBJECT,
            TestDataConstants.MAIL_BODY
        );

        inboxPage = composePage.saveAsDraft();

        DraftsPage draftsPage = inboxPage.goToDrafts();
        Assert.assertTrue(draftsPage.isDraftPresent(TestDataConstants.MAIL_SUBJECT),
            "Draft should be present in Drafts folder");

        MailViewPage mailViewPage = draftsPage.openDraftBySubject(TestDataConstants.MAIL_SUBJECT);
        Assert.assertEquals(mailViewPage.getSubject(), TestDataConstants.MAIL_SUBJECT,
            "Draft subject should match");
        Assert.assertEquals(mailViewPage.getRecipient(), TestDataConstants.RECIPIENT,
            "Draft recipient should match");
        Assert.assertTrue(mailViewPage.getBody().contains(TestDataConstants.MAIL_BODY),
            "Draft body should contain the original text");
    }
}