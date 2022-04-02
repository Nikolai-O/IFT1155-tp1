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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Provinces extends AppCompatActivity {
     List villes;
    private StringBuffer text = null;
    static String text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provinces);
        String[] files = this.fileList();
        boolean exists = false;

        for (int i = 0; i < files.length; i++) {
            if (files[i] == "cities.xml");
                exists = !exists;
        }
        if (exists) {
//            Log.i("files", "cities.xml found");
//            IdXmlParser parser = new IdXmlParser();
//            InputStream is = null;
//            try {
//                is = openFileInput("cities.xml");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                villes = parser.parse(is);
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            villes = getVilles("cities.xml");
        } else {
            Log.i("files", "cities.xml not found, downloading");
            downloadXML();
        }
    }


    public void back (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cities (View v) {
        List villesQc = new ArrayList();
        for (int i = 0; i < villes.size(); i++){
            IdXmlParser.Entry ville = (IdXmlParser.Entry) villes.get(i);
            if (ville.province.equals("QC")) {
                villesQc.add(ville);
            }
        }
        for (int i = 0; i< villesQc.size();i++) {
            IdXmlParser.Entry ville = (IdXmlParser.Entry) villesQc.get(i);
            Log.i("QcList", ville.nameFr);

        }
        Log.i("QcList", String.valueOf(villesQc.size()));
        IdXmlParser.Entry ids = (IdXmlParser.Entry) villes.get(0);
        IdXmlParser.Entry ids2 = (IdXmlParser.Entry) villes.get(854);
        Log.i("villes", "1");
        Log.i("villes", "Info: " + ids.code + " " + ids.nameEn  + " " + ids.nameFr  + " " + ids.province);
        Log.i("villes", "2");
        Log.i("villes", "Info: " + ids2.code + " " + ids2.nameEn  + " " + ids2.nameFr  + " " + ids2.province);
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
                Bundle b = new Bundle();
                b.putString("text", text.toString());
                msg.setData(b);
                in.close();
                messageHandler.sendMessage(msg);
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
                    //Log.i("info2", text2);
                    Document doc = convertStringToXMLDocument(text2);
                    NodeList names = doc.getElementsByTagName("nameEn");
                    //villes = getVilles("cities.xml");
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
                        //List entries = parser.parse(is);
                        villes = parser.parse(is);
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

    private final Handler messageHandler = new CustomHandler(this);
}
