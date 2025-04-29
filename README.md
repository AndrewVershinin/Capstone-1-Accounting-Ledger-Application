# Capstone-1-Accounting-Ledger-Application

This is a Java-based command-line application for managing financial transactions such as deposits and payments. It allows users to track transactions, view reports, and store all entries in a CSV file.

## Features

- Add Deposits and Payments (stored with correct positive/negative values)
- View all transactions
- Filter by:
  - Deposits
  - Payments
- Generate reports:
  - Month-To-Date
  - Previous Month
  - Year-To-Date
  - Previous Year
  - Search by Vendor
- Data is saved and loaded from `transactions.csv`

## Technologies Used

- Java 17
- BigDecimal for precise financial calculations
- Java Time API (`LocalDate`, `LocalTime`)
- File I/O (`BufferedReader`, `BufferedWriter`)
- OOP Principles

## File Format (transactions.csv)

Each transaction is stored on a new line in the following format:

date|time|description|vendor|amount

## Future Improvements

- Export filtered reports
