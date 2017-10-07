package com.example.android.sanrakshan.DBUtils;

/**
 * Created by sharaddadhich on 07/10/17.
 */

public class ContactDetails {

    String name;
    String number;
    String photo;

    public ContactDetails(String name, String number, String photo) {
        this.name = name;
        this.number = number;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoto() {
        return photo;
    }
}