package com.mma.bank;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

public class Card {
    public static final String CARD_ID = "bank.card.id";
    public static final String CARD_NUMBER = "bank.card.number";
    public static final String CARD_TYPE = "bank.card.type";
    public static final String CARD_VALID = "bank.card.valid";
    public static final String CARD_LIMIT = "bank.card.limit";
    public static final String CARD_ALLOWED = "bank.card.allowed";

    private String id;
    private String accountID;
    private String number;
    private String type;
    private Date dateValid;
    private boolean paymentAllowed;
    private long paymentLimit;

    public Card(String accountID, String number, String type, Date dateValid, boolean paymentAllowed, long paymentLimit) {
        this.id = UUID.randomUUID().toString();
        this.accountID = accountID;
        this.number = number;
        this.type = type;
        this.dateValid = dateValid;
        this.paymentAllowed = paymentAllowed;
        this.paymentLimit = paymentLimit;
    }

    public String getID() {
        return id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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
