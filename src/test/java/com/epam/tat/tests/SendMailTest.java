package com.epam.tat.tests;

import com.epam.tat.pages.*;
import com.epam.tat.utils.TestDataConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "regression")
public class SendMailTest extends BaseTest {

    @Test(description = "Sent mail disappears from Drafts and appears in Sent folder")
    public void testSendMailFromDraft() {
        InboxPage inboxPage = loginPage.login(
            TestDataConstants.USER_EMAIL,
            TestDataConstants.USER_PASSWORD
        );
        Assert.assertTrue(inboxPage.isInboxDisplayed(),
            "Inbox should be visible after login");

        ComposePage composePage = inboxPage.clickCompose();
        composePage.fillMail(
            TestDataConstants.RECIPIENT,
            TestDataConstants.MAIL_SUBJECT,
            TestDataConstants.MAIL_BODY
        );
        inboxPage = composePage.saveAsDraft();

        DraftsPage draftsPage = inboxPage.goToDrafts();
        Assert.assertTrue(draftsPage.isDraftPresent(TestDataConstants.MAIL_SUBJECT),
            "Draft should be present before sending");

        MailViewPage mailViewPage = draftsPage.openDraftBySubject(TestDataConstants.MAIL_SUBJECT);
        inboxPage = mailViewPage.send();

        draftsPage = inboxPage.goToDrafts();
        Assert.assertFalse(draftsPage.isDraftPresent(TestDataConstants.MAIL_SUBJECT),
            "Mail should no longer be in Drafts after sending");

        SentPage sentPage = inboxPage.goToSent();
        Assert.assertTrue(sentPage.isMailPresent(TestDataConstants.MAIL_SUBJECT),
            "Sent mail should appear in the Sent folder");
    }
}