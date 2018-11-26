package com.example.maestro.myfirstapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PulsatorLayout pulsator = findViewById(R.id.pulsator);
        pulsator.start();
    }
}
