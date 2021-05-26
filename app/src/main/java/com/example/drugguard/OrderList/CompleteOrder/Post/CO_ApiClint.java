package com.example.drugguard.OrderList.CompleteOrder.Post;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CO_ApiClint {

    public static Retrofit getClint(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();



        return new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create(new GsonBuilder().setLenient().create() ))
                .baseUrl("http://820243d8cdf9.ngrok.io")
                .client(okHttpClient)
                .build();
    }


    public  static CO_PostApiService getCO_Post(){

        CO_PostApiService co_postApiService = getClint().create( CO_PostApiService.class );
        return  co_postApiService;
    }
}
