package com.example.listencarefree;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Django_Api {

    @GET("emotion/emotion/{number}/")
    Call<List<Django_Item>> getPosts(@Path("number") String posts);

}

