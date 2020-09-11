package com.example.gadsleaderboard.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessageService {


    @GET("form")
    Call<String> getForm();
}
