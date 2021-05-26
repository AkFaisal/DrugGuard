package com.example.drugguard.OrderList.CompleteOrder.Post;

import com.example.drugguard.OrderList.CompleteOrder.Get.Complete_OrderList_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CO_PostApiService {


    @POST("/api/drug/location/role")

    Call<List<Complete_OrderList_Model>> role(@Body Complete_OrderList_Model complete_orderList_model);


}
