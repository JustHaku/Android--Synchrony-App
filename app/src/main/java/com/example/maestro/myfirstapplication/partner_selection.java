package com.example.maestro.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class partner_selection extends Activity {

    ImageButton returnButton;
    ImageButton proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_selection);

        returnButton = findViewById(R.id.returnButton);
        proceedButton = findViewById(R.id.proceedButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                startActivity(new Intent(partner_selection.this, landing_Screen.class));
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                startActivity(new Intent(partner_selection.this, MainActivity.class));
            }
        });
    }

}
