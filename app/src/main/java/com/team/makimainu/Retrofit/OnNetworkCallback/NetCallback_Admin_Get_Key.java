package com.team.makimainu.Retrofit.OnNetworkCallback;

import com.team.makimainu.Model.POJO_Admin_Key_Detail;

import okhttp3.ResponseBody;

public interface NetCallback_Admin_Get_Key {

    public void onResponse(POJO_Admin_Key_Detail key_detail);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
