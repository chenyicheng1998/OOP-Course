-- Quick setup script to add initial currencies
-- Run this in HeidiSQL or MySQL command line after creating the database

USE currency_converter2;

-- Add common currencies
INSERT INTO currency (abbreviation, name, conversion_rate) VALUES
    ('USD', 'US Dollar', 1.000000),
    ('EUR', 'Euro', 0.920000),
    ('GBP', 'British Pound', 0.790000),
    ('JPY', 'Japanese Yen', 149.500000),
    ('CAD', 'Canadian Dollar', 1.390000),
    ('AUD', 'Australian Dollar', 1.530000),
    ('CHF', 'Swiss Franc', 0.880000),
    ('CNY', 'Chinese Yuan', 7.240000);

-- Verify data inserted
SELECT * FROM currency;

