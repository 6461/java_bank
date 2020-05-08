package com.mma.bank;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

public class User {
    public static final String ID = "bank.user.id";
    public static final String NAME = "bank.user.name";
    public static final String DOB = "bank.user.dob";
    public static final String ADDRESS = "bank.user.address";
    public static final String PHONE = "bank.user.phone";

    private String id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String phone;

    public User(String name, Date dateOfBirth, String address, String phone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
