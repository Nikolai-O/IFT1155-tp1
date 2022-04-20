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
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Cities extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner listeVilles;
    List villes;
    List<String> spinRetour;
    List<String> spinRetourFr;
    private StringBuffer text = null;
    static String text2;
    static String text3;
    String nomProvince;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);
        spinRetour =  new ArrayList<String>();
        spinRetourFr =  new ArrayList<String>();
        listeVilles = findViewById(R.id.spinner);
        Intent intent = getIntent();
        nomProvince = intent.getStringExtra("Province");
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
        String url = "https://dd.weather.gc.ca/citypage_weather/xml/QC/" + select.code + "_e.xml";
        String urlFr = "https://dd.weather.gc.ca/citypage_weather/xml/QC/" + select.code + "_f.xml";
        downloadXML(url);
        //downloadXMLFR(urlFr);
        Log.i("Spinner", url);
        //Toast.makeText(this, (CharSequence) listeVilles.getSelectedItem(), Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void go(View v) {
        Intent intent = new Intent(this, Weather.class);

        intent.putExtra("currentTemperature", spinRetour.get(1));
        intent.putExtra("currentCondition", spinRetour.get(0));
        intent.putExtra("windSpeed", spinRetour.get(2));
        intent.putExtra("windDirection", spinRetour.get(3));
        intent.putExtra("summary", spinRetour.get(4));
        intent.putExtra("tmr", spinRetour.get(5));
        intent.putExtra("tmrSummary", spinRetour.get(6));
        intent.putExtra("tmrTemp", spinRetour.get(7));
        intent.putExtra("afterTmr", spinRetour.get(8));
        intent.putExtra("afterTmrSummary", spinRetour.get(9));
        intent.putExtra("afterTmrTemp", spinRetour.get(10));
        intent.putExtra("tmr3", spinRetour.get(11));
        intent.putExtra("tmrSummary3", spinRetour.get(12));
        intent.putExtra("tmrTemp3", spinRetour.get(13));
        intent.putExtra("tmr4", spinRetour.get(14));
        intent.putExtra("tmrSummary4", spinRetour.get(15));
        intent.putExtra("tmrTemp4", spinRetour.get(16));

/*
        intent.putExtra("currentTemperatureFR", spinRetourFr.get(1));
        intent.putExtra("currentConditionFR", spinRetourFr.get(0));
        intent.putExtra("windSpeedFR", spinRetourFr.get(2));
        intent.putExtra("windDirectionFR", spinRetourFr.get(3));
        intent.putExtra("summaryFR", spinRetourFr.get(4));
        intent.putExtra("tmrFR", spinRetourFr.get(5));
        intent.putExtra("tmrSummaryFR", spinRetourFr.get(6));
        intent.putExtra("tmrTempFR", spinRetourFr.get(7));
        intent.putExtra("afterTmrFR", spinRetourFr.get(8));
        intent.putExtra("afterTmrSummaryFR", spinRetourFr.get(9));
        intent.putExtra("afterTmrTempFR", spinRetourFr.get(10));
        intent.putExtra("tmr3FR", spinRetourFr.get(11));
        intent.putExtra("tmrSummary3FR", spinRetourFr.get(12));
        intent.putExtra("tmrTemp3FR", spinRetourFr.get(13));
        intent.putExtra("tmr4FR", spinRetourFr.get(14));
        intent.putExtra("tmrSummary4FR", spinRetourFr.get(15));
        intent.putExtra("tmrTemp4FR", spinRetourFr.get(16));
*/
        intent.putExtra("Province", nomProvince);
        Log.i("SPINRETOUR", String.valueOf(spinRetour.size()) + "dans thread");
        startActivity(intent);
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

    private void downloadXMLFR(String site) {
        final String url = site;
        new Thread(() -> {
            int BUFFER_SIZE = 2000;
            InputStream in;
            Message msg = Message.obtain();
            msg.what = 2;
            try {
                Log.i("info", "check");
                in = openHttpConnection(site);

                InputStreamReader isrFr = new InputStreamReader(in, StandardCharsets.UTF_8);
                int charRead;
                text = new StringBuffer();
                char[] inputBuffer = new char[BUFFER_SIZE];

                while ((charRead = isrFr.read(inputBuffer)) > 0) {
                    String readString =
                            String.copyValueOf(inputBuffer, 0, charRead);
                    text.append(readString);
                    inputBuffer = new char[BUFFER_SIZE];

                }
                Bundle b = new Bundle();
                b.putString("textFr", text.toString());
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
        }public List info() {
            return spinRetour;
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
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(
                               openFileOutput("ville.xml", 0 ), StandardCharsets.UTF_8);
                        Log.i("encoding", out.getEncoding());
                        out.write(text2);
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                       e.printStackTrace();
                    }


                    InputStream isSpin = null;
                    try {
                        isSpin = openFileInput("ville.xml");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {

                        //villes = parser.parse(is);
                        WeatherXmlParser parserSpinner = new WeatherXmlParser();
                        //List entries = parserSpinner.parse(isSpin);
                        WeatherXmlParser.Entry weatherSpin = (WeatherXmlParser.Entry) parserSpinner.parse(isSpin).get(0);
                        Log.i("WeatherEn", String.valueOf(weatherSpin));
                        Log.i("WeatherEn", "Current Conditions: " + weatherSpin.currentCondition);
                        Log.i("WeatherEn", "Current temperature: " + weatherSpin.currentTemperature);
                        Log.i("WeatherEn", "Current wind speed: " + weatherSpin.windSpeed);
                        Log.i("WeatherEn", "Current direction: " + weatherSpin.windDirection);
                        Log.i("WeatherEn", "Current summary: " + weatherSpin.summary);
                        Log.i("WeatherEn", weatherSpin.tmr + ": " + weatherSpin.tmrSummary + " Temperature: " + weatherSpin.tmrTemp);
                        Log.i("WeatherEn", weatherSpin.afterTmr + ": " + weatherSpin.afterTmrSummary + " Temperature: " + weatherSpin.afterTmrTemp);
                        Log.i("WeatherEn", weatherSpin.tmr3 + ": " + weatherSpin.tmrSummary3 + " Temperature: " + weatherSpin.tmrTemp3);
                        Log.i("WeatherEn", weatherSpin.tmr4 + ": " + weatherSpin.tmrSummary4 + " Temperature: " + weatherSpin.tmrTemp4);
                        Log.i("WeatherEn", "");


                        spinRetour.add(weatherSpin.currentCondition);
                        spinRetour.add(weatherSpin.currentTemperature);
                        spinRetour.add(weatherSpin.windSpeed);
                        spinRetour.add(weatherSpin.windDirection);
                        spinRetour.add(weatherSpin.summary);
                        spinRetour.add(weatherSpin.tmr);
                        spinRetour.add(weatherSpin.tmrSummary);
                        spinRetour.add(weatherSpin.tmrTemp);
                        spinRetour.add(weatherSpin.afterTmr);
                        spinRetour.add(weatherSpin.afterTmrSummary);
                        spinRetour.add(weatherSpin.afterTmrTemp);
                        spinRetour.add(weatherSpin.tmr3);
                        spinRetour.add(weatherSpin.tmrSummary3);
                        spinRetour.add(weatherSpin.tmrTemp3);
                        spinRetour.add(weatherSpin.tmr4);
                        spinRetour.add(weatherSpin.tmrSummary4);
                        spinRetour.add(weatherSpin.tmrTemp4);

                        for (int i = 0; i < spinRetour.size(); i ++ )
                            Log.i("SPINRETOUR", spinRetour.get(i));

                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*
                case 2:
                    text3 = msg.getData().getString("textFr");

                    Document docFR = convertStringToXMLDocument(text3);
                    NodeList namesFr = docFR.getElementsByTagName("nameFr");
                    //villes = getVilles("cities.xml");
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(
                                openFileOutput("villeFr.xml", 0 ), StandardCharsets.UTF_8);
                        Log.i("encoding", out.getEncoding());
                        out.write(text3);
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    InputStream isSpinFr = null;
                    try {
                        isSpinFr = openFileInput("villeFr.xml");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {

                        WeatherXmlParser parserSpinnerFr = new WeatherXmlParser();
                        WeatherXmlParser.Entry weatherSpinFr = (WeatherXmlParser.Entry) parserSpinnerFr.parse(isSpinFr).get(0);
                        Log.i("WeatherFr", String.valueOf(weatherSpinFr));
                        Log.i("WeatherFr", "Current Conditions: " + weatherSpinFr.currentCondition);
                        Log.i("WeatherFr", "Current temperature: " + weatherSpinFr.currentTemperature);
                        Log.i("WeatherFr", "Current wind speed: " + weatherSpinFr.windSpeed);
                        Log.i("WeatherFr", "Current direction: " + weatherSpinFr.windDirection);
                        Log.i("WeatherFr", "Current summary: " + weatherSpinFr.summary);
                        Log.i("WeatherFr", weatherSpinFr.tmr + ": " + weatherSpinFr.tmrSummary + " Temperature: " + weatherSpinFr.tmrTemp);
                        Log.i("WeatherFr", weatherSpinFr.afterTmr + ": " + weatherSpinFr.afterTmrSummary + " Temperature: " + weatherSpinFr.afterTmrTemp);
                        Log.i("WeatherFr", weatherSpinFr.tmr3 + ": " + weatherSpinFr.tmrSummary3 + " Temperature: " + weatherSpinFr.tmrTemp3);
                        Log.i("WeatherFr", weatherSpinFr.tmr4 + ": " + weatherSpinFr.tmrSummary4 + " Temperature: " + weatherSpinFr.tmrTemp4);
                        Log.i("WeatherFr", "");


                        spinRetourFr.add(weatherSpinFr.currentCondition);
                        spinRetourFr.add(weatherSpinFr.currentTemperature);
                        spinRetourFr.add(weatherSpinFr.windSpeed);
                        spinRetourFr.add(weatherSpinFr.windDirection);
                        spinRetourFr.add(weatherSpinFr.summary);
                        spinRetourFr.add(weatherSpinFr.tmr);
                        spinRetourFr.add(weatherSpinFr.tmrSummary);
                        spinRetourFr.add(weatherSpinFr.tmrTemp);
                        spinRetourFr.add(weatherSpinFr.afterTmr);
                        spinRetourFr.add(weatherSpinFr.afterTmrSummary);
                        spinRetourFr.add(weatherSpinFr.afterTmrTemp);
                        spinRetourFr.add(weatherSpinFr.tmr3);
                        spinRetourFr.add(weatherSpinFr.tmrSummary3);
                        spinRetourFr.add(weatherSpinFr.tmrTemp3);
                        spinRetourFr.add(weatherSpinFr.tmr4);
                        spinRetourFr.add(weatherSpinFr.tmrSummary4);
                        spinRetourFr.add(weatherSpinFr.tmrTemp4);


                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    */
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
    //private final Handler messageHandlerCitiesFr = new CustomHandler(this);
}
