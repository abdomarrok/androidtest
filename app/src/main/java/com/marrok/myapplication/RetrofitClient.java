package com.marrok.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitClient {


    @POST("/signup/")
    Call<User> SignUpNew(@Body User user);

    @POST("/login/")
    Call<User> LoginUser(@Body User user);


    @POST("/migrate_to_seller/")
    Call<Seller> createSeller(@Body Seller request);

}
