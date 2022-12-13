package com.example.listencarefree;

//  REST API 데이터 구조
//  Title(String)
//  Body(String)
//  Answer(Integer)
//  Audio(File)

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Django_Item {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("answer")
    @Expose
    private Integer answer;

    @SerializedName("audio")
    @Expose
    private String audio;

    public String getTitle(){
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getAnswer() {
        return answer;
    }

    public String getAudio() {
        return audio;
    }
}
