package com.example.maestro.myfirstapplication;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    ImageButton returnButton;
    ImageButton selectButton;
    ImageButton plusButton;
    ImageButton plusButton2;
    ImageButton minusButton;
    ImageButton minusButton2;
    ImageButton customButton;
    Button startButton;
    Button normalStart;
    Button stopButton;
    ImageView user_Circle;
    Integer length = 1;
    Integer bLength = 0;
    TextView duration;
    TextView breathLength;
    TextView breathLengthText;
    TextView introText;
    TextView choiceText1;
    Animation expand;
    double result = 1;
    private RelativeLayout relativeLayout2;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        Transition exitTrans = new Fade();
        getWindow().setExitTransition(exitTrans);
        Transition reenterTrans = new Fade();
        getWindow().setReenterTransition(reenterTrans);
        Transition enterTrans = new Fade();
        getWindow().setEnterTransition(enterTrans);
        Transition returnTrans = new Fade();
        getWindow().setReturnTransition(returnTrans);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_Circle = findViewById(R.id.user_Circle);
        duration =(TextView)findViewById(R.id.activityLen2);
        breathLength = (TextView)findViewById(R.id.breathLen);
        breathLengthText = (TextView)findViewById(R.id.breathLenText);
        introText = (TextView)findViewById(R.id.header);
        choiceText1 = (TextView)findViewById(R.id.selectionText);
        returnButton = findViewById(R.id.returnButton);
        selectButton = findViewById(R.id.selectionButton);
        plusButton = findViewById(R.id.plusIcon);
        plusButton2 = findViewById(R.id.plusIcon2);
        minusButton = findViewById(R.id.minusIcon);
        minusButton2 = findViewById(R.id.minusIcon2);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        normalStart = findViewById(R.id.preset1);
        customButton = findViewById(R.id.customSession);
        expand = AnimationUtils.loadAnimation(this, R.anim.expand);

        relativeLayout2 = findViewById(R.id.relativeLayout2);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) relativeLayout2.getBackground();

        // setting enter fade animation duration to 3 seconds
        animationDrawable.setEnterFadeDuration(3000);

        // setting exit fade animation duration to 3 seconds
        animationDrawable.setExitFadeDuration(3000);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                startActivity(new Intent(MainActivity.this, landing_Screen.class));
            }
        });

        normalStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                bLength = 3000;
                fullExpand();
                normalStart.setVisibility(View.GONE);
                customButton.setVisibility(View.GONE);
                breathLength.setVisibility(View.GONE);
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                selectButton.setVisibility(View.GONE);

                startActivity(new Intent(MainActivity.this, MainActivity.class));

                returnButton.setVisibility(View.VISIBLE);

            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                plusButton.setVisibility(View.VISIBLE);
                plusButton2.setVisibility(View.VISIBLE);
                minusButton.setVisibility(View.VISIBLE);
                minusButton2.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                breathLength.setVisibility(View.VISIBLE);
                breathLengthText.setVisibility(View.VISIBLE);
                choiceText1.setVisibility(View.VISIBLE);
                customButton.setVisibility(View.GONE);
                returnButton.setVisibility(View.GONE);
                selectButton.setVisibility(View.VISIBLE);
                normalStart.setVisibility(View.GONE);
                breathLengthText.setText("Total Breath Length:");

                introText.setText("Please choose your session settings");
                choiceText1.setText("Activity Length:");
                duration.setText("1 Minute(s)");
                breathLength.setText("1 Second(s)");


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

                if(result >= 1) {
                    bLength = bLength + 500;
                    result = result + 0.5;
                    breathLength.setText(result + " Second(s)");
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

                if(result >= 1.5) {
                    bLength = bLength - 500;
                    result = result - 0.5;
                    breathLength.setText(result + " Second(s)");
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

        expand.setDuration(1000 + bLength);

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
        introText.setText("Breathe with me... Keep up!");
        user_Circle.startAnimation(expand);
        choiceText1.setVisibility(View.VISIBLE);

        new CountDownTimer(length * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
               choiceText1.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
              // timer.setVisibility(View.GONE);
               introText.setText("All done, you can rest now!");
               choiceText1.setText("Click the stop button to restart.");

            }
        }.start();



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }

}
