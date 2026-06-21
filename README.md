# EPAM Automation Framework

This project is a Maven-based Java automation framework built with:

- Selenium WebDriver
- TestNG
- Page Object Model
- Extent Reports
- WebDriverManager

## Scenarios Covered

The framework currently includes automated flows for:

1. KSRTC bus search and seat counting
2. OrangeHRM child window handling and login
3. SauceDemo checkout completion

## Project Structure

```text
src
|-- main
|   |-- java/com/epam
|   |   |-- core
|   |   |-- enums
|   |   |-- listeners
|   |   |-- model
|   |   |-- pages
|   |   `-- utils
|   `-- resources
`-- test
    |-- java/com/epam
    |   `-- tests
    `-- resources
```

Notes:

- Test listeners are maintained in `src/main/java/com/epam/listeners`.
- `BaseTest` and all test classes are maintained in `src/test/java/com/epam/tests`.

## Package Responsibilities

- `core`: Framework-level setup and lifecycle utilities such as driver creation, report initialization, and config reading.
- `pages`: Page Object Model classes containing UI actions and page-specific workflows.
- `utils`: Reusable helper components for cross-cutting tasks such as screenshots, database reads, and Excel operations.
- `tests`: Executable test cases and shared test foundation (`BaseTest`) that validate business scenarios end-to-end.

## Run the Tests

```bash
mvn clean test
```

## Reports

- Extent report HTML: `reports/extent-report.html`
- TestNG/Surefire reports: `target/surefire-reports/`

## Configuration

Default test configuration is stored in `src/test/resources/config.properties`.

You can change browser, base URL, and headless mode there.
