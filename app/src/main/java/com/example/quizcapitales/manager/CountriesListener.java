package com.example.quizcapitales.manager;

import com.example.quizcapitales.model.Country;

import java.util.List;

public interface CountriesListener {
    void onSucess(List<Country> countries);
    void onError(Throwable t);
}
