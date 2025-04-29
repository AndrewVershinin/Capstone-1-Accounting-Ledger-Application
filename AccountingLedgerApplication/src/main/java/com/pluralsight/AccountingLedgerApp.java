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


    }

    private static void ledgerMenu(Scanner input, Ledger ledger) {

        while (true) {
            System.out.println("\nLedger Menu");
            System.out.println("A. All Transactions");
            System.out.println("D. Deposits");
            System.out.println("P. Payments");
            System.out.println("R. Reports");
            System.out.println("H. Home");
            System.out.print("Enter selection: ");

            String userChoice = input.nextLine().toUpperCase();

            switch (userChoice) {
                case "A":
                    ledger.showAllTransactions();
                    break;
                case "D":
                    ledger.showDeposits();
                    break;
                case "P":
                    ledger.showCredits();
                    break;
                case "R":
                    ledger.showReports();
                case "H":
                    return;
            }
        }
    }

    private static void addTransaction(Scanner input, List<Transaction> transactions, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = input.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = input.nextLine();

        System.out.print("Enter amount: ");
        String amountInput = input.nextLine();
        BigDecimal amount = new BigDecimal(amountInput);

        if (!isDeposit) {
            amount = amount.negate();
        }

        Transaction freshTransaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );
        // add to the in-memory. If we don't so we need to rerun code to see new transaction.
        transactions.add(freshTransaction);
        // save in to the CSV
        TransactionFileManager.saveTransaction("src/main/resources/transactions.csv", freshTransaction);

        System.out.println("Transaction saved successfully");
    }
}
