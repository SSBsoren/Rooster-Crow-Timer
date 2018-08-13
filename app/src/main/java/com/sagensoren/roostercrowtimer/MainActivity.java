package com.sagensoren.roostercrowtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView ChickenImageView;
    public void show(View view)
    {
       ChickenImageView.setVisibility(View.VISIBLE);
    }
    public void hide(View view)
    {
        ChickenImageView.setVisibility(View.INVISIBLE);
    }
    SeekBar TimerSeekbar;
    TextView TimerTextView;
    Button ControllerBtn;
    boolean counterIsActive=false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        TimerTextView.setText("0:30");
        TimerSeekbar.setProgress(30);
        countDownTimer.cancel();
        ControllerBtn.setText("Go!");
        TimerSeekbar.setEnabled(true);
        counterIsActive=false;
    }

    public void updateTimer(int secondsLeft) {


        int minutes = (int) secondsLeft/ 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
       TimerTextView.setText(Integer.toString(minutes)+":"+secondString);
    }

    public void controllerFun(View view){
        if(counterIsActive==false) {
            counterIsActive = true;
            TimerSeekbar.setEnabled(false);
            ControllerBtn.setText("Stop");
          countDownTimer=  new CountDownTimer(TimerSeekbar.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    TimerTextView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rooster);
                    mediaPlayer.start();

                }
            }.start();
        }else{
            resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChickenImageView=(ImageView)findViewById(R.id.ChickenImageView);

       TimerSeekbar=(SeekBar)findViewById(R.id.TimerSeekBar);
       TimerTextView=(TextView)findViewById(R.id.TimerTextView) ;
       ControllerBtn=(Button)findViewById(R.id.ControllerBtn);

        TimerSeekbar.setMax(600);
        TimerSeekbar.setProgress(30);
        TimerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



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
