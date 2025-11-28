# Currency Converter with Database Integration

A JavaFX-based currency converter application that uses MariaDB database to store and retrieve currency exchange rates.

## Prerequisites

1. **MariaDB Server** installed and running
2. **Java 17 or higher**
3. **Maven** for building the project

## Project Structure

```
Module7Lecture2Task/
├── src/main/java/
│   ├── Main.java                              # Application entry point
│   ├── view/
│   │   └── CurrencyConverterView.java        # JavaFX UI
│   ├── controller/
│   │   └── CurrencyConverterController.java  # Business logic
│   ├── dao/
│   │   └── CurrencyDao.java                  # Database access layer
│   ├── entity/
│   │   └── Currency.java                     # Entity class
│   └── datasource/
│       └── MariaDbConnection.java            # Database connection manager
└── src/main/resources/
    └── style.css                              # UI styling
```

## Quick Setup

### Step 1: Set Up Database

Run the following SQL script in HeidiSQL or MySQL command line:

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

**Note**: For Windows PowerShell, if you need to access MySQL command line, use:

```powershell
& "C:\Program Files\MariaDB 12.1\bin\mysql.exe" -u root -p
```

### Step 2: Verify Database Setup

Check if the database is correctly set up:

```sql
USE
currency_converter;
SELECT *
FROM currency;
```

You should see 8 currencies listed.

### Step 3: Build and Run the Application

```bash
cd Module7Lecture2Task
mvn clean compile
mvn javafx:run
```

If the database connection is successful, you should see:

```
Database connection established successfully.
Successfully loaded 8 currencies from database
```

### Step 4: Test the Application

1. Enter an amount (e.g., `100`)
2. Select source currency (e.g., `USD - US Dollar`)
3. Select target currency (e.g., `EUR - Euro`)
4. Click **Convert**
5. View the result (Expected: `92.00`)

## Features

- **Database-driven**: Exchange rates are fetched from MariaDB database
- **Real-time conversion**: Rates are retrieved fresh from database on each conversion
- **Error handling**: Graceful handling of database connection issues
- **User-friendly UI**: Clear instructions and validation
- **MVC architecture**: Clean separation of concerns

## Database Configuration

The application connects to the database with the following settings:

- **Host**: `localhost`
- **Port**: `3306`
- **Database**: `currency_converter`
- **User**: `appuser`
- **Password**: `password`

To change these settings, edit `src/main/java/datasource/MariaDbConnection.java`:

```java
conn =DriverManager.

getConnection(
    "jdbc:mariadb://localhost:3306/currency_converter?user=appuser&password=password");
```

## Troubleshooting

### Database Connection Failed

If you see "Failed to connect to the database", check:

1. MariaDB service is running
2. Database `currency_converter` exists
3. User `appuser` exists with correct password
4. Port 3306 is not blocked by firewall

### No Currencies Loaded

If you see "No currencies loaded from database":

1. Run the INSERT statements again
2. Verify appuser has SELECT privileges:
   ```sql
   SHOW GRANTS FOR 'appuser'@'localhost';
   ```

### Application Won't Start

If the application fails to start:

1. Ensure Java 17 or higher is installed
2. Run `mvn clean install` to download dependencies
3. Check that JavaFX dependencies are correctly configured in `pom.xml`

## Assignment Requirements Completed

This project fulfills all requirements for Module 7 Lecture 2 Assignment:

1. ✅ Refactored project structure with separate packages
2. ✅ Database connection successfully established
3. ✅ `CurrencyDao` class with `getConversionRate()` method
4. ✅ Controller uses database to fetch exchange rates
5. ✅ Application works correctly with database integration
6. ✅ Error handling for database unavailability

## Dependencies

- JavaFX 20.0.1 (UI framework)
- MariaDB JDBC Driver 3.1.2 (database connectivity)
- Logback Classic 1.4.6 (logging)

All dependencies are managed through Maven and specified in `pom.xml`.

