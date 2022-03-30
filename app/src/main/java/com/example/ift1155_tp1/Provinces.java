package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Provinces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provinces);

        downloadXML();


    }

    public void back (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cities (View v) {
        Intent intent = new Intent(this, Cities.class);
        startActivity(intent);
    }

    private void downloadXML() {
        final String url = "https://dd.weather.gc.ca/citypage_weather/xml/siteList.xml";

        new Thread(()->{
            int BUFFER_SIZE = 2000;
            InputStream in;
            Message msg = Message.obtain();
            msg.what=2;
            try {
                in = openHttpConnection(url);
                InputStreamReader isr = new InputStreamReader(in);
                int charRead;
                StringBuffer text = new StringBuffer();
                char[] inputBuffer = new char[BUFFER_SIZE];

                while ((charRead = isr.read(inputBuffer)) > 0) {
                    String readString =
                            String.copyValueOf(inputBuffer, 0, charRead);
                    text.append(readString);
                    inputBuffer = new char[BUFFER_SIZE];
                    Log.i("Line", readString);
                }
                Bundle b = new Bundle();
                b.putString("text", text.toString());
                msg.setData(b);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // messageHandler.sendMessage(msg);
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
            httpConn.connect();

            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
