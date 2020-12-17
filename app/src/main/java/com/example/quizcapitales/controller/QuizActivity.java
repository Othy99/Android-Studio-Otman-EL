package com.example.quizcapitales.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizcapitales.R;
import com.example.quizcapitales.manager.CountriesListener;
import com.example.quizcapitales.manager.CountryManager;
import com.example.quizcapitales.model.Country;
import com.example.quizcapitales.model.Questions;
import com.example.quizcapitales.model.QuestionsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, CountriesListener {
    private TextView Question;
    private Button Choix1;
    private Button Choix2;
    private Button Choix3;
    private Button Choix4;

    private int score;
    private int nombredequestions;
    private Questions questionactuelle;
    private QuestionsSource questionssrc;

    public static final String BUNDLE_SCORE_KEY = "SCORE_KEY";
    public static final String BUNDLE_STATE_SCORE = "ScoreActuelle";
    public static final String BUNDLE_STATE_QUESTION = "QuestionActuelle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        CountryManager.shared.getAllCountries(this);

        nombredequestions = 5;

        //On effectue les branchements nécessaires :
        Choix1 = (Button) findViewById(R.id.choix1_btn);
        Choix2 = (Button) findViewById(R.id.choix2_btn);
        Choix3 = (Button) findViewById(R.id.choix3_btn);
        Choix4 = (Button) findViewById(R.id.choix4_btn);
        Question = (TextView) findViewById(R.id.question_text);

        Choix1.setTag(0);
        Choix2.setTag(1);
        Choix3.setTag(2);
        Choix4.setTag(3);

        Choix1.setOnClickListener(this);
        Choix2.setOnClickListener(this);
        Choix3.setOnClickListener(this);
        Choix4.setOnClickListener(this);
    }
    // un override ou pas ?
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == questionactuelle.getAnswerIndex()) {
            // Bonne réponse :)
            // On utilise le systeme des "Toast" ici qui permet d'afficher des messages qui disparaissent apres un bout de temps
            Toast.makeText(this, "Bonne réponse! ;)", Toast.LENGTH_SHORT).show();
            score=score+1;
        } else {
            // Mauvaise réponse :'(
            Toast.makeText(this, "Mauvaise réponse! :'( ", Toast.LENGTH_SHORT).show();
        }

        //on vérifie par la suite combien il reste de questions, si le compteur est à 0, il reste plus de question et le jeu s'arrête
        if (--nombredequestions == 0) {
            endgame();
        } else {
            questionactuelle = questionssrc.getQuestion();
            displayQuestion(questionactuelle);
        }
    }

    private void endgame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Le jeu est fini!")
                .setMessage("Votre score est de " + score)
                .setMessage("Rejouez à nouveau pour essayer d'améliorer votre score ! ")

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_SCORE_KEY, score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void displayQuestion(final Questions question) {
        Question.setText(question.getQuestion());
        Choix1.setText(question.getChoiceList().get(0));
        Choix2.setText(question.getChoiceList().get(1));
        Choix3.setText(question.getChoiceList().get(2));
        Choix4.setText(question.getChoiceList().get(3));
    }


    private QuestionsSource generateQuestions(List<Country> countries) {
        Random random = new Random();
        Map<String,String> map = new HashMap<String,String>();

        List<Questions> questions = new ArrayList<>();

        for(int i=0; i <= nombredequestions; i++) {
            int a=genererInt(0, 200);
            int b=genererInt(0, 200);
            int c=genererInt(0, 200);
            //
            int x=genererInt(0,3);

            // c'est un peu moche mais ca permet de rendre les réponses plus aléatoires en effet, avant de mettre ce case, la bonne réponse était tjr la 0
            switch (x) {
                case 0:
                    questions.add(new Questions(
                    "Quelle est la capitale de "+ countries.get(i).getName() + "?",

                    Arrays.asList(countries.get(i).getCapital(), countries.get(a).getCapital(), countries.get(b).getCapital(), countries.get(c).getCapital()),
                    0));
                    break;
                case 1:
                    questions.add(new Questions(
                            "Quelle est la capitale de "+ countries.get(i).getName() + "?",

                            Arrays.asList( countries.get(a).getCapital(), countries.get(i).getCapital(),countries.get(b).getCapital(), countries.get(c).getCapital()),
                            1));
                    break;
                case 2:
                    questions.add(new Questions(
                            "Quelle est la capitale de "+ countries.get(i).getName() + "?",

                            Arrays.asList( countries.get(a).getCapital(), countries.get(b).getCapital(),countries.get(i).getCapital(), countries.get(c).getCapital()),
                            2));
                    break;
                case 3:
                    questions.add(new Questions(
                            "Quelle est la capitale de "+ countries.get(i).getName() + "?",

                            Arrays.asList(countries.get(a).getCapital(), countries.get(b).getCapital(), countries.get(c).getCapital(), countries.get(i).getCapital()),
                            3));
                    break;

            }
        }
        return new QuestionsSource(questions);
    }

    @Override
    public void onSucess(List<Country> countries) {
        Log.e("OnSuccess", countries.toString());
        questionssrc = generateQuestions(countries);

        questionactuelle = questionssrc.getQuestion();
        this.displayQuestion(questionactuelle);
    }

    @Override
    public void onError(Throwable t) {

    }
    int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }
}