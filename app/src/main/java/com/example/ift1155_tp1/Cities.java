package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Cities extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);
    }

    public void back (View v) {
        Intent intent = new Intent(this, Provinces.class);
        startActivity(intent);
    }

    public void weather (View v) {
        Button gaspe = findViewById(R.id.gaspeButton);
        Button madeleine = findViewById(R.id.madeleineButton);
        Button montreal = findViewById(R.id.mtlButton);
        Button quebec = findViewById(R.id.quebecButton);
        Button salluit = findViewById(R.id.salluitButton);

        if ( v == gaspe ) {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        }

        else if ( v == madeleine ) {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        }

        else if ( v == montreal ) {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        }

        else if ( v == quebec ) {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        }

        else if ( v == salluit ) {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        }
    }
}
