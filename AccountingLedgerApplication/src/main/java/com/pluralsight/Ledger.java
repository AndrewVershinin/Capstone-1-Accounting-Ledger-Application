package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;

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

}
