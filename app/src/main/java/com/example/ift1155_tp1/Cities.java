package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Cities extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner listeVilles;
    List villes;

    private StringBuffer text = null;
    static String text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);

        listeVilles = findViewById(R.id.spinner);

        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("City/Ville");
        villes = getVilles("qc.xml");
        listeVilles.setPrompt("select city");
        for (int i = 0; i < villes.size(); i++) {
            IdXmlParser.Entry ville = (IdXmlParser.Entry) villes.get(i);
            spinnerArray.add(ville.nameFr);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listeVilles.setAdapter(adapter);
        int initialposition=listeVilles.getSelectedItemPosition();
        listeVilles.setSelection(initialposition, false);
        listeVilles.setOnItemSelectedListener(this);
        IdXmlParser.Entry ville = (IdXmlParser.Entry) villes.get(0);
        for (int i = 0 ; i < spinnerArray.size(); i++) {
            String j = spinnerArray.get(i);
            Log.i("ingo", j);
        }
        Log.i("info", String.valueOf(ville.nameFr));
    }



        public List getVilles(String file) {
        IdXmlParser parser = new IdXmlParser();
        InputStream is = null;
        List entries = null;
        try {
            is = openFileInput(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            entries = parser.parse(is);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int choix = (int) listeVilles.getSelectedItemId();
        IdXmlParser.Entry select = (IdXmlParser.Entry) villes.get(choix - 1);
        String url = "https://dd.weather.gc.ca/citypage_weather/xml/QC/" + select.code + "_f.xml";
        downloadXML(url);
        Log.i("Spinner", url);
        //Toast.makeText(this, (CharSequence) listeVilles.getSelectedItem(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Weather.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void downloadXML(String site) {
        final String url = site;
        new Thread(() -> {
            int BUFFER_SIZE = 2000;
            InputStream in;
            Message msg = Message.obtain();
            msg.what = 1;
            try {
                Log.i("info", "check");
                in = openHttpConnection(site);

                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                int charRead;
                text = new StringBuffer();
                char[] inputBuffer = new char[BUFFER_SIZE];

                while ((charRead = isr.read(inputBuffer)) > 0) {
                    String readString =
                            String.copyValueOf(inputBuffer, 0, charRead);
                    text.append(readString);
                    inputBuffer = new char[BUFFER_SIZE];

                }
                Bundle b = new Bundle();
                b.putString("text", text.toString());
                msg.setData(b);
                in.close();
                messageHandlerCities.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode;

        URL url = null;

        try {
            url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException ("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection)urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            Log.i("httpConnTest", String.valueOf(10));
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            Log.i("httpConnTest", String.valueOf(resCode));

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    class CustomHandler extends Handler {

        private final Cities activity;

        CustomHandler(Cities activity) {
            super(Looper.myLooper());
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    text2 = msg.getData().getString("text");
                    Document doc = convertStringToXMLDocument(text2);
                    NodeList names = doc.getElementsByTagName("nameEn");
                    //villes = getVilles("cities.xml");
//                    try {
//                        OutputStreamWriter out = new OutputStreamWriter(
//                                openFileOutput("cities.xml", 0 ), StandardCharsets.UTF_8);
//                        Log.i("encoding", out.getEncoding());
//                        out.write(text2);
//                        out.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    WeatherXmlParser parser = new WeatherXmlParser();
                    InputStream is = null;
                    try {
                        is = openFileInput(text2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        List entries = parser.parse(is);
                        //villes = parser.parse(is);
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
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }

    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
//            byte[] frenchBytes = xmlString.getBytes();
//            String xmlFrenchString = new String(frenchBytes, StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(xmlString);
            String text1 = new String(xmlString.getBytes(Charset.forName("UTF-8")), Charset.forName("UTF-8"));
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(text1)));
            //Log.i("encoding", doc.getXmlEncoding());
            //Log.i("encoding", doc.getInputEncoding());
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private final Handler messageHandlerCities = new CustomHandler(this);
}
