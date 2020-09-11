package com.example.gadsleaderboard.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL = "https://docs.google.com/forms/d/e/";

    //Create Logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create OKHttp Client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);


    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit sRetrofit = sBuilder.build();

    public static <S> S buildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }
}
