package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void showPayments() {
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
            System.out.println("6. Custom Search");
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
                case 6:
                    customSearch();
                    break;
                case 0:
                    return; // exists and goes back to the ledger menu
            }

        }
    }
    public void customSearch() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
        String startDateInput = input.nextLine();
        // ternary operator
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);

        System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
        String endDayInput = input.nextLine();
        LocalDate endDay = endDayInput.isEmpty() ? null : LocalDate.parse(endDayInput);

        System.out.print("Enter description or leave blank: ");
        String descriptionInput = input.nextLine().toLowerCase();

        System.out.print("Enter vendor or leave blank: ");
        String vendorInput = input.nextLine().toLowerCase();

        System.out.print("Enter amount or leave blank: ");
        String amountInput = input.nextLine();
        BigDecimal amount = amountInput.isEmpty() ? null : new BigDecimal(amountInput).setScale(2, RoundingMode.HALF_UP);

        System.out.println("\nSearch Results");

        for (Transaction t : transactions) {
            boolean matches = true;
            // if user put data and this date is before then start day , matches = false
            if (startDate != null && t.getDate().isBefore(startDate)) {
                matches = false;
            }

            if (endDay != null && t.getDate().isAfter(endDay)) {
                matches = false;
            }
            // if user put data and transaction is not contain user input, matches = false.
            if (!descriptionInput.isEmpty() && !t.getDescription().toLowerCase().contains(descriptionInput)) {
                matches = false;
            }
            if (!vendorInput.isEmpty() && !t.getVendor().toLowerCase().contains(vendorInput)) {
                matches = false;
            }
            // if user put data and if the user gave a specific amount, and this transaction does not equal it, then skip it.
            if (amount != null && t.getAmount().compareTo(amount) != 0) {
                matches = false;
            }

            if (matches) {
                System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }

    }
}
