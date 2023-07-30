package com.marrok.myapplication;

import java.util.List;
        import retrofit2.Call;
        import retrofit2.http.GET;

public interface RetrofitClient {

    @GET("/posts/1")
    Call<Post> getSinglePost();
    @GET("") // Make sure the endpoint is correct based on your server's API
    Call<List<Restaurant>> getResturents(); // The return type should be Call<List<Restaurant>>
}
