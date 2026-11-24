# OrangeHRM UI Automation

UI test automation for the OrangeHRM demo site using Selenium WebDriver, TestNG, Page Object Model, and Allure reporting.

## Requirements

- Java 21
- Maven 3.9 or newer
- Google Chrome (latest version)
- Allure command-line tool (only needed to open the report)

## Configuration

Environment settings live in `src/test/resources/config.properties`:

- `base.url`: the site address
- `browser`: the browser to use (chrome)
- `explicit.wait`: maximum seconds to wait for an element

Test data lives in `src/test/resources/testdata/` as JSON files.

## Running the tests

```
mvn test
```

The suite in `testng.xml` runs in parallel with 3 threads. Each thread opens its own browser.

After the run:

- Allure results are written to `allure-results/`
- Test logs are written to `logs/test-run.log`

## Generating and opening the report

```
allure generate allure-results --clean -o allure-report
allure open allure-report
```

A generated copy of the report is committed under `allure-report/`.

## Project structure

- `src/test/java/base`: driver management (`DriverManager`) and the base test class
- `src/test/java/pages`: page objects
- `src/test/java/tests`: test classes
- `src/test/java/listeners`: TestNG listeners, retry analyzer and retry transformer
- `src/test/java/utils`: config reader, JSON reader, and data providers
- `src/test/resources`: config, test data, and logging configuration

## Test cases

| # | Area | Test |
|---|------|------|
| 1 | Login | Valid credentials reach the dashboard |
| 2 | Login | Invalid credentials show an error |
| 3 | Login | Empty fields show Required messages |
| 4 | PIM | Search for an existing employee |
| 5 | PIM | Search for a non-existing employee |
| 6 | PIM | Open the Add Employee page |
| 7 | PIM | Adding without a first name shows Required |
| 8 | PIM | Add an employee and find them in the list |
| 9 | Admin | Add User form has the required fields |
| 10 | UI | Footer link opens orangehrm.com in a new tab |
| 11 | UI | Sidebar contains the expected menu items |

## Notes

- The demo site is shared and resets periodically. Employee names used in the tests are generated or read from the config file, so the tests do not depend on existing data.
- The site UI is in English. If the page language changes, the text-based checks will fail.
- Retry: a failed test is retried up to 2 times before it is marked as failed.