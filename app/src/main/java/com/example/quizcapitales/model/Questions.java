package com.example.quizcapitales.model;

import java.util.List;

public class Questions {
    private String Question; // la question posée
    private List<String> Reponses; // La liste des réponses proposées
    private int IndiceBonneReponse; // l'indice de la bonne réponse dans la liste ci-dessus

    public Questions(String question, List<String> reponses, int bonneReponse) {
        Question = question;
        Reponses = reponses;
        IndiceBonneReponse = bonneReponse;
    }

    public String getQuestion() {
        return Question;
    }

    public List<String> getChoiceList() {
        return Reponses;
    }

    public int getAnswerIndex() {
        return IndiceBonneReponse;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
