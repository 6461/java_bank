package com.mma.bank;

import androidx.annotation.NonNull;

import java.util.Date;

public class Transaction {
    public static final String AMOUNT = "bank.transaction.amount";

    private String accountID;
    private Date date;
    private String type;
    private long amount;

    public Transaction(String accountID, Date date, String type, long amount) {
        this.accountID = accountID;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s %.2f", Converter.dateToString(date, "dd.MM.yyyy HH.mm"), type, ((double) amount / 100));
    }
}
