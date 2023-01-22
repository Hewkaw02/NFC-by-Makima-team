package com.team.makimainu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_Sign_Up {

    @SerializedName("staus")
    @Expose
    private String staus;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
