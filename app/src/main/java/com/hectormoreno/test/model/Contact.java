package com.hectormoreno.test.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hectormoreno on 5/13/17.
 */

public class Contact extends BaseObservable {
    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("birthdate")
    private String birthdate;

    @Expose
    @SerializedName("zip_code")
    private String zipCode;

    @Bindable
    public String getBirthdate() {
        return birthdate;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    @Bindable
    public String getZipCode() {
        return zipCode;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
