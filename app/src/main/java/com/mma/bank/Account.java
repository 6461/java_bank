package com.mma.bank;

public class Account {
    private User user;
    private String number;
    private String type;
    private long balance = 0;
    private long withdrawLimit = 0;
    private long transferLimit = 0;
    private boolean withdrawAllowed = true;
    private boolean transferAllowed = true;

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
}
