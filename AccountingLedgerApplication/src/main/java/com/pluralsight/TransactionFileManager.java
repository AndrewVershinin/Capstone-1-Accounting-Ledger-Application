package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

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

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String line = transaction.getDate() + "|" +
                    transaction.getTime().format(timeFormatter) + "|" +
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
}
