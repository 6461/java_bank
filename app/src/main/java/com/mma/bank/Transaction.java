package com.mma.bank;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private Account account;
    private Date date;
    private String type;
    private long amount;

    public Transaction(Account account, Date date, String type, long amount) {
        this.account = account;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
