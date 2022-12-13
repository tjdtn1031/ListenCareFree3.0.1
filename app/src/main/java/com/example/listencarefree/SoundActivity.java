package com.example.listencarefree;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import org.tensorflow.lite.Interpreter;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;


public class SoundActivity extends AppCompatActivity {

    /**오디오 파일 관련 변수*/
    // 오디오 권한
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private Thread mRecordingThread = null;

    private MediaRecorder mediaRecorder;
    private String audioFileName; // 오디오 녹음 생성 파일 이름
    private boolean isRecording = false;    // 현재 녹음 상태를 확인하기 위함.
    private Uri audioUri = null; // 오디오 파일 uri



    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sound);

    }

    public void onDefault(View v){
        final ImageButton btn = findViewById(R.id.imageButton);

        ImageView image = findViewById(R.id.imageView3);

        TextView text = findViewById(R.id.textView5);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.defaultbutton);
                text.setText("현재 기본감지가 실행되고 있습니다.");

                Interpreter tflite = getTfliteInterpreter("default_mfcc_model.tflite");

                mRecordingThread = new Thread(new Runnable() {

                    @Override

                    public void run() {
                        startRecording();
                    }

                }, "AudioRecorder Thread");

                mRecordingThread.start();


                /*if(isRecording) {
                        // 현재 녹음 중 O
                        // 녹음 상태에 따른 변수 아이콘 & 텍스트 변경
                        isRecording = false; // 녹음 상태 값
                        stopRecording();
                    } else {
                        // 현재 녹음 중 X
                        if(checkAudioPermission()) {
                            // 녹음 상태에 따른 변수 아이콘 & 텍스트 변경
                            isRecording = true; // 녹음 상태 값
                            startRecording();
                        }
                    }

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            isRecording = false;
                            stopRecording();
                        }
                    }, 2000);// 2초 정도 딜레이를 준 후 시작
                    */

            }
        });

    }


    public void onSiren(View v){
        final ImageButton btn = findViewById(R.id.imageButton2);

        ImageView image = findViewById(R.id.imageView3);

        TextView text = findViewById(R.id.textView5);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName()); // 여분의 키
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR"); // 언어 설정

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.sirenbutton);
                text.setText("현재 사이렌감지가 실행되고 있습니다.");

                Interpreter tflite = getTfliteInterpreter("default_mfcc_model.tflite");

                mRecordingThread = new Thread(new Runnable() {

                    @Override

                    public void run() {
                        startRecording();
                    }

                }, "AudioRecorder Thread");

                mRecordingThread.start();
            }
        });

    }

    public void onBabycry(View v){
        final ImageButton btn = findViewById(R.id.imageButton3);

        ImageView image = findViewById(R.id.imageView3);

        TextView text = findViewById(R.id.textView5);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName()); // 여분의 키
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR"); // 언어 설정

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.babybutton);
                text.setText("현재 울음감지가 실행되고 있습니다");

                Interpreter tflite = getTfliteInterpreter("default_mfcc_model.tflite");

                mRecordingThread = new Thread(new Runnable() {

                    @Override

                    public void run() {
                        startRecording();
                    }

                }, "AudioRecorder Thread");

                mRecordingThread.start();
            }
        });
    }

    public void onScream(View v){
        final ImageButton btn = findViewById(R.id.imageButton4);

        ImageView image = findViewById(R.id.imageView3);

        TextView text = findViewById(R.id.textView5);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName()); // 여분의 키
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR"); // 언어 설정

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.screambutton);
                text.setText("현재 비명감지가 실행되고 있습니다");

                Interpreter tflite = getTfliteInterpreter("default_mfcc_model.tflite");

                mRecordingThread = new Thread(new Runnable() {

                    @Override

                    public void run() {
                        startRecording();
                    }

                }, "AudioRecorder Thread");

                mRecordingThread.start();
            }
        });

    }

    private Interpreter getTfliteInterpreter(String modelPath){
        try {
            return new Interpreter(loadModelFile(SoundActivity.this, modelPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private MappedByteBuffer loadModelFile(Activity activity, String modelPath) throws IOException{
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void startRecording() {
        //파일의 외부 경로 확인
        String recordPath = getExternalFilesDir("/").getAbsolutePath();
        audioFileName = recordPath + "/" +"Recordsound.wav";

        //Media Recorder 생성 및 설정
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(audioFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //녹음 시작
        mediaRecorder.start();
    }
    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

        // 파일 경로(String) 값을 Uri로 변환해서 저장
        //      - Why? : 리사이클러뷰에 들어가는 ArrayList가 Uri를 가지기 때문
        //      - File Path를 알면 File을  인스턴스를 만들어 사용할 수 있기 때문

    }
    private boolean checkAudioPermission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), recordPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }
}
