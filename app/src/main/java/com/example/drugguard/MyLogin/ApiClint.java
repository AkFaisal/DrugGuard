package com.example.drugguard.MyLogin;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {

    public static Retrofit getClint(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();



        return new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create())
                .baseUrl("http://820243d8cdf9.ngrok.io")
                .client(okHttpClient)
                .build();
    }


    public  static IApiService getUser(){

        IApiService iApiService = getClint().create( IApiService.class );
        return  iApiService;
    }


}
