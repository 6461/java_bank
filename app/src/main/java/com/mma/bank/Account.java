package com.mma.bank;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Account {
    public static final String ID = "bank.account.id";
    public static final String NUMBER = "bank.account.number";
    public static final String TYPE = "bank.account.type";
    public static final String BALANCE = "bank.account.balance";
    public static final String WITHDRAW_LIMIT = "bank.account.w.limit";
    public static final String TRANSFER_LIMIT = "bank.account.t.limit";
    public static final String WITHDRAW_ALLOWED = "bank.account.w.allowed";
    public static final String TRANSFER_ALLOWED = "bank.account.t.allowed";

    private String id;
    private String userID;
    private String number;
    private String type;
    private long balance;
    private long withdrawLimit;
    private long transferLimit;
    private boolean withdrawAllowed;
    private boolean transferAllowed;

    public Account(String userID, String number, String type, long balance, long withdrawLimit, long transferLimit, boolean withdrawAllowed, boolean transferAllowed) {
        this.id = UUID.randomUUID().toString();
        this.userID = userID;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.transferLimit = transferLimit;
        this.withdrawAllowed = withdrawAllowed;
        this.transferAllowed = transferAllowed;
    }

    public String getID() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    /**
     * Deposit money to this account.
     * @param amount Amount in cents.
     * @return True on success.
     */
    public boolean deposit(long amount) {
        balance = balance + amount;

        return true;
    }

    /**
     * Make a payment from this account.
     * @param amount Amount in cents.
     * @return True on success.
     */
    public boolean payment(long amount) {
        boolean payment = false;

        if (amount <= balance) {
            balance = balance - amount;
            payment = true;
        }

        return payment;
    }

    /**
     * Withdraw money from this account.
     * @param amount Amount in cents.
     * @return True on success.
     */
    public boolean withdraw(long amount) {
        boolean withdraw = false;

        if (withdrawAllowed && amount <= withdrawLimit && amount <= balance) {
            balance = balance - amount;
            withdraw = true;
        }

        return withdraw;
    }

    /**
     * Transfer money from this account.
     * @param amount Amount in cents.
     * @return True on success.
     */
    public boolean transfer(long amount) {
        boolean transfer = false;

        if (transferAllowed && amount <= transferLimit && amount <= balance) {
            balance = balance - amount;
            transfer = true;
        }

        return transfer;
    }

    /**
     * Receive money to this account.
     * @param amount Amount in cents.
     * @return True on success.
     */
    public boolean receive(long amount) {
        balance = balance + amount;

        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (%.2f)", number, ((float)balance / 100));
    }
}
