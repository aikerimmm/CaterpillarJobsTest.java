# Caterpillar Jobs UI Test Automation

UI test automation project for the [Caterpillar Careers](https://careers.caterpillar.com/) website.

The project demonstrates automated testing of the Caterpillar Jobs page using **Java, Selenide, JUnit 5, Gradle, Allure Report, Jenkins, and Selenoid**.

---

## 📋 Contents

- [Description](#description)
- [Technologies and Tools](#technologies-and-tools)
- [Implemented Tests](#implemented-tests)
- [Project Structure](#project-structure)
- [Test Architecture](#test-architecture)
- [Running Tests](#running-tests)
- [Selenoid](#selenoid)
- [Allure Report](#allure-report)
- [Jenkins CI](#jenkins-ci)
- [Telegram Notifications](#telegram-notifications)
- [Test Execution Video](#test-execution-video)

---

## 📖 Description

This project contains automated UI tests for the **Caterpillar Careers Jobs** page.

The main goal of the project is to demonstrate a maintainable UI automation framework with:

- Page Object pattern
- reusable `TestBase`
- Selenide smart waits
- Allure `@Step` annotations
- automatic screenshots and page source attachments
- remote browser execution with Selenoid
- CI execution with Jenkins
- Allure reporting
- Telegram test notifications

Tested page:

https://careers.caterpillar.com/en/jobs/

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
| Allure Selenide | Selenide steps and attachments |
| Selenoid | Remote browser execution |
| Docker | Running Selenoid infrastructure |
| Jenkins | Continuous Integration |
| Git | Version control |
| GitHub | Source code repository |

---

## 🧪 Implemented Tests

The project contains automated tests for the Caterpillar Jobs page.

### Page opens and displays jobs

Verifies that the jobs page is successfully opened and job results are available.

### Search returns results

Performs a job search using the keyword:

```text
Engineer
```

and verifies that search results are displayed.

### Job cards are displayed

Verifies that the expected number of job cards is loaded on the page.

### First job title is displayed

Verifies that the first job card contains a non-empty job title.

### Job count is greater than minimum value

Verifies that the number of available jobs is greater than the specified minimum value.

The assertions for web elements are implemented using **Selenide conditions** such as:

```java
element.shouldBe(visible);

element.shouldHave(text("Expected text"));

collection.shouldHave(
        CollectionCondition.sizeGreaterThan(minSize)
);
```

Selenide conditions provide built-in smart waiting and help reduce flaky UI tests.

---

## 📁 Project Structure

```text
CaterpillarJobsTest.java
├── .github
│   └── images
│       ├── allure.png
│       ├── jenkins.png
│       └── telegram.png
│
├── gradle
│
├── media
│   └── test-execution.mov
│
├── notifications
│   ├── allure-notifications-4.11.0.jar
│   └── config.json
│
├── src
│   ├── main
│   │
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── TestBase.java
│       │   │
│       │   ├── pages
│       │   │   └── CaterpillarJobsPage.java
│       │   │
│       │   └── tests
│       │       └── CaterpillarJobsTest.java
│       │
│       └── resources
│           └── selenide.properties
│
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

The project follows the **Page Object Model** approach.

### TestBase

`TestBase` contains common test configuration and lifecycle logic.

It is responsible for:

- browser configuration
- Selenide configuration
- Allure Selenide listener registration
- screenshot attachments
- page source attachments
- browser cleanup after test execution

This keeps configuration and common test logic outside individual test classes.

### Page Object

`CaterpillarJobsPage` contains:

- page elements
- user actions
- page-level verification methods

Page methods use Allure `@Step` annotations to make test execution easier to understand in reports.

Example:

```java
@Step("Verify that job cards count is greater than {minSize}")
public CaterpillarJobsPage verifyCardsCountIsGreaterThan(int minSize) {
    jobCards.shouldHave(
            CollectionCondition.sizeGreaterThan(minSize)
    );
    return this;
}
```

### Tests

`CaterpillarJobsTest` contains test scenarios only.

Example:

```java
@Test
void jobCountIsPositiveNumber() {
    jobsPage
            .open()
            .verifyCardsCountIsGreaterThan(10);
}
```

This approach keeps the test code readable and separates test scenarios from implementation details.

---

## ▶️ Running Tests

### Prerequisites

Make sure the following tools are installed:

- Java
- Docker
- Gradle Wrapper is included in the project

Clone the repository:

```bash
git clone https://github.com/aikerimmm/CaterpillarJobsTest.java.git
```

Open the project directory:

```bash
cd CaterpillarJobsTest.java
```

Run all tests:

```bash
./gradlew clean test
```

Run a specific test:

```bash
./gradlew clean test --tests "tests.CaterpillarJobsTest.pageOpensAndShowsJobs"
```

---

## 🐳 Selenoid

The project supports remote browser execution using **Selenoid**.

Selenide remote configuration:

```properties
selenide.browser=chrome
selenide.remote=http://localhost:4444/wd/hub
selenide.headless=false
```

Selenoid runs browser sessions inside Docker containers, which makes the test environment isolated and reproducible.

Check that Selenoid is running:

```bash
docker ps
```

The Selenoid container should be available on port:

```text
4444
```

---

## 📊 Allure Report

The project is integrated with **Allure Report**.

Allure provides detailed information about test execution, including:

- test status
- execution steps
- execution duration
- screenshots
- page source
- failure details

### Allure Report Example

<p align="center">
  <img src=".github/images/allure.png" alt="Allure Report" width="900">
</p>

### Generate Allure Report

```bash
./gradlew allureReport
```

### Open Allure Report

```bash
./gradlew allureServe
```

Screenshots and page source are attached to Allure to simplify failure investigation.

---

## 🔄 Jenkins CI

The project contains a `Jenkinsfile` for automated test execution in CI.

The Jenkins pipeline can be used to:

- checkout the project
- execute automated UI tests
- collect test results
- generate Allure results
- publish the Allure report

### Jenkins Pipeline

<p align="center">
  <img src=".github/images/jenkins.png" alt="Jenkins Pipeline" width="900">
</p>

This allows automated tests to be executed consistently without depending only on a local development environment.

---

## 📩 Telegram Notifications

The project supports Telegram notifications for test execution results.

Notification configuration is stored in:

```text
notifications/
├── allure-notifications-4.11.0.jar
└── config.json
```

After test execution, the notification contains information about the test run and its result.

### Telegram Notification Example

<p align="center">
  <img src=".github/images/telegram.png" alt="Telegram Notification" width="500">
</p>

Sensitive credentials and tokens should not be stored directly in the public repository.

---

## 🎥 Test Execution Video

A recorded example of automated UI test execution is available in the repository.

### Watch the test execution

[▶️ Open Test Execution Video](media/test-execution.mov)

The video demonstrates the automated test interacting with the Caterpillar Careers website.

---

## 📎 Allure Attachments

For easier debugging, test execution information is automatically attached to the Allure report.

Available attachments include:

- browser screenshots
- page source
- Selenide execution steps
- failure information

Example screenshot attachment logic is located in:

```text
src/test/java/base/TestBase.java
```

---

## ⚙️ Configuration

Selenide configuration is located in:

```text
src/test/resources/selenide.properties
```

Example:

```properties
selenide.browser=chrome
selenide.remote=http://localhost:4444/wd/hub
selenide.headless=false
```

The configuration can be adjusted depending on whether tests are executed locally or through Selenoid.

---

## 🔍 Key Features

- Page Object Model
- reusable TestBase
- Selenide smart waits
- Selenide conditions instead of standard JUnit assertions for web elements
- Allure `@Step` annotations
- screenshots on test failures
- page source attachments
- remote browser execution
- Docker/Selenoid integration
- Jenkins CI pipeline
- Allure reporting
- Telegram notifications
- recorded test execution example

---

## 👩‍💻 Author

**Aikerim**

QA Automation Engineer