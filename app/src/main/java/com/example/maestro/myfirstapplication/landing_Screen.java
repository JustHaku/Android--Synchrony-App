package com.example.maestro.myfirstapplication;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class landing_Screen extends Activity {

    ImageButton transitionButton;
    private RelativeLayout relativeLayout;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__screen);

        Transition exitTrans = new Fade();
        getWindow().setExitTransition(exitTrans);
        Transition reenterTrans = new Fade();
        getWindow().setReenterTransition(reenterTrans);
        Transition enterTrans = new Fade();
        getWindow().setEnterTransition(enterTrans);
        Transition returnTrans = new Fade();
        getWindow().setReturnTransition(returnTrans);

        relativeLayout = findViewById(R.id.relativeLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();

        // setting enter fade animation duration to 3 seconds
        animationDrawable.setEnterFadeDuration(3000);

        // setting exit fade animation duration to 3 seconds
        animationDrawable.setExitFadeDuration(3000);

        transitionButton = findViewById(R.id.proceedButton);

        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(landing_Screen.this);
                Intent intent = new Intent(landing_Screen.this, MainActivity.class);
                startActivity(new Intent(landing_Screen.this, MainActivity.class));
            }
        });
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
