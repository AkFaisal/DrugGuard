package com.example.drugguard.MyLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface  IApiService {

    @POST("/login")
    //@FormUrlEncoded
    //@Body User user (hash map ar jaygay)
    Call<User> login(@Body User user);
//            @Field("email") String email,
//            @Field("private_key") String private_key


}
