package com.example.listencarefree;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Quest_Emotion extends AppCompatActivity {

    Call<List<Django_Item>> call;
    //TextView textView = findViewById(R.id.quest_emotion_text);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView((R.layout.quest_emotion));

        TextView textView = findViewById(R.id.quest_emotion_text);

        call = Django_Client.getApiService().getPosts("1");

        call.enqueue(new Callback<List<Django_Item>>() {
            @Override
            public void onResponse(Call<List<Django_Item>> call, Response<List<Django_Item>> response) {

                if(response.isSuccessful()){
                    List<Django_Item> result = response.body();
                    String str ="";
                    for(Django_Item item : result){
                        str += "title :" + item.getTitle() + "\n" +
                                "body :" + item.getBody() + "\n" +
                                "answer :" + item.getAnswer() + "\n" +
                                "audio :" + item.getAudio();
                    }
                    textView.setText(str);
                    Log.d(TAG, "onResponse: 성공 데이터 받아오지는 못함");
                } else{
                    textView.setText("로딩 오류");
                    Log.d(TAG,"onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<List<Django_Item>> call, Throwable t){
                Log.d(TAG, "onFailure 실패");
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

}