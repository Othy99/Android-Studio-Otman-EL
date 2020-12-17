package com.example.quizcapitales.model;

import java.util.Collections;
import java.util.List;

public class QuestionsSource {

    private List<Questions> ListedesQuestions;
    private int NextQuestionIndex;

    public QuestionsSource(List<Questions> questionList) {
        ListedesQuestions = questionList;
        //shuffle = mélanger
        Collections.shuffle(ListedesQuestions);
        NextQuestionIndex = 0;
    }

    public Questions getQuestion() {
        // Ensure we loop over the questions
        if (NextQuestionIndex == ListedesQuestions.size()) {
            NextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return ListedesQuestions.get(NextQuestionIndex++);
    }
}
