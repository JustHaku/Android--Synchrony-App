package com.example.maestro.myfirstapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    ImageButton returnButton;
    ImageButton plusButton;
    ImageButton plusButton2;
    ImageButton minusButton;
    ImageButton minusButton2;
    Button startButton;
    Button stopButton;
    ImageView user_Circle;
    Integer length = 1;
    Integer bLength = 1;
    TextView duration;
    TextView breathLength;
    TextView breathLengthText;
    TextView activityLength;
    TextView timer;
    Animation expand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_Circle = findViewById(R.id.user_Circle);
        duration =(TextView)findViewById(R.id.activityLen2);
        breathLength = (TextView)findViewById(R.id.breathLen);
        breathLengthText = (TextView)findViewById(R.id.breathLenText);
        activityLength = (TextView)findViewById(R.id.activityLen);
        timer = (TextView)findViewById(R.id.countDown);
        returnButton = findViewById(R.id.returnButton);
        plusButton = findViewById(R.id.plusIcon);
        plusButton2 = findViewById(R.id.plusIcon2);
        minusButton = findViewById(R.id.minusIcon);
        minusButton2 = findViewById(R.id.minusIcon2);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        expand = AnimationUtils.loadAnimation(this, R.anim.expand);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                startActivity(new Intent(MainActivity.this, landing_Screen.class));
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                if(length >= 1) {
                    length++;
                    duration.setText(length + " Minute(s)");
                }
            }
        });

        plusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                if(bLength >= 1) {
                    bLength++;
                    breathLength.setText(bLength + " Second(s)");
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                if(length >= 2) {
                    length--;
                    duration.setText(length + " Minute(s)");
                }
            }
        });

        minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                if(bLength >= 2) {
                    bLength--;
                    breathLength.setText(bLength + " Second(s)");
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View a) {

                fullExpand();

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                startActivity(new Intent(MainActivity.this, MainActivity.class));

            }
        });
    }

    public void fullExpand(){

        expand.setDuration(bLength * 1000);

        System.out.println(length);

        View b = findViewById(R.id.user_Circle);
        b.setVisibility(View.VISIBLE);

        plusButton.setVisibility(View.GONE);
        plusButton2.setVisibility(View.GONE);
        minusButton.setVisibility(View.GONE);
        minusButton2.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.VISIBLE);
        duration.setVisibility(View.GONE);
        breathLength.setVisibility(View.GONE);
        breathLengthText.setVisibility(View.GONE);
        activityLength.setText("Breathe with me... Keep up!");
        user_Circle.startAnimation(expand);
        timer.setVisibility(View.VISIBLE);

        new CountDownTimer(length * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setVisibility(View.GONE);
               activityLength.setText("All done, you can rest now!");
            }
        }.start();



    }



}
