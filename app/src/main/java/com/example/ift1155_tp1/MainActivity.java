package com.example.ift1155_tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v) {
        Button helpBtn = findViewById(R.id.helpButton);
        Button aboutBtn = findViewById(R.id.aboutButton);

        if ( v == helpBtn) {
            Intent intent = new Intent(this, Help.class);
            startActivity(intent);
        }

        else if ( v == aboutBtn) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        else {
            Intent intent = new Intent(this, Provinces.class);
            startActivity(intent);
        }
    }
}