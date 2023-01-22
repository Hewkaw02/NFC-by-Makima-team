package com.team.makimainu.Retrofit.OnNetworkCallback;

import com.team.makimainu.Model.POJO_Login;
import com.team.makimainu.Model.POJO_Sign_Up;

import java.util.List;

import okhttp3.ResponseBody;

public interface NetCallback_Login {

    public void onResponse(POJO_Login Sign_In);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
