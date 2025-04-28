package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        List<Transaction> transactions = TransactionFileManager.loadTransactions("src/main/resources/transactions.csv");

        for (Transaction t : transactions) {
            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
        }


        // test transaction
//        Transaction t = new Transaction(
//                LocalDate.now(),
//                LocalTime.now(),
//                "Just the second test",
//                "ATM",
//                new BigDecimal("2000.00")
//
//        );

//        TransactionFileManager.saveTransaction("src/main/resources/transactions.csv", t);
    }
}
