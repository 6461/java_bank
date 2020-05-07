package com.mma.bank;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Account implements Serializable {
    private User user;
    private String number;
    private String type;
    private long balance;
    private long withdrawLimit;
    private long transferLimit;
    private boolean withdrawAllowed;
    private boolean transferAllowed;

    public Account(User user, String number, String type, long balance, long withdrawLimit, long transferLimit, boolean withdrawAllowed, boolean transferAllowed) {
        this.user = user;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.transferLimit = transferLimit;
        this.withdrawAllowed = withdrawAllowed;
        this.transferAllowed = transferAllowed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(long withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public long getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(long transferLimit) {
        this.transferLimit = transferLimit;
    }

    public boolean isWithdrawAllowed() {
        return withdrawAllowed;
    }

    public void setWithdrawAllowed(boolean withdrawAllowed) {
        this.withdrawAllowed = withdrawAllowed;
    }

    public boolean isTransferAllowed() {
        return transferAllowed;
    }

    public void setTransferAllowed(boolean transferAllowed) {
        this.transferAllowed = transferAllowed;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (%.2f)", number, ((float)balance / 100));
    }
}
