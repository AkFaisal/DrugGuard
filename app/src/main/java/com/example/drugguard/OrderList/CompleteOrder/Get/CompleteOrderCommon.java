package com.example.drugguard.OrderList.CompleteOrder.Get;

import com.example.drugguard.OrderList.Retrofit.ApiClint;

public class CompleteOrderCommon {
    private static final String BASE_URL ="http://820243d8cdf9.ngrok.io/";


    public static CO_GetApiService getCompleteOrderList() {
        return ApiClint.getClient( BASE_URL ).create( CO_GetApiService.class );
    }
}
