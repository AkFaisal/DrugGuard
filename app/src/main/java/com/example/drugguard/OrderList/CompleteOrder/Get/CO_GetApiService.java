package com.example.drugguard.OrderList.CompleteOrder.Get;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CO_GetApiService {

    @GET("/api/drug/{id}/location")
    Call<List<Complete_OrderList_Model>> getCompleteOrderList(@Path( "id" ) String id);


}
