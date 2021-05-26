package com.example.drugguard.MyProduct.Common;

import com.example.drugguard.MyProduct.Retrofit.ApiClint;
import com.example.drugguard.MyProduct.Interface.RetrofitService;

public class Common {

    private static final String BASE_URL ="http://820243d8cdf9.ngrok.io/api/";


    public static RetrofitService getProduct() {
        return ApiClint.getClient( BASE_URL ).create( RetrofitService.class );
    }



}