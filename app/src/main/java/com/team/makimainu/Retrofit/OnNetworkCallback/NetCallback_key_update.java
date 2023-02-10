package com.team.makimainu.Retrofit.OnNetworkCallback;

import com.team.makimainu.Model.POJO_Admin_Key_Detail;
import com.team.makimainu.Model.POJO_Key_Update;

import okhttp3.ResponseBody;

public interface NetCallback_key_update {

    public void onResponse(POJO_Key_Update key_update);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
