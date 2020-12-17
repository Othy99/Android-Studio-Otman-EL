package com.example.quizcapitales.service;

import com.example.quizcapitales.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    static String countryServiceUrl = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<List<Country>> getCountries();
}
