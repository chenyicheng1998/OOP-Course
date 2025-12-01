# 📖 Complete Usage Guide - Module 7 Lecture 4 Task

## ⚠️ IMPORTANT: Current Configuration

This application is now configured with **`update` mode** (not `drop-and-create`).

**What this means:**

- ✅ Data persists between application restarts
- ✅ Adding currencies via SQL will be saved
- ⚠️ **You MUST close the application before adding data via SQL**
- ⚠️ After adding data via SQL, you MUST restart the application

**If you see "Database is empty" after adding data:**

1. Close the application completely
2. Stop all Java processes: `Get-Process java | Stop-Process -Force`
3. Rebuild: `mvn clean compile`
4. Restart: `.\run.bat`

---

## 🚀 First Time Setup (Complete Guide)

### Step 1: Create Database (ONE TIME ONLY)

Open HeidiSQL and connect as `root`, then run:

```sql
DROP
DATABASE IF EXISTS currency_converter2;
CREATE
DATABASE currency_converter2;

GRANT
SELECT,
INSERT
,
UPDATE,
DELETE
, CREATE
, DROP, ALTER
ON currency_converter2.* TO 'appuser'@'localhost';
FLUSH
PRIVILEGES;
```

### Step 2: Start the Application

Double-click `run.bat` or run:

```bash
mvn javafx:run
```

### Step 3: Handle First Run Message

You will see an **INFORMATION** dialog (not an error!):

```
Title: First Time Setup
Message: Database is empty

The database is connected but contains no currencies yet.

Please click the 'Add Currency' button to add currencies before converting.

Recommended currencies to add:
• USD - US Dollar - 1.0
• EUR - Euro - 0.92
• GBP - British Pound - 0.79
```

**This is NORMAL!** Click OK to continue.

### Step 4: Add Currencies

You have two options:

#### Option A: Use the GUI (Easy, Recommended)

1. Click the **"Add Currency"** button
2. Fill in the form:
    - Abbreviation: `USD`
    - Name: `US Dollar`
    - Conversion Rate: `1.0`
3. Click **Save**
4. Repeat for other currencies:
    - EUR, Euro, 0.92
    - GBP, British Pound, 0.79

#### Option B: Load via SQL Script (Faster)

In HeidiSQL or PowerShell:

```sql
USE
currency_converter2;

INSERT INTO currency (abbreviation, name, conversion_rate)
VALUES ('USD', 'US Dollar', 1.000000),
       ('EUR', 'Euro', 0.920000),
       ('GBP', 'British Pound', 0.790000),
       ('JPY', 'Japanese Yen', 149.500000),
       ('CAD', 'Canadian Dollar', 1.390000),
       ('AUD', 'Australian Dollar', 1.530000),
       ('CHF', 'Swiss Franc', 0.880000),
       ('CNY', 'Chinese Yuan', 7.240000);
```

**Close and restart the application** to see the currencies.

---

## 💱 Using the Currency Converter

### Convert Currency

1. Enter amount: `100`
2. Select "From Currency": `USD - US Dollar`
3. Select "To Currency": `EUR - Euro`
4. Click **Convert**
5. See result: `92.00`
6. **Transaction is automatically saved!**

### Add More Currencies

1. Click **"Add Currency"** button anytime
2. Enter currency details
3. Click Save
4. New currency appears in dropdowns immediately

---

## 🔍 Verify Transactions in Database

### Using HeidiSQL

1. Connect to `currency_converter2` database
2. Click on `transaction` table
3. Click "Data" tab
4. See all your conversion history!

### Using SQL Query

```sql
USE
currency_converter2;

-- View all transactions
SELECT *
FROM transaction;

-- View transactions with currency details
SELECT t.transactionId,
       c1.abbreviation AS from_currency,
       c2.abbreviation AS to_currency,
       t.sourceAmount,
       t.targetAmount,
       t.transactionDate
FROM transaction t
         JOIN currency c1 ON t.source_currency_id = c1.id
         JOIN currency c2 ON t.target_currency_id = c2.id
ORDER BY t.transactionDate DESC;
```

---

## 🔧 Troubleshooting

### Problem: "Cannot connect to database" (RED error)

**Solution:** Database doesn't exist. Run Step 1 again.

### Problem: "Database is empty" (BLUE info) after adding currencies via SQL

**Solutions:**

1. **The application is caching old data** - Completely close and restart the application
2. **Using old compiled version** - Run: `mvn clean compile` then `.\run.bat`
3. **Verify data is in database:**
   ```sql
   SELECT COUNT(*) FROM currency;
   ```
   If count > 0, then data exists. Stop ALL java processes and restart.

**Quick Fix:**

```powershell
# Stop all Java processes
Get-Process -Name java -ErrorAction SilentlyContinue | Stop-Process -Force

# Rebuild and run
cd C:\Users\cheny\IdeaProjects\OOP-Course\Module7Lecture4Task
mvn clean compile
.\run.bat
```

### Problem: No currencies in dropdown

**Solutions:**

1. Close the info dialog and check the dropdown - currencies might be there
2. If truly empty, add currencies using Step 4
3. Make sure you recompiled after changing persistence.xml to "update" mode

### Problem: Data disappears on restart

**Solution:** Check `persistence.xml` - should be set to **`update`** not `drop-and-create`

Current correct setting:

```xml

<property name="jakarta.persistence.schema-generation.database.action"
          value="update"/>
```

If it says `drop-and-create`, change to `update` and recompile.

---

## 📊 Expected Console Output

### Normal Startup (Empty Database)

```
JPA EntityManager created successfully.
Retrieved 0 currencies using JPA
Warning: No currencies loaded from database
```

### After Adding Currencies

```
JPA EntityManager created successfully.
Retrieved 8 currencies using JPA
Successfully loaded 8 currencies from database
```

### After Performing Conversion

```
Transaction persisted successfully: Transaction{id=1, from=USD, to=EUR, amount=100.0, result=92.0, date=2025-12-01T19:20:00}
```

---

## ✅ Checklist for Successful Run

- [x] Database `currency_converter2` created
- [x] Privileges granted to `appuser`
- [x] Application starts without RED error
- [ ] Info message about empty database (first run only)
- [ ] At least 2 currencies added
- [ ] Currency dropdown lists show currencies
- [ ] Conversion works and shows result
- [ ] Transaction saved (check in HeidiSQL)
- [ ] Transaction ID auto-increments (1, 2, 3, ...)

---

## 🎯 Quick Commands Reference

**Create Database:**

```sql
CREATE
DATABASE currency_converter2;
GRANT ALL
ON currency_converter2.* TO 'appuser'@'localhost';
```

**Add Initial Data:**

```sql
USE
currency_converter2;
-- Copy INSERT statements from insert_initial_data.sql
```

**Run Application:**

```bash
.\run.bat
```

**Check Transactions:**

```sql
SELECT *
FROM transaction
ORDER BY transactionId DESC LIMIT 10;
```

---

## 📁 Important Files

- `run.bat` - Start the application
- `src/main/resources/insert_initial_data.sql` - Initial currency data
- `src/main/resources/database_script.sql` - Database creation script
- `README.md` - Project overview
- `USAGE_GUIDE.md` - This file

---

**Remember:** The "Database is empty" message on first run is **INFORMATION**, not an error!
It's a friendly reminder to add currencies. ✨

