package com.pluralsight;

import java.util.List;

// we need the Ledger to load the transactions, then pass them to the Ledger and then we can show, filter, or report to user
public class Ledger {

    private List<Transaction> transactions;

    public Ledger(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void showAllTransactions() {

    }

    public void showDeposits() {

    }

    public void showCredits() {

    }

    public void showReports() {
        // display reports and run selected report
    }

}
