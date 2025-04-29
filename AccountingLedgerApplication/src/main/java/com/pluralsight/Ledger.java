package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Ledger {

    private List<Transaction> transactions;

    public Ledger(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void showAllTransactions() {
        System.out.println("\nAll Transactions");
        for (Transaction t : transactions) {
            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
        }
    }

    public void showDeposits() {
        System.out.println("\nAll Deposits");

        for (Transaction t : transactions) {
            // .compareTo(BigDecimal.ZERO returns 1 if the number inside it > 0, and returns -1 if the number inside it <0
            if (t.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }

        }
    }

    public void showCredits() {
        System.out.println("\nAll Credits");

        for (Transaction t : transactions) {
            if (t.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public void showReports() {
        // for reports logic
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nReports Menu");
            System.out.println("1. Month-To-Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year-To-Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Vendor");
            System.out.println("0. Back");
            System.out.print("Enter selection: ");

            int userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {
                case 1:
                    System.out.println("Month To Date");
                    break;
                case 2:
                    System.out.println("Previous Month");
                    break;
                case 3:
                    System.out.println("Year To Date");
                    break;
                case 4:
                    System.out.println("Previous Year");
                    break;
                case 5:
                    System.out.print("Enter vendor name to search: ");
                    String vendor = input.nextLine();
                    System.out.println("Search results for vendor: " + vendor);
                    break;
                case 0:
                    return; // exists and goes back to the ledger menu
            }


        }
    }

}
