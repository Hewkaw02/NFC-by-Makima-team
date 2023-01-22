package com.team.makimainu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_Login {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("Apikey")
    @Expose
    private Object apikey;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getApikey() {
        return apikey;
    }

    public void setApikey(Object apikey) {
        this.apikey = apikey;
    }

}
