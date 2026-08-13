# Caterpillar Jobs UI Test Automation

UI test automation project for the [Caterpillar Careers](https://careers.caterpillar.com/) website.

The project demonstrates automated testing of the Caterpillar Jobs page using Java, Selenide, JUnit 5, Gradle, Allure Report and Jenkins CI.

---

## 📋 Contents

- [Description](#description)
- [Technologies and Tools](#technologies-and-tools)
- [Implemented Tests](#implemented-tests)
- [Project Structure](#project-structure)
- [Test Architecture](#test-architecture)
- [Running Tests](#running-tests)
- [Jenkins CI](#jenkins-ci)
- [Allure Report](#allure-report)
- [Telegram Notifications](#telegram-notifications)
- [Test Execution Video](#test-execution-video)

---

## 📌 Description

This project contains automated UI tests for the Caterpillar Careers Jobs page.

The tests verify the main functionality of the jobs search page, including:

- opening the Caterpillar Jobs page;
- verifying that job vacancies are displayed;
- searching for jobs by keyword;
- verifying that search results are returned;
- verifying the number of displayed job cards;
- verifying that job titles are displayed correctly.

The project follows the **Page Object Model** pattern.

Test setup and browser configuration are separated into `TestBase`, while page interactions and UI assertions are implemented inside the Page Object.

---

## 🛠 Technologies and Tools

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenide | UI test automation |
| Selenium WebDriver | Browser automation |
| JUnit 5 | Test framework |
| Gradle | Build and dependency management |
| Allure Report | Test reporting |
| Allure Selenide | Screenshots and page source attachments |
| Jenkins | CI test execution |
| Docker | Containerized infrastructure |
| Selenoid | Remote browser execution |
| Git / GitHub | Version control |
| Telegram Bot | CI test result notifications |

---

## ✅ Implemented Tests

The project contains automated checks for the Caterpillar Jobs page.

### Page opening

Verifies that the jobs page opens successfully and job results are available.

### Job search

Searches for jobs using the `Engineer` keyword and verifies that search results are displayed.

### Job cards

Verifies that the expected job cards are loaded on the page.

### Job title

Verifies that the first displayed vacancy contains a non-empty job title.

### Job count

Verifies that the number of available job cards is greater than the expected minimum value.

Selenide conditions such as `shouldBe()` and `shouldHave()` are used for UI assertions.  
These conditions provide built-in waiting and help reduce flaky tests.

---

## 📂 Project Structure

```text
CaterpillarJobsTest.java
├── .github
├── gradle
│   └── wrapper
├── media
│   └── test-execution.mov
├── notifications
│   ├── allure-notifications-4.11.0.jar
│   └── config.json
├── src
│   ├── main
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── TestBase.java
│       │   ├── pages
│       │   │   └── CaterpillarJobsPage.java
│       │   └── tests
│       │       └── CaterpillarJobsTest.java
│       └── resources
│           └── selenide.properties
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── Jenkinsfile
├── README.md
└── settings.gradle
```

---

## 🏗 Test Architecture

The project uses the **Page Object Model** pattern.

### TestBase

`TestBase` contains common test configuration and setup logic.

It is responsible for:

- browser configuration;
- Selenide configuration;
- Allure Selenide listener configuration;
- screenshot attachments;
- page source attachments;
- common setup and teardown logic.

This keeps configuration code separate from the test scenarios.

### Page Object

`CaterpillarJobsPage` contains:

- page locators;
- interactions with UI elements;
- search actions;
- UI validations;
- Selenide assertions.

Page methods are annotated with Allure `@Step` annotations to make test execution easier to understand in Allure Report.

Example:

```java
@Step("Verify that job cards count is greater than {minSize}")
public CaterpillarJobsPage verifyCardsCountIsGreaterThan(int minSize) {
    jobCards.shouldHave(CollectionCondition.sizeGreaterThan(minSize));
    return this;
}
```

### Tests

Test classes contain only high-level test scenarios.

Example:

```java
@Test
void jobCountIsPositiveNumber() {
    jobsPage.open()
            .verifyCardsCountIsGreaterThan(10);
}
```

This approach keeps tests readable and moves implementation details into the Page Object.

---

## ▶️ Running Tests

### Prerequisites

Before running the project, make sure the following tools are installed:

- Java
- Git
- Docker
- Google Chrome

Check Java:

```bash
java -version
```

Check Docker:

```bash
docker --version
```

---

### Clone the repository

```bash
git clone https://github.com/aikerimmm/CaterpillarJobsTest.java.git
```

Open the project directory:

```bash
cd CaterpillarJobsTest.java
```

---

### Run all tests

```bash
./gradlew clean test
```

Successful execution should finish with:

```text
BUILD SUCCESSFUL
```

---

### Run a specific test

For example:

```bash
./gradlew clean test --tests "tests.CaterpillarJobsTest.pageOpensAndShowsJobs"
```

---

## 🚀 Jenkins CI

The project contains a `Jenkinsfile` for automated test execution in Jenkins.

The CI pipeline is responsible for running the automated tests and generating test results.

Typical pipeline flow:

```text
GitHub Repository
        ↓
     Jenkins
        ↓
   Gradle Build
        ↓
 Automated Tests
        ↓
  Allure Results
        ↓
  Allure Report
        ↓
Telegram Notification
```

The Jenkins integration allows tests to be executed automatically instead of relying only on local execution.

> Jenkins is running in a local environment, therefore a public Jenkins URL is not provided in this repository.

---

## 📊 Allure Report

The project is integrated with **Allure Report**.

Allure provides detailed information about test execution, including:

- test status;
- test steps;
- execution duration;
- screenshots;
- page source attachments;
- failed test information.

### Generate Allure report

Run:

```bash
./gradlew allureReport
```

### Open Allure report

Run:

```bash
./gradlew allureServe
```

The report will be generated from the test execution results.

### Allure Attachments

`TestBase` and `AllureSelenide` are configured to attach useful debugging information to the report.

This includes:

```text
Screenshot
Page Source
```

These attachments make it easier to investigate failed UI tests.

---

## 📩 Telegram Notifications

The Jenkins pipeline is integrated with Telegram notifications.

After test execution, the pipeline can send information about the build and test results to Telegram.

This makes it possible to quickly see the CI execution status without constantly checking Jenkins.

The notification configuration is stored separately from the test logic.

Sensitive values such as bot tokens should not be committed to the repository.

---

## 🎥 Test Execution Video

An example of automated UI test execution is included in the repository.

[▶️ Watch Test Execution](media/test-execution.mov)

The video demonstrates an automated Caterpillar Jobs UI test execution and successful Gradle build.

---

## 🔐 Configuration and Security

Environment-specific configuration and sensitive data should not be stored directly in the repository.

IDE-specific files are also excluded from Git.

The `.gitignore` configuration excludes files such as:

```text
.idea/
.gradle/
build/
*.iml
.DS_Store
```

This keeps the repository clean and prevents IDE-specific configuration from being shared between developers.

---

## 👩‍💻 Author

**Aikerim**

QA Automation Engineer