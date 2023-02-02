package com.team.makimainu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POJO_Admin_Status {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<POJO_Admin_Get_User> data;

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

    public List<POJO_Admin_Get_User> getData() {
        return data;
    }

    public void setData(List<POJO_Admin_Get_User> data) {
        this.data = data;
    }

}
