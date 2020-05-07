package com.mma.bank;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private Account account;
    private String number;
    private String type;
    private Date dateValid;
    private boolean paymentAllowed = true;
    private long paymentLimit = 0;

    public Card(Account account, String number, String type, Date dateValid, boolean paymentAllowed, long paymentLimit) {
        this.account = account;
        this.number = number;
        this.type = type;
        this.dateValid = dateValid;
        this.paymentAllowed = paymentAllowed;
        this.paymentLimit = paymentLimit;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public Date getDateValid() {
        return dateValid;
    }

    public void setDateValid(Date dateValid) {
        this.dateValid = dateValid;
    }

    public boolean isPaymentAllowed() {
        return paymentAllowed;
    }

    public void setPaymentAllowed(boolean paymentAllowed) {
        this.paymentAllowed = paymentAllowed;
    }

    public long getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(long paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    @NonNull
    @Override
    public String toString() {
        return number;
    }
}
