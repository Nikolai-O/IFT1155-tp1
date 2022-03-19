package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        TextView tv = findViewById(R.id.weatherText);
        Intent ville = getIntent();
        String temperature = ville.getStringExtra("currentTemperature");
        String condition = ville.getStringExtra("currentCondition");
        String windSpeed = ville.getStringExtra("windSpeed");
        String windDirection = ville.getStringExtra("windDirection");
        String summary = ville.getStringExtra("summary");
        String tmr = ville.getStringExtra("tmr");
        String tmrSummary = ville.getStringExtra("tmrSummary");
        String tmrTemp = ville.getStringExtra("tmrTemp");
        String afterTmr = ville.getStringExtra("afterTmr");
        String afterTmrSummary = ville.getStringExtra("aferTmrSummary");
        String afterTmrTemp = ville.getStringExtra("afterTmrTemp");
        tv.setText(tv.getText() + " " + temperature  + "\n" + condition  + "\n" + windSpeed
                + "\n" + windDirection  + "\n" + summary  + "\n" + tmr  + "\n" + tmrTemp
                + "\n" + tmrSummary  + "\n" + afterTmr  + "\n" + afterTmrSummary  + "\n" + afterTmrTemp);
    }

    public void back (View v) {
        Intent intent = new Intent(this, Cities.class);
        startActivity(intent);
    }
}
