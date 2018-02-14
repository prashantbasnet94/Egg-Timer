package com.example.prashant.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.health.TimerStat;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar timerSeekBar;
    Button goButton;
    boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;


    public void clicked(View view) {

        if(counterIsActive){
            textView.setText("00:30");
            counterIsActive=false;
            countDownTimer.cancel();
            timerSeekBar.setEnabled(true);
            goButton.setText("Go!");
            timerSeekBar.setProgress(30);
            mediaPlayer.stop();

        }else{



        goButton.setText("Stop");
        timerSeekBar.setEnabled(false);

       countDownTimer= new CountDownTimer(timerSeekBar.getProgress()*1000,1000){

            @Override
            public void onTick(long l) {
                updatingTextView((int) l/1000);

            }

            @Override
            public void onFinish() {
               mediaPlayer =MediaPlayer.create(getApplicationContext(),R.raw.analog);
                mediaPlayer.start();
            }
        }.start();

        counterIsActive=true;
        }
    }

    public void updatingTextView(int i){
        int minutes=i/60;
        int seconds=i-(minutes*60);
        String string=Integer.toString(minutes);
        if(0<=minutes && minutes<10){

            string="0"+string;

        }
        String stringSeconds=Integer.toString(seconds);

        if(0<=seconds && seconds<10){
            stringSeconds=Integer.toString(seconds);
            stringSeconds="0"+stringSeconds;

        }

        textView.setText(string+" : "+stringSeconds);

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        textView =(TextView) findViewById(R.id.countDownTextView);

        goButton=findViewById(R.id.button);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            updatingTextView(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }




}
