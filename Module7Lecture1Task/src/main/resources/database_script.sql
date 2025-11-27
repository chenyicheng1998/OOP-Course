-- 1. Drop the existing database if it exists
DROP DATABASE IF EXISTS currency_converter;

-- 2. Create the database
CREATE DATABASE currency_converter;

-- 3. Use the database
USE currency_converter;

-- 4. Create the currency table
CREATE TABLE currency (
                          id INT NOT NULL AUTO_INCREMENT,
                          abbreviation VARCHAR(3) NOT NULL UNIQUE,
                          name VARCHAR(50) NOT NULL,
                          conversion_rate DECIMAL(10, 6) NOT NULL,
                          PRIMARY KEY (id)
);

-- 5. Insert currency data (using USD as the base currency with approximate exchange rates as of November 2025)
INSERT INTO currency (abbreviation, name, conversion_rate) VALUES
                                                               ('USD', 'US Dollar', 1.000000),
                                                               ('EUR', 'Euro', 0.920000),
                                                               ('GBP', 'British Pound', 0.790000),
                                                               ('JPY', 'Japanese Yen', 149.500000),
                                                               ('CAD', 'Canadian Dollar', 1.390000),
                                                               ('AUD', 'Australian Dollar', 1.530000),
                                                               ('CHF', 'Swiss Franc', 0.880000),
                                                               ('CNY', 'Chinese Yuan', 7.240000);

-- 6. Drop the existing user if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- 7. Create a new user
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password';

-- 8. Grant privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter.* TO 'appuser'@'localhost';

-- Flush privileges
FLUSH PRIVILEGES;