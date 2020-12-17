package com.example.quizcapitales.service;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static ApiService shareApiServiceInstance = new ApiService();

    private final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

    public CountryService countryService = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
                    .baseUrl(CountryService.countryServiceUrl)
                    .build()
                    .create(CountryService.class);
}
