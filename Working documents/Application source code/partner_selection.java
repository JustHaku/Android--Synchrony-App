package com.example.maestro.myfirstapplication;

import android.os.Bundle;
import android.app.Activity;

public class partner_selection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_selection);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
