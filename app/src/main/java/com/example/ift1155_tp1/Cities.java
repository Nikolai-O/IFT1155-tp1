package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
            Log.i("Weather", weather.tmr3 + ": " + weather.tmrSummary3 + " Temperature: " + weather.tmrTemp3);
            Log.i("Weather", weather.tmr4 + ": " + weather.tmrSummary4 + " Temperature: " + weather.tmrTemp4);
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
            intent.putExtra("tmr3", weather.tmr3);
            intent.putExtra("tmrSummary3", weather.tmrSummary3);
            intent.putExtra("tmrTemp3", weather.tmrTemp3);
            intent.putExtra("tmr4", weather.tmr4);
            intent.putExtra("tmrSummary4", weather.tmrSummary4);
            intent.putExtra("tmrTemp4", weather.tmrTemp4);

            WeatherXmlParser parserFR = new WeatherXmlParser();
            InputStream isFR = getResources().openRawResource(R.raw.gaspe_fr);
            WeatherXmlParser.Entry weatherFR = (WeatherXmlParser.Entry) parserFR.parse(isFR).get(0);
            Log.i("WeatherFR", "Gaspé");
            Log.i("WeatherFR", "Current Conditions: " + weatherFR.currentCondition);
            Log.i("WeatherFR", "Current temperature: " + weatherFR.currentTemperature);
            Log.i("WeatherFR", "Current wind speed: " + weatherFR.windSpeed);
            Log.i("WeatherFR", "Current direction: " + weatherFR.windDirection);
            Log.i("WeatherFR", "Current summary: " + weatherFR.summary);
            Log.i("WeatherFR", weatherFR.tmr + ": " + weatherFR.tmrSummary + " Temperature: " + weatherFR.tmrTemp);
            Log.i("WeatherFR", weatherFR.afterTmr + ": " + weatherFR.afterTmrSummary + " Temperature: " + weatherFR.afterTmrTemp);
            Log.i("WeatherFR", weatherFR.tmr3 + ": " + weatherFR.tmrSummary3 + " Temperature: " + weatherFR.tmrTemp3);
            Log.i("WeatherFR", weatherFR.tmr4 + ": " + weatherFR.tmrSummary4 + " Temperature: " + weatherFR.tmrTemp4);
            Log.i("WeatherFR", "");
            intent.putExtra("currentTemperatureFR",  weatherFR.currentTemperature);
            intent.putExtra("currentConditionFR", weatherFR.currentCondition);
            intent.putExtra("windSpeedFR", weatherFR.windSpeed);
            intent.putExtra("windDirectionFR", weatherFR.windDirection);
            intent.putExtra("summaryFR", weatherFR.summary);
            intent.putExtra("tmrFR", weatherFR.tmr);
            intent.putExtra("tmrSummaryFR", weatherFR.tmrSummary);
            intent.putExtra("tmrTempFR", weatherFR.tmrTemp);
            intent.putExtra("afterTmrFR", weatherFR.afterTmr);
            intent.putExtra("afterTmrSummaryFR", weatherFR.afterTmrSummary);
            intent.putExtra("afterTmrTempFR", weatherFR.afterTmrTemp);
            intent.putExtra("tmr3FR", weatherFR.tmr3);
            intent.putExtra("tmrSummary3FR", weatherFR.tmrSummary3);
            intent.putExtra("tmrTemp3FR", weatherFR.tmrTemp3);
            intent.putExtra("tmr4FR", weatherFR.tmr4);
            intent.putExtra("tmrSummary4FR", weatherFR.tmrSummary4);
            intent.putExtra("tmrTemp4FR", weatherFR.tmrTemp4);

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
            Log.i("Weather", weather.tmr3 + ": " + weather.tmrSummary3 + " Temperature: " + weather.tmrTemp3);
            Log.i("Weather", weather.tmr4 + ": " + weather.tmrSummary4 + " Temperature: " + weather.tmrTemp4);
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
            intent.putExtra("tmr3", weather.tmr3);
            intent.putExtra("tmrSummary3", weather.tmrSummary3);
            intent.putExtra("tmrTemp3", weather.tmrTemp3);
            intent.putExtra("tmr4", weather.tmr4);
            intent.putExtra("tmrSummary4", weather.tmrSummary4);
            intent.putExtra("tmrTemp4", weather.tmrTemp4);

            WeatherXmlParser parserFR = new WeatherXmlParser();
            InputStream isFR = getResources().openRawResource(R.raw.madeleine_fr);
            WeatherXmlParser.Entry weatherFR = (WeatherXmlParser.Entry) parserFR.parse(isFR).get(0);
            Log.i("WeatherFR", "Îles de la Madeleine");
            Log.i("WeatherFR", "Current Conditions: " + weatherFR.currentCondition);
            Log.i("WeatherFR", "Current temperature: " + weatherFR.currentTemperature);
            Log.i("WeatherFR", "Current wind speed: " + weatherFR.windSpeed);
            Log.i("WeatherFR", "Current direction: " + weatherFR.windDirection);
            Log.i("WeatherFR", "Current summary: " + weatherFR.summary);
            Log.i("WeatherFR", weatherFR.tmr + ": " + weatherFR.tmrSummary + " Temperature: " + weatherFR.tmrTemp);
            Log.i("WeatherFR", weatherFR.afterTmr + ": " + weatherFR.afterTmrSummary + " Temperature: " + weatherFR.afterTmrTemp);
            Log.i("WeatherFR", weatherFR.tmr3 + ": " + weatherFR.tmrSummary3 + " Temperature: " + weatherFR.tmrTemp3);
            Log.i("WeatherFR", weatherFR.tmr4 + ": " + weatherFR.tmrSummary4 + " Temperature: " + weatherFR.tmrTemp4);
            Log.i("WeatherFR", "");
            intent.putExtra("currentTemperatureFR",  weatherFR.currentTemperature);
            intent.putExtra("currentConditionFR", weatherFR.currentCondition);
            intent.putExtra("windSpeedFR", weatherFR.windSpeed);
            intent.putExtra("windDirectionFR", weatherFR.windDirection);
            intent.putExtra("summaryFR", weatherFR.summary);
            intent.putExtra("tmrFR", weatherFR.tmr);
            intent.putExtra("tmrSummaryFR", weatherFR.tmrSummary);
            intent.putExtra("tmrTempFR", weatherFR.tmrTemp);
            intent.putExtra("afterTmrFR", weatherFR.afterTmr);
            intent.putExtra("afterTmrSummaryFR", weatherFR.afterTmrSummary);
            intent.putExtra("afterTmrTempFR", weatherFR.afterTmrTemp);
            intent.putExtra("tmr3FR", weatherFR.tmr3);
            intent.putExtra("tmrSummary3FR", weatherFR.tmrSummary3);
            intent.putExtra("tmrTemp3FR", weatherFR.tmrTemp3);
            intent.putExtra("tmr4FR", weatherFR.tmr4);
            intent.putExtra("tmrSummary4FR", weatherFR.tmrSummary4);
            intent.putExtra("tmrTemp4FR", weatherFR.tmrTemp4);

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
            Log.i("Weather", weather.tmr3 + ": " + weather.tmrSummary3 + " Temperature: " + weather.tmrTemp3);
            Log.i("Weather", weather.tmr4 + ": " + weather.tmrSummary4 + " Temperature: " + weather.tmrTemp4);
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
            intent.putExtra("tmr3", weather.tmr3);
            intent.putExtra("tmrSummary3", weather.tmrSummary3);
            intent.putExtra("tmrTemp3", weather.tmrTemp3);
            intent.putExtra("tmr4", weather.tmr4);
            intent.putExtra("tmrSummary4", weather.tmrSummary4);
            intent.putExtra("tmrTemp4", weather.tmrTemp4);

            WeatherXmlParser parserFR = new WeatherXmlParser();
            InputStream isFR = getResources().openRawResource(R.raw.montreal_fr);
            WeatherXmlParser.Entry weatherFR = (WeatherXmlParser.Entry) parserFR.parse(isFR).get(0);
            Log.i("WeatherFR", "Montréal");
            Log.i("WeatherFR", "Current Conditions: " + weatherFR.currentCondition);
            Log.i("WeatherFR", "Current temperature: " + weatherFR.currentTemperature);
            Log.i("WeatherFR", "Current wind speed: " + weatherFR.windSpeed);
            Log.i("WeatherFR", "Current direction: " + weatherFR.windDirection);
            Log.i("WeatherFR", "Current summary: " + weatherFR.summary);
            Log.i("WeatherFR", weatherFR.tmr + ": " + weatherFR.tmrSummary + " Temperature: " + weatherFR.tmrTemp);
            Log.i("WeatherFR", weatherFR.afterTmr + ": " + weatherFR.afterTmrSummary + " Temperature: " + weatherFR.afterTmrTemp);
            Log.i("WeatherFR", weatherFR.tmr3 + ": " + weatherFR.tmrSummary3 + " Temperature: " + weatherFR.tmrTemp3);
            Log.i("WeatherFR", weatherFR.tmr4 + ": " + weatherFR.tmrSummary4 + " Temperature: " + weatherFR.tmrTemp4);
            Log.i("WeatherFR", "");
            intent.putExtra("currentTemperatureFR",  weatherFR.currentTemperature);
            intent.putExtra("currentConditionFR", weatherFR.currentCondition);
            intent.putExtra("windSpeedFR", weatherFR.windSpeed);
            intent.putExtra("windDirectionFR", weatherFR.windDirection);
            intent.putExtra("summaryFR", weatherFR.summary);
            intent.putExtra("tmrFR", weatherFR.tmr);
            intent.putExtra("tmrSummaryFR", weatherFR.tmrSummary);
            intent.putExtra("tmrTempFR", weatherFR.tmrTemp);
            intent.putExtra("afterTmrFR", weatherFR.afterTmr);
            intent.putExtra("afterTmrSummaryFR", weatherFR.afterTmrSummary);
            intent.putExtra("afterTmrTempFR", weatherFR.afterTmrTemp);
            intent.putExtra("tmr3FR", weatherFR.tmr3);
            intent.putExtra("tmrSummary3FR", weatherFR.tmrSummary3);
            intent.putExtra("tmrTemp3FR", weatherFR.tmrTemp3);
            intent.putExtra("tmr4FR", weatherFR.tmr4);
            intent.putExtra("tmrSummary4FR", weatherFR.tmrSummary4);
            intent.putExtra("tmrTemp4FR", weatherFR.tmrTemp4);


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
            Log.i("Weather", weather.tmr3 + ": " + weather.tmrSummary3 + " Temperature: " + weather.tmrTemp3);
            Log.i("Weather", weather.tmr4 + ": " + weather.tmrSummary4 + " Temperature: " + weather.tmrTemp4);
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
            intent.putExtra("tmr3", weather.tmr3);
            intent.putExtra("tmrSummary3", weather.tmrSummary3);
            intent.putExtra("tmrTemp3", weather.tmrTemp3);
            intent.putExtra("tmr4", weather.tmr4);
            intent.putExtra("tmrSummary4", weather.tmrSummary4);
            intent.putExtra("tmrTemp4", weather.tmrTemp4);

            WeatherXmlParser parserFR = new WeatherXmlParser();
            InputStream isFR = getResources().openRawResource(R.raw.quebec_fr);
            WeatherXmlParser.Entry weatherFR = (WeatherXmlParser.Entry) parserFR.parse(isFR).get(0);
            Log.i("WeatherFR", "Québec");
            Log.i("WeatherFR", "Current Conditions: " + weatherFR.currentCondition);
            Log.i("WeatherFR", "Current temperature: " + weatherFR.currentTemperature);
            Log.i("WeatherFR", "Current wind speed: " + weatherFR.windSpeed);
            Log.i("WeatherFR", "Current direction: " + weatherFR.windDirection);
            Log.i("WeatherFR", "Current summary: " + weatherFR.summary);
            Log.i("WeatherFR", weatherFR.tmr + ": " + weatherFR.tmrSummary + " Temperature: " + weatherFR.tmrTemp);
            Log.i("WeatherFR", weatherFR.afterTmr + ": " + weatherFR.afterTmrSummary + " Temperature: " + weatherFR.afterTmrTemp);
            Log.i("WeatherFR", weatherFR.tmr3 + ": " + weatherFR.tmrSummary3 + " Temperature: " + weatherFR.tmrTemp3);
            Log.i("WeatherFR", weatherFR.tmr4 + ": " + weatherFR.tmrSummary4 + " Temperature: " + weatherFR.tmrTemp4);
            Log.i("WeatherFR", "");
            intent.putExtra("currentTemperatureFR",  weatherFR.currentTemperature);
            intent.putExtra("currentConditionFR", weatherFR.currentCondition);
            intent.putExtra("windSpeedFR", weatherFR.windSpeed);
            intent.putExtra("windDirectionFR", weatherFR.windDirection);
            intent.putExtra("summaryFR", weatherFR.summary);
            intent.putExtra("tmrFR", weatherFR.tmr);
            intent.putExtra("tmrSummaryFR", weatherFR.tmrSummary);
            intent.putExtra("tmrTempFR", weatherFR.tmrTemp);
            intent.putExtra("afterTmrFR", weatherFR.afterTmr);
            intent.putExtra("afterTmrSummaryFR", weatherFR.afterTmrSummary);
            intent.putExtra("afterTmrTempFR", weatherFR.afterTmrTemp);
            intent.putExtra("tmr3FR", weatherFR.tmr3);
            intent.putExtra("tmrSummary3FR", weatherFR.tmrSummary3);
            intent.putExtra("tmrTemp3FR", weatherFR.tmrTemp3);
            intent.putExtra("tmr4FR", weatherFR.tmr4);
            intent.putExtra("tmrSummary4FR", weatherFR.tmrSummary4);
            intent.putExtra("tmrTemp4FR", weatherFR.tmrTemp4);

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
            Log.i("Weather", weather.tmr3 + ": " + weather.tmrSummary3 + " Temperature: " + weather.tmrTemp3);
            Log.i("Weather", weather.tmr4 + ": " + weather.tmrSummary4 + " Temperature: " + weather.tmrTemp4);
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
            intent.putExtra("tmr3", weather.tmr3);
            intent.putExtra("tmrSummary3", weather.tmrSummary3);
            intent.putExtra("tmrTemp3", weather.tmrTemp3);
            intent.putExtra("tmr4", weather.tmr4);
            intent.putExtra("tmrSummary4", weather.tmrSummary4);
            intent.putExtra("tmrTemp4", weather.tmrTemp4);

            WeatherXmlParser parserFR = new WeatherXmlParser();
            InputStream isFR = getResources().openRawResource(R.raw.salluit_fr);
            WeatherXmlParser.Entry weatherFR = (WeatherXmlParser.Entry) parserFR.parse(isFR).get(0);
            Log.i("WeatherFR", "Salluit");
            Log.i("WeatherFR", "Current Conditions: " + weatherFR.currentCondition);
            Log.i("WeatherFR", "Current temperature: " + weatherFR.currentTemperature);
            Log.i("WeatherFR", "Current wind speed: " + weatherFR.windSpeed);
            Log.i("WeatherFR", "Current direction: " + weatherFR.windDirection);
            Log.i("WeatherFR", "Current summary: " + weatherFR.summary);
            Log.i("WeatherFR", weatherFR.tmr + ": " + weatherFR.tmrSummary + " Temperature: " + weatherFR.tmrTemp);
            Log.i("WeatherFR", weatherFR.afterTmr + ": " + weatherFR.afterTmrSummary + " Temperature: " + weatherFR.afterTmrTemp);
            Log.i("WeatherFR", weatherFR.tmr3 + ": " + weatherFR.tmrSummary3 + " Temperature: " + weatherFR.tmrTemp3);
            Log.i("WeatherFR", weatherFR.tmr4 + ": " + weatherFR.tmrSummary4 + " Temperature: " + weatherFR.tmrTemp4);
            Log.i("WeatherFR", "");
            intent.putExtra("currentTemperatureFR",  weatherFR.currentTemperature);
            intent.putExtra("currentConditionFR", weatherFR.currentCondition);
            intent.putExtra("windSpeedFR", weatherFR.windSpeed);
            intent.putExtra("windDirectionFR", weatherFR.windDirection);
            intent.putExtra("summaryFR", weatherFR.summary);
            intent.putExtra("tmrFR", weatherFR.tmr);
            intent.putExtra("tmrSummaryFR", weatherFR.tmrSummary);
            intent.putExtra("tmrTempFR", weatherFR.tmrTemp);
            intent.putExtra("afterTmrFR", weatherFR.afterTmr);
            intent.putExtra("afterTmrSummaryFR", weatherFR.afterTmrSummary);
            intent.putExtra("afterTmrTempFR", weatherFR.afterTmrTemp);
            intent.putExtra("tmr3FR", weatherFR.tmr3);
            intent.putExtra("tmrSummary3FR", weatherFR.tmrSummary3);
            intent.putExtra("tmrTemp3FR", weatherFR.tmrTemp3);
            intent.putExtra("tmr4FR", weatherFR.tmr4);
            intent.putExtra("tmrSummary4FR", weatherFR.tmrSummary4);
            intent.putExtra("tmrTemp4FR", weatherFR.tmrTemp4);

            startActivity(intent);
        }
    }
}
