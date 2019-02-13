package com.example.maestro.myfirstapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageButton returnButton;
    ImageView user_Circle;
    ImageView partner_Circle;
    int phase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_Circle = findViewById(R.id.user_Circle);
        partner_Circle= findViewById(R.id.partner_Circle);

        returnButton = findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                startActivity(new Intent(MainActivity.this, partner_selection.class));
            }
        });

        phase = 10;

        if (phase == 10) {

            fullExpand();
            minExpand();

        }

        //phase = 1;

        if (phase == 1){

            minExpand();

        }

    }

    public MainActivity(){


    }

    public void fullExpand(){

        Animation expand = AnimationUtils.loadAnimation(this, R.anim.expand);
        user_Circle.startAnimation(expand);

    }

    public void minExpand(){

        Animation expandMin = AnimationUtils.loadAnimation(this, R.anim.expand_min);
        partner_Circle.startAnimation(expandMin);

    }

}
