package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

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

    public void weather (View v) throws IOException, XmlPullParserException {
        Button gaspe = findViewById(R.id.gaspeButton);
        Button madeleine = findViewById(R.id.madeleineButton);
        Button montreal = findViewById(R.id.mtlButton);
        Button quebec = findViewById(R.id.quebecButton);
        Button salluit = findViewById(R.id.salluitButton);

        if ( v == gaspe ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.gaspe);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            Log.i("Weather", "Gaspé");
            Log.i("Weather", "Current Conditions: " + weather.currentCondition);
            Log.i("Weather", "Current temperature: " + weather.currentTemperature);
            Log.i("Weather", "Current wind speed: " + weather.windSpeed);
            Log.i("Weather", "Current direction: " + weather.windDirection);
            Log.i("Weather", "Current summary: " + weather.summary);
            Log.i("Weather", weather.tmr + ": " + weather.tmrSummary + " Temperature: " + weather.tmrTemp);
            Log.i("Weather", weather.afterTmr + ": " + weather.afterTmrSummary + " Temperature: " + weather.afterTmrTemp);
            Log.i("Weather", "");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature",  weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            intent.putExtra("windSpeed", weather.windSpeed);
            intent.putExtra("windDirection", weather.windDirection);
            intent.putExtra("summary", weather.summary);
            intent.putExtra("tmr", weather.tmr);
            intent.putExtra("tmrSummary", weather.tmrSummary);
            intent.putExtra("tmrTemp", weather.tmrTemp);
            intent.putExtra("afterTmr", weather.afterTmr);
            intent.putExtra("afterTmrSummary", weather.afterTmrSummary);
            intent.putExtra("afterTmrTemp", weather.afterTmrTemp);
            startActivity(intent);
        }

        else if ( v == madeleine ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.madeleine);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            Log.i("Weather", "Iles de la Madeleine");
            Log.i("Weather", "Current Conditions: " + weather.currentCondition);
            Log.i("Weather", "Current temperature: " + weather.currentTemperature);
            Log.i("Weather", "Current wind speed: " + weather.windSpeed);
            Log.i("Weather", "Current direction: " + weather.windDirection);
            Log.i("Weather", "Current summary: " + weather.summary);
            Log.i("Weather", weather.tmr + ": " + weather.tmrSummary + " Temperature: " + weather.tmrTemp);
            Log.i("Weather", weather.afterTmr + ": " + weather.afterTmrSummary + " Temperature: " + weather.afterTmrTemp);
            Log.i("Weather", "");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature",  weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            intent.putExtra("windSpeed", weather.windSpeed);
            intent.putExtra("windDirection", weather.windDirection);
            intent.putExtra("summary", weather.summary);
            intent.putExtra("tmr", weather.tmr);
            intent.putExtra("tmrSummary", weather.tmrSummary);
            intent.putExtra("tmrTemp", weather.tmrTemp);
            intent.putExtra("afterTmr", weather.afterTmr);
            intent.putExtra("afterTmrSummary", weather.afterTmrSummary);
            intent.putExtra("afterTmrTemp", weather.afterTmrTemp);
            startActivity(intent);
        }

        else if ( v == montreal ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.montreal);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            Log.i("Weather", "Montréal");
            Log.i("Weather", "Current Conditions: " + weather.currentCondition);
            Log.i("Weather", "Current temperature: " + weather.currentTemperature);
            Log.i("Weather", "Current wind speed: " + weather.windSpeed);
            Log.i("Weather", "Current direction: " + weather.windDirection);
            Log.i("Weather", "Current summary: " + weather.summary);
            Log.i("Weather", weather.tmr + ": " + weather.tmrSummary + " Temperature: " + weather.tmrTemp);
            Log.i("Weather", weather.afterTmr + ": " + weather.afterTmrSummary + " Temperature: " + weather.afterTmrTemp);
            Log.i("Weather", "");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature",  weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            intent.putExtra("windSpeed", weather.windSpeed);
            intent.putExtra("windDirection", weather.windDirection);
            intent.putExtra("summary", weather.summary);
            intent.putExtra("tmr", weather.tmr);
            intent.putExtra("tmrSummary", weather.tmrSummary);
            intent.putExtra("tmrTemp", weather.tmrTemp);
            intent.putExtra("afterTmr", weather.afterTmr);
            intent.putExtra("afterTmrSummary", weather.afterTmrSummary);
            intent.putExtra("afterTmrTemp", weather.afterTmrTemp);
            startActivity(intent);
        }

        else if ( v == quebec ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.quebec);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            Log.i("Weather", "Québec");
            Log.i("Weather", "Current Conditions: " + weather.currentCondition);
            Log.i("Weather", "Current temperature: " + weather.currentTemperature);
            Log.i("Weather", "Current wind speed: " + weather.windSpeed);
            Log.i("Weather", "Current direction: " + weather.windDirection);
            Log.i("Weather", "Current summary: " + weather.summary);
            Log.i("Weather", weather.tmr + ": " + weather.tmrSummary + " Temperature: " + weather.tmrTemp);
            Log.i("Weather", weather.afterTmr + ": " + weather.afterTmrSummary + " Temperature: " + weather.afterTmrTemp);
            Log.i("Weather", "");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature",  weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            intent.putExtra("windSpeed", weather.windSpeed);
            intent.putExtra("windDirection", weather.windDirection);
            intent.putExtra("summary", weather.summary);
            intent.putExtra("tmr", weather.tmr);
            intent.putExtra("tmrSummary", weather.tmrSummary);
            intent.putExtra("tmrTemp", weather.tmrTemp);
            intent.putExtra("afterTmr", weather.afterTmr);
            intent.putExtra("afterTmrSummary", weather.afterTmrSummary);
            intent.putExtra("afterTmrTemp", weather.afterTmrTemp);
            startActivity(intent);
        }

        else if ( v == salluit ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.salluit);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            Log.i("Weather", "Salluit");
            Log.i("Weather", "Current Conditions: " + weather.currentCondition);
            Log.i("Weather", "Current temperature: " + weather.currentTemperature);
            Log.i("Weather", "Current wind speed: " + weather.windSpeed);
            Log.i("Weather", "Current direction: " + weather.windDirection);
            Log.i("Weather", "Current summary: " + weather.summary);
            Log.i("Weather", weather.tmr + ": " + weather.tmrSummary + " Temperature: " + weather.tmrTemp);
            Log.i("Weather", weather.afterTmr + ": " + weather.afterTmrSummary + " Temperature: " + weather.afterTmrTemp);
            Log.i("Weather", "");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature",  weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            intent.putExtra("windSpeed", weather.windSpeed);
            intent.putExtra("windDirection", weather.windDirection);
            intent.putExtra("summary", weather.summary);
            intent.putExtra("tmr", weather.tmr);
            intent.putExtra("tmrSummary", weather.tmrSummary);
            intent.putExtra("tmrTemp", weather.tmrTemp);
            intent.putExtra("afterTmr", weather.afterTmr);
            intent.putExtra("afterTmrSummary", weather.afterTmrSummary);
            intent.putExtra("afterTmrTemp", weather.afterTmrTemp);
            startActivity(intent);
        }
    }
}
