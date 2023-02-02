package com.team.makimainu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_Admin_Get_User {
    @SerializedName("ID")
    @Expose
    private String id;
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
    @SerializedName("Type_user")
    @Expose
    private String typeUser;
    @SerializedName("Key_unit")
    @Expose
    private String keyUnit;
    @SerializedName("Apikey")
    @Expose
    private Object apikey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getKeyUnit() {
        return keyUnit;
    }

    public void setKeyUnit(String keyUnit) {
        this.keyUnit = keyUnit;
    }

    public Object getApikey() {
        return apikey;
    }

    public void setApikey(Object apikey) {
        this.apikey = apikey;
    }

}