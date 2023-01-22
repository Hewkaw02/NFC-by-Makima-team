package com.team.makimainu.Retrofit;

import static com.team.makimainu.LoginActivity.BASEURL;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.team.makimainu.BuildConfig;
import com.team.makimainu.Model.POJO_Login;
import com.team.makimainu.Model.POJO_Sign_Up;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Login;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Sign_Up;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnectManager {

    public NetworkConnectManager() {

    }

    public void callServer_Login(final NetCallback_Login callback_login, String Username, String Password) {

        OkHttpClient.Builder http = new OkHttpClient.Builder();

        /// log check
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            http.addInterceptor(loggingInterceptor);

//            Request.Builder requestBuilder = chain.request().newBuilder();
//            requestBuilder.header("Content-Type", "application/json");
//            return chain.proceed(requestBuilder.build());

        }

        // Check Type
        http.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder().header("Content-Type","application/json");
                return chain.proceed(request.build());
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(http.build())
                .build();

        APIServer callapi = retrofit.create(APIServer.class);


//        Log.e("Network connected","Response code = ");

        Call call = callapi.api_part_login(Username, Password);
        call.enqueue(new Callback<POJO_Login>() {
            @Override
            public void onResponse(Call<POJO_Login> call, Response<POJO_Login> response) {

                try {

                    POJO_Login call_IDcard = response.body();

                    if (call_IDcard == null) {
                        //404 or the response cannot be converted to User.
                        ResponseBody responseBody = response.errorBody();
                        if (responseBody != null) {
                            callback_login.onBodyError(responseBody);
                        } else {
                            callback_login.onBodyErrorIsNull();
                        }
                    } else {
                        //callback_idCard
                        callback_login.onResponse(response.body());
//                        Log.e("ResNet", "" + call_IDcard.getFullnameTh());
                    }

                } catch (Exception e) {
                    Log.e("Network connect error1", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<POJO_Login> call, Throwable t) {

                try {

                    callback_login.onFailure(t);

                } catch (Exception e) {

                    callback_login.onFailure(t);
                }

            }
        });

    }

    public void callServer_Sign_Up(final NetCallback_Sign_Up sign_up,
                                   String Name,
                                   String Email,
                                   String Username,
                                   String Password,
                                   String Phone_number) {

        OkHttpClient.Builder http = new OkHttpClient.Builder();

        /// log check
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            http.addInterceptor(loggingInterceptor);

//            Request.Builder requestBuilder = chain.request().newBuilder();
//            requestBuilder.header("Content-Type", "application/json");
//            return chain.proceed(requestBuilder.build());

        }

        // Check Type
        http.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder().header("Content-Type","application/json");
                return chain.proceed(request.build());
            }
        });


        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient();

        client.newBuilder().addInterceptor(loggingInterceptor);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(http.build())
                .build();

        APIServer callapi = retrofit.create(APIServer.class);
        Call call = callapi.api_part_register(Name,Email,Username, Password,Phone_number);
        call.enqueue(new Callback<POJO_Sign_Up>() {
            @Override
            public void onResponse(Call<POJO_Sign_Up> sign_upCall, Response<POJO_Sign_Up> response) {

                try {

                    POJO_Sign_Up signUp = response.body();

                    if (signUp == null) {
                        //404 or the response cannot be converted to User.
                        ResponseBody responseBody = response.errorBody();
                        if (responseBody != null) {
                            sign_up.onBodyError(responseBody);
                        } else {
                            sign_up.onBodyErrorIsNull();
                        }
                    } else {
                        //callback_idCard
                        sign_up.onResponse(response.body());
//                        Log.e("ResNet", "" + ());
                    }

                } catch (Exception e) {
                    Log.e("Network connect error1", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<POJO_Sign_Up> sign_upCall, Throwable t) {

                try {

                    sign_up.onFailure(t);

                } catch (Exception e) {

                    sign_up.onFailure(t);
                }

            }
        });

    }
}