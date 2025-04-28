package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // test transaction
        Transaction t = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                "Deposit from somewhere",
                "ATM",
                new BigDecimal("1000.00")

        );

        TransactionFileManager.saveTransaction("src/main/resources/transactions.csv", t);
    }
}
