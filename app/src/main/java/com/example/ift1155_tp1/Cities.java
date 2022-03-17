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
            // Log.i("input", "Temperature actuelle " + parser.parse(is).get(0).toString());
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature", parser.parse(is).get(0).toString());
            startActivity(intent);
        }

        else if ( v == madeleine ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.madeleine);
            // Log.i("input", "Temperature actuelle " + parser.parse(is).get(0).toString());
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature", parser.parse(is).get(0).toString());
            is.close();
            startActivity(intent);
        }

        else if ( v == montreal ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.montreal);
            WeatherXmlParser.Entry weather = (WeatherXmlParser.Entry) parser.parse(is).get(0);
            //InputStream is2 = getResources().openRawResource(R.raw.montreal);
            //String currentCondition = parser.parse(is2).get(1).toString();
            Log.i("input", weather.currentCondition + weather.currentTemperature);
            //Log.i("input", "Temperature actuelle (currentTemperature)" + currentTemperature + "Condition actuelle ");
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature", weather.currentCondition + weather.currentTemperature);
            intent.putExtra("currentCondition", weather.currentCondition);
            startActivity(intent);
        }

        else if ( v == quebec ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.quebec);
            //Log.i("input", "Temperature actuelle " + parser.parse(is).get(0).toString());
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature", parser.parse(is).get(0).toString());
            startActivity(intent);
        }

        else if ( v == salluit ) {
            WeatherXmlParser parser = new WeatherXmlParser();
            InputStream is = getResources().openRawResource(R.raw.salluit);
            // Log.i("input", "Temperature actuelle " + parser.parse(is).get(0).toString());
            Intent intent = new Intent(this, Weather.class);
            intent.putExtra("currentTemperature", parser.parse(is).get(0).toString());
            startActivity(intent);
        }
    }
}
