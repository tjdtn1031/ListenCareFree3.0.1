package com.example.listencarefree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Train_EmotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_emotion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onSolve_emotion(View v){
        Intent intent = new Intent(getApplicationContext(),Quest_Emotion.class);
        startActivity(intent);
    }

}
