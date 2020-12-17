package com.example.quizcapitales.model;

public class Country {
    private String name;
    private String[] topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private String[] callingCodes;
    private String capital;
    private String[] altSpellings;
    private String subregion;
    private long population;
    private double[] latlng;
    private String demonym;
    private Double area;
    private Double gini;
    private String[] timezones;
    private String[] borders;
    private String nativeName;
    private String numericCode;
    private String flag;
    private String cioc;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }
}
