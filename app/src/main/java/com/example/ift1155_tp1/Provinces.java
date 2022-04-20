package com.example.ift1155_tp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

        File file = new File(this.getFilesDir(), "cities.xml");
        for (int i = 0; i < files.length; i++) {
            if (files[i] == "cities.xml");
                exists = !exists;
        }
        if (file.exists()) {
              Log.i("files", "cities.xml found");
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
        Button qcBtn = findViewById(R.id.qcButton);
        if (v == qcBtn) {
            File fileQc = new File(this.getFilesDir(), "qc.xml");
            if (!fileQc.exists()) {
                List villesQc = new ArrayList();
                for (int i = 0; i < villes.size(); i++) {
                    IdXmlParser.Entry ville = (IdXmlParser.Entry) villes.get(i);
                    //Log.i("Qc", ville.province);

                    if (ville.province.equals("QC")) {
                        //Log.i("Qc", String.valueOf(i));
                        villesQc.add(ville);
                    }
                }

                StringBuilder str = new StringBuilder();
                str.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<siteList>");
                for (int i = 0; i < villesQc.size(); i++) {
                    IdXmlParser.Entry ville = (IdXmlParser.Entry) villesQc.get(i);
                    str.append("\n<site code=\"" + ville.code + "\">\n");
                    str.append("\t<nameEn>" + ville.nameEn + "</nameEn>\n");
                    str.append("\t<nameFr>" + ville.nameFr + "</nameFr>\n");
                    str.append("\t<provinceCode>" + ville.province + "</provinceCode>\n");
                    str.append("</site>");
//            Log.i("QcList", "<site code=\"" + ville.code + "\">\n");
//            Log.i("QcList", "\t<nameEn>" + ville.nameEn + "</nameEn>\n");
//            Log.i("QcList", "\t<nameFr>" + ville.nameFr + "</nameFr>\n");
//            Log.i("QcList", "\t<provinceCode>" + ville.province + "</provinceCode>\n");
//            Log.i("QcList", "</site>");
                }
                str.append("</siteList>");
                Log.i("QcList", String.valueOf(str));


                Document doc = convertStringToXMLDocument(String.valueOf(str));
                NodeList names = doc.getElementsByTagName("nameEn");
                //villes = getVilles("cities.xml");
                try {
                    OutputStreamWriter out = new OutputStreamWriter(
                            openFileOutput("qc.xml", 0));
                    out.write(String.valueOf(str));
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                IdXmlParser.Entry ids = (IdXmlParser.Entry) villes.get(0);
                IdXmlParser.Entry ids2 = (IdXmlParser.Entry) villes.get(854);
                Log.i("villes", "1");
                Log.i("villes", "Info: " + ids.code + " " + ids.nameEn + " " + ids.nameFr + " " + ids.province);
                Log.i("villes", "2");
                Log.i("villes", "Info: " + ids2.code + " " + ids2.nameEn + " " + ids2.nameFr + " " + ids2.province);
                Intent intent = new Intent(this, Cities.class);
                intent.putExtra("province", "Qc");
                startActivity(intent);
            } else {
                Log.i("villes", "Found");
                Intent intent = new Intent(this, Cities.class);
                intent.putExtra("province", "Qc");
                startActivity(intent);
            }
        }
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
                    text2 = msg.getData().getString("text");
                    Document doc = convertStringToXMLDocument(text2);
                    NodeList names = doc.getElementsByTagName("nameEn");
                    //villes = getVilles("cities.xml");
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(
                                openFileOutput("cities.xml", 0 ), StandardCharsets.UTF_8);
                        Log.i("encoding", out.getEncoding());
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
