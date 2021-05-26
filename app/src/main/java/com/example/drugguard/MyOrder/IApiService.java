package com.example.drugguard.MyOrder;

import com.example.drugguard.MyOrder.Model.Order_Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface  IApiService {

    @POST("/api/drug/pharmacist/orders")
    //@FormUrlEncoded
    //@Body User user (hash map ar jaygay)
    Call<Order_Model> placedOrder(@Body Order_Model user);
//            @Field("email") String email,
//            @Field("private_key") String private_key


}
