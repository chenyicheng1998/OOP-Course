USE currency_converter;

-- Query 1: Retrieve all currencies
SELECT * FROM currency;

-- Query 2: Retrieve a specific currency by abbreviation (e.g., EUR)
SELECT * FROM currency WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies
SELECT COUNT(*) as currency_count FROM currency;

-- Query 4: Retrieve the currency with the highest exchange rate
SELECT * FROM currency ORDER BY conversion_rate DESC LIMIT 1;