package com.example.quizcapitales.model;

public class QuizCapitale {
    private String pays;
    private String capitale;

    public QuizCapitale(String pays, String capitale) {
        this.pays = pays;
        this.capitale = capitale;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }
}
