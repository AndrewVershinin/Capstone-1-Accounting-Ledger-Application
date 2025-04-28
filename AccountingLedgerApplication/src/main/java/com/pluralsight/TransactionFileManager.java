package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// this class does reading and writing to transactions.csv
public class TransactionFileManager {

//    public static List<Transaction> loadTransactions(String filename) {
//        // read from csv and return List<Transaction>
//
//    }

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

}
