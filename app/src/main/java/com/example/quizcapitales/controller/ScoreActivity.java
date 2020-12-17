package com.example.quizcapitales.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizcapitales.R;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quizcapitales.R.layout.activity_score);
        TextView t1;
        TextView t2;


        t1 = findViewById(R.id.textView9);
        t2 = findViewById(R.id.textView10);

        SharedPreferences preferencesjoueur = getApplicationContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String name = preferencesjoueur.getString("name", "");

        int score = preferencesjoueur.getInt("score du joueur", 0);
        String scoreStr = String.valueOf(score);


            t1.setText(name);
            t2.setText(scoreStr);

        }
    }

