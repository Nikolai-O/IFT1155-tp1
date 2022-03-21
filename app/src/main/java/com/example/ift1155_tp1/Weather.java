package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        TextView currentTV = findViewById(R.id.currentTV);
        TextView currentCondition = findViewById(R.id.currentCondTV);
        TextView windDirectionTV = findViewById(R.id.windDirection);
        TextView windSpeedTV = findViewById(R.id.windSpeed);
        TextView summaryTV = findViewById(R.id.summaryTV);
        TextView tmrTV = findViewById(R.id.tmrTV);
        TextView tmrSummaryTV = findViewById(R.id.tmrSummaryTV);
        TextView tmrTempTV = findViewById(R.id.tmrTempTV);
        TextView afterTmrTV = findViewById(R.id.afterTmrTV);
        TextView afterTmrSummaryTV = findViewById(R.id.afterTmrSummaryTV);
        TextView afterTmrTempTV = findViewById(R.id.afterTmrTempTV);

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
        String afterTmrSummary = ville.getStringExtra("afterTmrSummary");
        String afterTmrTemp = ville.getStringExtra("afterTmrTemp");

        currentTV.setText(temperature + currentTV.getText());
        currentCondition.setText(condition);
        windDirectionTV.setText(windDirection);
        windSpeedTV.setText(windSpeed + windSpeedTV.getText());
        summaryTV.setText(summary);
        tmrTV.setText(tmr);
        tmrSummaryTV.setText(tmrSummary);
        tmrTempTV.setText(tmrTemp + tmrTempTV.getText());
        afterTmrTV.setText(afterTmr);
        afterTmrSummaryTV.setText(afterTmrSummary);
        afterTmrTempTV.setText(afterTmrTemp + afterTmrTempTV.getText());

        //Toast.makeText(this, afterTmrSummary, Toast.LENGTH_LONG).show();

//        currentTV.setText(tv.getText() + " " + temperature  + "\n" + condition  + "\n" + windSpeed
//                + "\n" + windDirection  + "\n" + summary  + "\n" + tmr  + "\n" + tmrTemp
//                + "\n" + tmrSummary  + "\n" + afterTmr  + "\n" + afterTmrSummary  + "\n" + afterTmrTemp);
    }

    public void back (View v) {
        Intent intent = new Intent(this, Cities.class);
        startActivity(intent);
    }
}
