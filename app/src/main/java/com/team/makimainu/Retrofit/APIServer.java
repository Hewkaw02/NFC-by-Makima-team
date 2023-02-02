package com.team.makimainu.Retrofit;

import com.team.makimainu.Model.POJO_Admin_Key_Detail;
import com.team.makimainu.Model.POJO_Admin_Status;
import com.team.makimainu.Model.POJO_Login;
import com.team.makimainu.Model.POJO_Sign_Up;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServer {

    @FormUrlEncoded
    @POST("login.php")
    Call<POJO_Login> api_part_login(@Field("Username") String Username,
                                    @Field("Password") String  Password);

    @FormUrlEncoded
    @POST("register.php")
    Call<POJO_Sign_Up> api_part_register (@Field("Name") String Name ,
                                          @Field("Email")   String Email ,
                                          @Field("Username") String Username,
                                          @Field("Password")    String Password ,
                                          @Field("Phone_number")    String Phone_Number);

    @FormUrlEncoded
    @POST("admin_get_user.php")
    Call<POJO_Admin_Status> api_admin_get_user(@Field("Type_user") String Type_user);

    @FormUrlEncoded
    @POST("admin_user_key.php")
    Call<POJO_Admin_Key_Detail> api_admin_get_key(@Field("users_id") String users_id);

}
