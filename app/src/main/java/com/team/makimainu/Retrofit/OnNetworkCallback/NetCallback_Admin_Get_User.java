package com.team.makimainu.Retrofit.OnNetworkCallback;

import com.team.makimainu.Model.POJO_Admin_Get_User;
import com.team.makimainu.Model.POJO_Admin_Status;
import com.team.makimainu.Model.POJO_Login;

import java.util.List;

import okhttp3.ResponseBody;

public interface NetCallback_Admin_Get_User {


    public void onResponse(POJO_Admin_Status status);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);


}
