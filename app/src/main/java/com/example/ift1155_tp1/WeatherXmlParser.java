package com.example.ift1155_tp1;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherXmlParser {
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
        int count = 0;
        parser.require(XmlPullParser.START_TAG, ns, "siteData");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("currentConditions")) {
                Log.i("forecast", "conditions found");
                entries.add(readEntry(parser));
            } else if (name.equals("forecastGroup"))
                Log.i("forecast", "found");
            else if (name.equals("hourlyForecastGroup"))
                Log.i("forecast", "hourlyForecastGroup found");
            else {
                Log.i("forecast", "skipped " + name);
                count++;
                skip(parser);
                Log.i("forecast", "Count = " + count);
            }
        }
        return entries;

    }

    public static class Entry {
        public final String currentTemperature;
        public final String currentCondition;
        public final String windSpeed;
        public final String windDirection;

        private Entry(String temperature, String currentCondition, String windSpeed, String windDirection) {
            this.currentTemperature = temperature;
            this.currentCondition = currentCondition;
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
        }

        public String toString() {
            return this.currentTemperature + " " + this.currentCondition;
        }
    }

    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "currentConditions");
        String temperature = null;
        String currentCondition = null;
        String windSpeed = null;
        String windDirection = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("condition")) {
                currentCondition = readCurrentCondition(parser);
            }
            else if (name.equals("temperature")) {
                temperature = readTemperature(parser);
            }
            else if (name.equals("wind")) {
                windSpeed = readWindSpeed(parser);
                windDirection = readWindDirection(parser);
            } else {
                skip(parser);
            }
        }
        return new Entry(temperature, currentCondition, windSpeed, windDirection);
    }

    private String readTemperature(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "temperature");
        String temperature = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "temperature");
        return temperature;
    }

    private String readCurrentCondition(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "condition");
        String currentCondition = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "condition");
        return currentCondition;
    }

    private String readWindSpeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.next();
        parser.next();
        parser.next();
        return parser.getText();
    }

    private String readWindDirection(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.next();
        parser.next();
        parser.next();
        parser.next();
        parser.next();
        parser.next();
        parser.next();
        parser.next();
        return parser.getText();
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
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
}
