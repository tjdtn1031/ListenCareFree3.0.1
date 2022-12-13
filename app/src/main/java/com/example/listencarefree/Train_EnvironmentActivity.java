package com.example.listencarefree;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class Train_EnvironmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_environment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onQuestActivicy(View v){
        Intent intent = new Intent(getApplicationContext(), Train_EmotionActivity.class);
        startActivity(intent);
    }


}