package com.example.ift1155_tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List villes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        villes = getVilles(R.raw.qc);
//        for (int i = 0; i < villes.size(); i++) {
//            GeoXmlParser.Entry ville = (GeoXmlParser.Entry) villes.get(i);
//            Log.i("Coord", ville.nameEn + ville.lat + ville.lon);
//        }
//        Log.i("Coord", "hello");
    }

    public void start(View v) {
        Button helpBtn = findViewById(R.id.helpButton);
        Button aboutBtn = findViewById(R.id.aboutButton);
        Button mapBtn = findViewById(R.id.mapButton);

        if ( v == helpBtn) {
            Intent intent = new Intent(this, Help.class);
            startActivity(intent);
        }

        else if ( v == aboutBtn) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        else if ( v == mapBtn) {
            Intent intent = new Intent(this, Map.class);
            startActivity(intent);
        }

        else {
            Intent intent = new Intent(this, Provinces.class);
            startActivity(intent);
        }
    }
    public List getVilles(int file) {
        GeoXmlParser parser = new GeoXmlParser ();
        InputStream is = null;
        List entries = null;
        is = getResources().openRawResource(file);
        try {
            entries = parser.parse(is);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}