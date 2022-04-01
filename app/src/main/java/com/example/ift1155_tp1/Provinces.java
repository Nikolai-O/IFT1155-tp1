package com.example.ift1155_tp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Provinces extends AppCompatActivity {
    //static Handler messageHandler;
    private StringBuffer text = null;
    static String text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provinces);
        //openHttpConnection("https://dd.weather.gc.ca/citypage_weather/xml/siteList.xml");
        downloadXML();
        //Bundle bundle = messageHandler.obtainMessage(1).getData();
        //String text = bundle.getString("text");
        //Log.i("info2", text.toString());
        //Log.i("info2", text2);
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
        new Thread(() -> {
            int BUFFER_SIZE = 2000;
            InputStream in;
            Message msg = Message.obtain();
            msg.what = 1;
            try {
                in = openHttpConnection(url);
                InputStreamReader isr = new InputStreamReader(in);
                int charRead;
                text = new StringBuffer();
                char[] inputBuffer = new char[BUFFER_SIZE];

                while ((charRead = isr.read(inputBuffer)) > 0) {
                    String readString =
                            String.copyValueOf(inputBuffer, 0, charRead);
                    text.append(readString);
                    inputBuffer = new char[BUFFER_SIZE];

                }
                //Log.i("Line", text.toString());
                Bundle b = new Bundle();
                b.putString("text", text.toString());
                msg.setData(b);
                in.close();
                messageHandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //messageHandler = new CustomHandler(this);
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

    class CustomHandler extends Handler {

        private final Provinces activity;

        CustomHandler(Provinces activity) {
            super(Looper.myLooper());
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.i("info2", "what what");
                    text2 = msg.getData().getString("text");
                    Log.i("info2", text2);
                    Document doc = convertStringToXMLDocument(text2);
                    NodeList names = doc.getElementsByTagName("nameEn");

                    try {
                        OutputStreamWriter out = new OutputStreamWriter(
                                openFileOutput("cities.xml", 0));
                        out.write(text2);
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    IdXmlParser parser = new IdXmlParser();
                    InputStream is = null;
                    try {
                        is = openFileInput("cities.xml");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        List entries = parser.parse(is);
                        IdXmlParser.Entry ids = (IdXmlParser.Entry) entries.get(0);
                        IdXmlParser.Entry ids2 = (IdXmlParser.Entry) entries.get(854);
                        Log.i("Ids", "1");
                        Log.i("Ids", "Info: " + ids.code + " " + ids.nameEn  + " " + ids.nameFr  + " " + ids.province);
                        Log.i("Ids2", "2");
                        Log.i("Ids2", "Info: " + ids2.code + " " + ids2.nameEn  + " " + ids2.nameFr  + " " + ids2.province);
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.i("info2", String.valueOf(names.getLength()));
            }
            //this.activity.simpleProgressBar.setVisibility(View.GONE);

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

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private final Handler messageHandler = new CustomHandler(this);
}
