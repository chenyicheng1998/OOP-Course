-- Database setup script for Module7Lecture4Task
-- Creates database and grants privileges for currency converter with transactions

-- Create database
DROP DATABASE IF EXISTS currency_converter2;
CREATE DATABASE currency_converter2;
USE currency_converter2;

-- Grant privileges to appuser
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER
ON currency_converter2.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;

-- Note: Tables will be created automatically by Hibernate
-- The schema-generation.database.action is set to "drop-and-create" in persistence.xml

-- Initial currency data will be inserted through the application
-- You can add initial currencies manually if needed:
/*
INSERT INTO currency (abbreviation, name, conversion_rate) VALUES
    ('USD', 'US Dollar', 1.000000),
    ('EUR', 'Euro', 0.920000),
    ('GBP', 'British Pound', 0.790000),
    ('JPY', 'Japanese Yen', 149.500000),
    ('CAD', 'Canadian Dollar', 1.390000),
    ('AUD', 'Australian Dollar', 1.530000),
    ('CHF', 'Swiss Franc', 0.880000),
    ('CNY', 'Chinese Yuan', 7.240000);
*/

