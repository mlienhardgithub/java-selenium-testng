# java-selenium-testng
## Java Selenium TestNG

## Overview
Demo mini-framework using Java, Selenium, and TestNG. This is a black-box UI and API approach to automating tests using Selenium for browser-based tests, and RestAssured for REST API tests. Selenium closely mimics the end-user interaction with the web browser, and closely represents the functional behavior and navigational flow through the application. TestNG is used to configure, setup, and execute the automated tests, and reports on the results.

The downside to this type of test automation is that it is prone to high maintenance, because the test code is entirely independent of the application code. Therefore it will require regular maintenance to synchronize the test code with the application code. Because of this tests will become flaky and not always fail for application defects. Due to the independent decoupling, tests will require a great deal of additional code, data, and configuration to mimic the application behavior.

Automated tests should be developed at each layer of the application stack, along with refactoring the application code for testability. At each layer test utilities should be exposed to tests at higher layers of the application stack. The instrumentation enables higher level tests to be scoped properly, and significantly reduces the amount of redundant code, data, and configuration to mimic application behavior in black box tests. This type of automation can be refactored to reuse test utilities developed at lower-levels of the application stack, making the tests deterministic.

## Packages
### Selenium
Selenium components and pages library

`app/src/test/java/testng/upgrade/lib/ui`

### Data files
`app/src/test/resources/data`

### TestNG suite
`app/src/test/resources/TestNGSuite.xml`

### Test classes
UI tests:

`app/src/test/java/testng/upgrade/e2e/UpgradeUiTests.java`

API tests:

`app/src/test/java/testng/upgrade/api/UpgradeApiTests.java`

## Configuration
Modify the following file locally:

`app/src/test/resources/config.properties`

Supply your own password to the property:

`app.password.test`

Update the directory where the GeckoDriver and ChromeDriver executables are stored:

`webdriver.directory`

Update the directory where the Chrome browser binary is stored:

`chrome.binary`

Update the directory where the Chrome browser binary is stored:

`firefox.binary`

## Running the tests
Built with Gradle 7.5.1

Run the following command to clean, build, and execute the tests:

`gradle clean build`

Open the TestNG report in a browser:

`java-selenium-testng/app/build/reports/tests/test/index.html`


