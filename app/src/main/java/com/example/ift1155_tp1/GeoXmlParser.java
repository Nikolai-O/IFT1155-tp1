package com.example.ift1155_tp1;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeoXmlParser implements Serializable {
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries= new ArrayList();
        //parser.require(XmlPullParser.START_TAG, ns, "siteList");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("ville")) {
                //entries.add(parser.getProperty("code"));
                //Log.i("info2", (String) parser.getProperty("code"));
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public static class Entry {
        public final String nameEn;
        public final String nameFr;
        public final Float lat;
        public final Float lon;

        private Entry(String nameEn, String nameFr, Float lat, Float lon) {

            this.nameEn = nameEn;
            this.nameFr = nameFr;
            this.lat = lat;
            this.lon = lon;
        }
    }

    private GeoXmlParser.Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ville");
        String nameen = null;//parser.getAttributeValue(null, "namen");
        String namefr = null;
        Float lat = null;
        Float lon = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("nameen"))
                nameen = readNameEn(parser);
            else if (name.equals("namefr"))
                namefr = readNameFr(parser);
            else if (name.equals("lat"))
                lat = readLat(parser);
            else if (name.equals("long"))
                lon = readLon(parser);
            else
                skip(parser);
        }

        return new GeoXmlParser.Entry(nameen, namefr, lat, lon);
    }

    private String readNameEn(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "nameen");
        String nameen = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "nameen");
        return nameen;
    }

    private String readNameFr(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "namefr");
        String namefr = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "namefr");
        return namefr;
    }

    private Float readLat(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "lat");
        Float lat = Float.valueOf(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "lat");
        return lat;
    }

    private Float readLon(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "long");
        Float lon = Float.valueOf(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "long");
        return lon;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
