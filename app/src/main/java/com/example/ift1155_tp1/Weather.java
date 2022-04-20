package com.example.ift1155_tp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity {
    String nomProvince;
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
        nomProvince = ville.getStringExtra("Province");
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
        String tmr3 = ville.getStringExtra("tmr3");
        String tmrSummary3 = ville.getStringExtra("tmrSummary3");
        String tmrTemp3 = ville.getStringExtra("tmrTemp3");
        String tmr4 = ville.getStringExtra("tmr4");
        String tmrSummary4 = ville.getStringExtra("tmrSummary4");
        String tmrTemp4 = ville.getStringExtra("tmrTemp4");

        String windSpeedTxt = (String) windSpeedTV.getText();



        String temperatureFR = ville.getStringExtra("currentTemperatureFR");
        String conditionFR = ville.getStringExtra("currentConditionFR");
        String windSpeedFR = ville.getStringExtra("windSpeedFR");
        String windDirectionFR = ville.getStringExtra("windDirectionFR");
        String summaryFR = ville.getStringExtra("summaryFR");
        String tmrFR = ville.getStringExtra("tmrFR");
        String tmrSummaryFR = ville.getStringExtra("tmrSummaryFR");
        String tmrTempFR = ville.getStringExtra("tmrTempFR");
        String afterTmrFR = ville.getStringExtra("afterTmrFR");
        String afterTmrSummaryFR = ville.getStringExtra("afterTmrSummaryFR");
        String afterTmrTempFR = ville.getStringExtra("afterTmrTempFR");
        String tmr3FR = ville.getStringExtra("tmr3FR");
        String tmrSummary3FR = ville.getStringExtra("tmrSummary3FR");
        String tmrTemp3FR = ville.getStringExtra("tmrTemp3FR");
        String tmr4FR = ville.getStringExtra("tmr4FR");
        String tmrSummary4FR = ville.getStringExtra("tmrSummary4FR");
        String tmrTemp4FR = ville.getStringExtra("tmrTemp4FR");

        if ( (getResources().getConfiguration().getLocales().get(0)).toString().contains("fr")) {
            currentTV.setText(temperature + currentTV.getText());
            currentCondition.setText(condition);
            windDirectionTV.setText(windDirection);
            windSpeedTV.setText(windSpeed + windSpeedTxt);
            summaryTV.setText(summary);
            tmrTV.setText(tmr);
            tmrSummaryTV.setText(tmrSummary);
            tmrTempTV.setText(tmrTemp + tmrTempTV.getText());
            afterTmrTV.setText(afterTmr);
            afterTmrSummaryTV.setText(afterTmrSummary);
            afterTmrTempTV.setText(afterTmrTemp + afterTmrTempTV.getText());
            if (isTablet(this)) {
                TextView tmrTV3 = findViewById(R.id.tmrTV3);
                TextView tmrSummaryTV3 = findViewById(R.id.tmrSummaryTV3);
                TextView tmrTempTV3 = findViewById(R.id.tmrTempTV3);
                TextView tmrTV4 = findViewById(R.id.tmrTV4);
                TextView tmrSummaryTV4 = findViewById(R.id.tmrSummaryTV4);
                TextView tmrTempTV4 = findViewById(R.id.tmrTempTV4);

                tmrTV3.setText(tmr3);
                tmrSummaryTV3.setText(tmrSummary3);
                tmrTempTV3.setText(tmrTemp3 + tmrTempTV3.getText());
                tmrTV4.setText(tmr4);
                tmrSummaryTV4.setText(tmrSummary4);
                tmrTempTV4.setText(tmrTemp4 + tmrTempTV4.getText());
            }
        }

        else {
            currentTV.setText(temperatureFR);
            currentCondition.setText(conditionFR);
            windDirectionTV.setText(windDirectionFR);
            windSpeedTV.setText(windSpeedFR + windSpeedTxt);
            summaryTV.setText(summaryFR);
            tmrTV.setText(tmrFR);
            tmrSummaryTV.setText(tmrSummaryFR);
            tmrTempTV.setText(tmrTempFR + tmrTempTV.getText());
            afterTmrTV.setText(afterTmrFR);
            afterTmrSummaryTV.setText(afterTmrSummaryFR);
            afterTmrTempTV.setText(afterTmrTempFR + afterTmrTempTV.getText());
            if (isTablet(this)) {
                TextView tmrTV3 = findViewById(R.id.tmrTV3);
                TextView tmrSummaryTV3 = findViewById(R.id.tmrSummaryTV3);
                TextView tmrTempTV3 = findViewById(R.id.tmrTempTV3);
                TextView tmrTV4 = findViewById(R.id.tmrTV4);
                TextView tmrSummaryTV4 = findViewById(R.id.tmrSummaryTV4);
                TextView tmrTempTV4 = findViewById(R.id.tmrTempTV4);

                tmrTV3.setText(tmr3FR);
                tmrSummaryTV3.setText(tmrSummary3FR);
                tmrTempTV3.setText(tmrTemp3FR + tmrTempTV3.getText());
                tmrTV4.setText(tmr4FR);
                tmrSummaryTV4.setText(tmrSummary4FR);
                tmrTempTV4.setText(tmrTemp4FR + tmrTempTV4.getText());
            }
        }
    }

    public void back (View v) {
        Intent intent = new Intent(this, Cities.class);
        intent.putExtra("Province", nomProvince);
        startActivity(intent);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Province", nomProvince);
        super.onBackPressed();
    }
}
