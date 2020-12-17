package com.example.quizcapitales.model;

public class Utilisateur {
    private String uName;



    public String getName() {
        return uName;
    }
    public void setName(String name) {
        uName = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + uName + '\'' +
                '}';
    }
}
