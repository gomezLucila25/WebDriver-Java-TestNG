# Gmail End-to-End Test Suite — Selenium WebDriver · TestNG · Page Object

![Java](https://img.shields.io/badge/Java-11-orange) ![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A) ![TestNG](https://img.shields.io/badge/TestNG-suite-red)

End-to-end tests against a real, complex web app (Gmail). It is harder than a demo site: dynamic DOM, multi-step login and async UI updates.

## Scenarios

| Suite | Scenario |
|---|---|
| Smoke | Log in and log out |
| Regression | Compose a mail and **save it as a draft**, then verify it shows in Drafts with the right recipient, subject and body |
| Regression | **Send a draft**, then verify it disappears from Drafts and appears in Sent |

## Design

- **Page Object Model**: `LoginPage`, `InboxPage`, `ComposePage`, `DraftsPage`, `SentPage` and `MailViewPage`, all extending `AbstractPage`. Navigation methods return the next page, which gives a fluent flow.
- **Locator strategy chosen per element and documented in code**: stable `id` where Google provides one, semantic `name` for form fields, and XPath/CSS only when needed.
- `DriverManager` centralizes the driver lifecycle. Test data lives in one constants class.
- TestNG **groups** (smoke / regression) wired in `testng.xml`.

## Run

Set a **test** account in `TestDataConstants` (the credentials in the repo are placeholders), then:

```bash
mvn clean test
```

## Stack

Java 11 · Selenium WebDriver · WebDriverManager · TestNG · Maven

---
Part of my QA automation portfolio → see the full framework with design patterns, BDD, Allure and Jenkins: [selenium-framework-patterns](https://github.com/gomezLucila25/selenium-framework-patterns)
