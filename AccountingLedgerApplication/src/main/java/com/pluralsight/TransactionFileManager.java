package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this class does reading and writing to transactions.csv
public class TransactionFileManager {

    public static List<Transaction> loadTransactions(String filename) {
        // read from csv and return List<Transaction>
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufReader.readLine()) != null) {

                String[] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                BigDecimal amount = new BigDecimal(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);

                transactions.add(transaction);
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static void saveTransaction(String filename, Transaction transaction) {
        // add a transaction to the CSV file
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filename, true));

            String line = transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount() + "|";

            bufWriter.write(line);
            bufWriter.newLine();
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("Am error happened while saving the transaction.");
            e.printStackTrace();
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
        saveTransaction("src/main/resources/transactions.csv", freshTransaction);

        System.out.println("Transaction saved successfully");
    }
}
