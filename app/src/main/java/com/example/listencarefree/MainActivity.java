package com.example.listencarefree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onSolveQuestion(View v){
        Intent intent = new Intent(getApplicationContext(),TrainActivity.class);
        startActivity(intent);
    }

    public void onRealtimesound(View v){
        Intent intent = new Intent(getApplicationContext(), SoundActivity.class);
        startActivity(intent);
    }

    public void onDiagnosticGuide(View v){
        Intent intent = new Intent(getApplicationContext(),GuideActivity.class);
        startActivity(intent);
    }

}