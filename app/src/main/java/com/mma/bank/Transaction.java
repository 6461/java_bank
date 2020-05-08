package com.mma.bank;

import java.util.Date;

public class Transaction {
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
}
