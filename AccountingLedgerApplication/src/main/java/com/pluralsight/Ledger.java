package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;
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

            LocalDate today = LocalDate.now();

            switch (userChoice) {
                case 1:
                    System.out.println("Month To Date Report");

                    LocalDate firstDayOfMonth = today.withDayOfMonth(1);

                    for (Transaction t : transactions) {
                        LocalDate eachDate = t.getDate();
                        // !isBefore returns true if we have no days before our startOfMonth and !isAfter returns true if we have no days after today.
                        if (!eachDate.isBefore(firstDayOfMonth) && !eachDate.isAfter(today)) {
                            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Previous Month Report");

                    LocalDate firstDayOfPrevMonth = today.minusMonths(1).withDayOfMonth(1);
                    LocalDate lastDayOfPrevMonth = today.withDayOfMonth(1).minusDays(1);

                    for (Transaction t : transactions) {
                        LocalDate eachDate = t.getDate();
                        if (!eachDate.isBefore(firstDayOfPrevMonth) && !eachDate.isAfter(lastDayOfPrevMonth)) {
                            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Year To Date Report");

                    LocalDate firstDayOfYear = today.withDayOfYear(1);

                    for (Transaction t : transactions) {
                        LocalDate eachDate = t.getDate();
                        if (!eachDate.isBefore(firstDayOfYear) && !eachDate.isAfter(today)) {
                            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Previous Year Report");

                    LocalDate firstDayOfPrevYear = today.minusYears(1).withDayOfYear(1);
                    LocalDate lastDayOfPrevYear = today.withDayOfYear(1).minusDays(1);

                    for (Transaction t : transactions) {
                        LocalDate eachDay = t.getDate();
                        if (!eachDay.isBefore(firstDayOfPrevYear) && !eachDay.isAfter(lastDayOfPrevYear)) {
                            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Search By Vendor");
                    System.out.print("Enter vendor name to search: ");
                    String vendorSearch = input.nextLine().toLowerCase();

                    for (Transaction t : transactions) {
                        if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                        }
                    }
                    break;
                case 0:
                    return; // exists and goes back to the ledger menu
            }

        }
    }
}
