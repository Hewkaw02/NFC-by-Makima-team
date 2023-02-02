package com.team.makimainu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POJO_Admin_Key_Detail {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("key_data")
    @Expose
    private List<String> keyData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getKeyData() {
        return keyData;
    }

    public void setKeyData(List<String> keyData) {
        this.keyData = keyData;
    }

}

