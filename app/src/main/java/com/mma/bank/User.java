package com.mma.bank;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String name;
    private Date dateOfBirth;
    private String address;
    private String phone;

    public User(String name, Date dateOfBirth, String address, String phone) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
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
