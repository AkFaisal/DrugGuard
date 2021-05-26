package com.example.drugguard.MyProduct.Interface;

import com.example.drugguard.MyProduct.Model.Product_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("drug")
    Call<List<Product_Model>> getProducts();



}
