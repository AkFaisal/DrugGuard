package com.example.drugguard.OrderList.Interface;

import com.example.drugguard.OrderList.Model.OrderList_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitServiceOrderList {

    @GET("/api/drug/pharmacist/orders")
    Call<List<OrderList_Model>> getOrderList();



}
