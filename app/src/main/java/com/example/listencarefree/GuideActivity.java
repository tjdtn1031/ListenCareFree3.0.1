package com.example.listencarefree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_guide);
    }

    public void on0to4(View v){
        final TextView text = findViewById(R.id.guide_text);

        TextView btn = findViewById(R.id.textView_0to4);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                text.setText("주변소음이 차단되는 sound booth에서 실시되어야합니다.\n수면상태인 아기의 귀에서 8~10cm 거리에서 장난감 소리를 들려주었을 때 2초 이내에 다음과 같은 행동을 하면 적절한 반응으로 간주합니다.\n" +
                        "-눈 깜빡임, 경련, 몸전체를 흔듦, 머리를 움직임, 그 외 몸의 확실한 움직임, 상기 반응의 조합적 반응.");
            }
        });
    }

    public void on5to2(View v){
        final TextView text = findViewById(R.id.guide_text);

        TextView btn = findViewById(R.id.textView_5to2);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                text.setText("주변소음이 차단되는 sound booth에서 실시되어야합니다.\n소리자극에 따른 연쇄적 시각강화(장난감 또는 동영상)를 익히도록 한 후, 소리자극을 주고 반응(소리가 난 곳으로 고개를 돌림)을 나타내면 강화장난감 또는 강화동영상을 보여주는 원리입니다.\n" +
                        "12개월 이후에는 주파수별 청력측정도 가능합니다.");
            }
        });
    }

    public void on2to5(View v){
        final TextView text = findViewById(R.id.guide_text);

        TextView btn = findViewById(R.id.textView_2to5);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                text.setText("주변소음이 차단되는 sound booth에서 실시되어야합니다.\n순음검사를 시행하기 전 단계에서 놀이를 통해 어린이의 집중능력과 호기심을 유지하며 검사를 시행하는 방법입니다.\n" +
                        "주파수별 측정, 좌우귀의 분리측정, 청력의 종류측정도 가능합니다.");
            }
        });
    }

}
