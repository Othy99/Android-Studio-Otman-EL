package com.example.quizcapitales.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizcapitales.R;
import com.example.quizcapitales.model.Utilisateur;
import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {
    private TextView Pseudo;
    private EditText Upseudo;
    private Button goButton;
    private Button Score; //
    private Utilisateur utilisateur;
    private static final int CODE_QUIZ_ACTIVITY = 1;
    private static final int CODE_SCORE_ACTIVITY = 2; //

    private SharedPreferences preferencesjoueur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilisateur = new Utilisateur();

        //On effectue les branchement de boutons:
        Pseudo = findViewById(R.id.textView2);
        Upseudo = findViewById(R.id.editTextTextPersonName);
        goButton = findViewById(R.id.button3);
        Score = findViewById(R.id.button);

        //on initialise nos preferences
        preferencesjoueur = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // On desactive le bouton Go pour l'instant:
        goButton.setEnabled(false);

        // On "ecoute" ce qu'il y'a dans le champs de texte :
        Upseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            // cette méthode est appelée quand on commence à écrire :
            public void onTextChanged(CharSequence nom, int start, int before, int count) {
                // dès que le nombre de caractères est > 2 on débloque notre bouton "Go" :
                goButton.setEnabled(nom.toString().length() > 2);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // cette méthode inclut ce que ca fait quand on appuye sur le bouton "Go" :
        goButton.setOnClickListener(v -> {
            //String name = Upseudo.getText().toString();
            //utilisateur.setName(name);

            String nameStr = Upseudo.getText().toString(); //



            SharedPreferences.Editor editor = preferencesjoueur.edit(); //

            editor.putString("name", nameStr); //
            editor.commit(); //


            Toast.makeText(MainActivity.this,"Information saved", Toast.LENGTH_LONG).show();


            // a chaque fois qu'on va appuyer sur le bouton GO, ca va nous diriger vers l'activité des questions
            Intent quizActivity = new Intent(MainActivity.this, QuizActivity.class);
            //le code requête est pratique si jamais je décide de créer plusieurs activités
            startActivityForResult(quizActivity, CODE_QUIZ_ACTIVITY);


        });

        // cette méthode inclut ce que ca fait quand on appuye sur le bouton "Go" :
        Score.setOnClickListener(v -> {


            // a chaque fois qu'on va appuyer sur le bouton GO, ca va nous diriger vers l'activité des questions
            Intent scoreActivity = new Intent(MainActivity.this, ScoreActivity.class);

            //le code requête est pratique si jamais je décide de créer plusieurs activités
            startActivityForResult(scoreActivity, CODE_SCORE_ACTIVITY);



        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (CODE_QUIZ_ACTIVITY == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(QuizActivity.BUNDLE_SCORE_KEY, 0);
            //j'accède aux preferences, je modifie, je crée une clé score du joueur à laquelle j'associe le score

            // sauvegarder l'ancien score avec "vieuxscore" relire l'ancien score

            int vieuxscore = preferencesjoueur.getInt("score du joueur", 0);


            if (score>vieuxscore) {
                preferencesjoueur.edit().putInt("score du joueur", score).apply();

            }

            // ici on essaye d'afficher le nom et le score de l'utilisateur au début
            String firstname = preferencesjoueur.getString("pseudo", null);

            if (null != firstname) {
                int scorejoueur = preferencesjoueur.getInt("score", score);

                String fulltext = "  Bonjour, " + firstname + " Ton dernier score est de " + scorejoueur;
                Pseudo.setText(fulltext);
                Upseudo .setText(firstname);
                Upseudo.setSelection(firstname.length());}
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}