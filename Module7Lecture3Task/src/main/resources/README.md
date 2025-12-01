# Currency Converter with JPA

A JavaFX currency converter application using JPA (Jakarta Persistence API) with Hibernate and MariaDB database.

## Prerequisites

- MariaDB Server installed and running
- Java 17 or higher
- Maven

## Quick Start

### Step 1: Ensure Database Exists

The `currency_converter` database with `currency` table must exist. If not, run:

```sql
DROP
DATABASE IF EXISTS currency_converter;
CREATE
DATABASE currency_converter;
USE
currency_converter;

CREATE TABLE currency
(
    id              INT            NOT NULL AUTO_INCREMENT,
    abbreviation    VARCHAR(3)     NOT NULL UNIQUE,
    name            VARCHAR(50)    NOT NULL,
    conversion_rate DECIMAL(10, 6) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO currency (abbreviation, name, conversion_rate)
VALUES ('USD', 'US Dollar', 1.000000),
       ('EUR', 'Euro', 0.920000),
       ('GBP', 'British Pound', 0.790000),
       ('JPY', 'Japanese Yen', 149.500000),
       ('CAD', 'Canadian Dollar', 1.390000),
       ('AUD', 'Australian Dollar', 1.530000),
       ('CHF', 'Swiss Franc', 0.880000),
       ('CNY', 'Chinese Yuan', 7.240000);

DROP
USER IF EXISTS 'appuser'@'localhost';
CREATE
USER 'appuser'@'localhost' IDENTIFIED BY 'password';
GRANT
SELECT,
INSERT
,
UPDATE,
DELETE
ON currency_converter.* TO 'appuser'@'localhost';
FLUSH
PRIVILEGES;
```

### Step 2: Build and Run

```bash
cd Module7Lecture3Task
mvn clean compile
mvn javafx:run
```

### Step 3: Use the Application

**Convert Currency:**

1. Enter amount (e.g., `100`)
2. Select "From Currency" (e.g., `USD - US Dollar`)
3. Select "To Currency" (e.g., `EUR - Euro`)
4. Click **Convert** → Result: `92.00`

**Add New Currency:**

1. Click **Add Currency** button
2. Enter abbreviation (e.g., `SEK`)
3. Enter name (e.g., `Swedish Krona`)
4. Enter conversion rate (e.g., `10.50`)
5. Click **Save**
6. New currency will appear in dropdown lists immediately

