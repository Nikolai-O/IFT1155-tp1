package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
    }

    public void back (View v) {
        Intent intent = new Intent(this, Cities.class);
        startActivity(intent);
    }
}
