package com.team.makimainu.Retrofit.OnNetworkCallback;

import com.team.makimainu.Model.POJO_Sign_Up;

import okhttp3.ResponseBody;

public interface NetCallback_Sign_Up {

    public void onResponse(POJO_Sign_Up Sign_Up);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
