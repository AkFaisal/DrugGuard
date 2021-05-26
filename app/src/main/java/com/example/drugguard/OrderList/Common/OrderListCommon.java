package com.example.drugguard.OrderList.Common;

import com.example.drugguard.OrderList.Interface.RetrofitServiceOrderList;
import com.example.drugguard.OrderList.Retrofit.ApiClint;

public class OrderListCommon {

    private static final String BASE_URL ="http://820243d8cdf9.ngrok.io";


    public static RetrofitServiceOrderList getOrderList() {
        return ApiClint.getClient( BASE_URL ).create( RetrofitServiceOrderList.class );
    }



}