package com.example.quizcapitales.manager;

import android.util.Log;

import com.example.quizcapitales.model.Country;
import com.example.quizcapitales.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryManager {

    public static CountryManager shared = new CountryManager();

    private List<Country> countriesCache = new ArrayList<>();

    public void getAllCountries(CountriesListener listener) {
        if (!countriesCache.isEmpty()) {
            listener.onSucess(countriesCache);
            return;
        }

        ApiService.shareApiServiceInstance.countryService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();
                countriesCache.addAll(countries);
                listener.onSucess(countriesCache);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("Fetched Country Error", t.getLocalizedMessage());
                listener.onError(t);
            }
        });
    }
}