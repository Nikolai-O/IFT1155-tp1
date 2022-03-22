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
        parser.require(XmlPullParser.START_TAG, ns, "siteData");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("currentConditions")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    public static class Entry {
        public final String currentTemperature;
        public final String currentCondition;
        public final String windSpeed;
        public final String windDirection;
        public final String summary;
        public final String tmr;
        public final String tmrSummary;
        public final String tmrTemp;
        public final String afterTmr;
        public final String afterTmrSummary;
        public final String afterTmrTemp;
        public final String tmr3;
        public final String tmrSummary3;
        public final String tmrTemp3;
        public final String tmr4;
        public final String tmrSummary4;
        public final String tmrTemp4;

        private Entry(String temperature, String currentCondition, String windSpeed,
                      String windDirection, String summary, String tmr, String tmrSummary, String tmrTemp,
                      String afterTmr, String afterTmrSummary, String afterTmrTemp, String tmr3,
                      String tmrSummary3, String tmrTemp3, String tmr4, String tmrSummary4, String tmrTemp4) {
            this.currentTemperature = temperature;
            this.currentCondition = currentCondition;
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
            this.summary = summary;
            this.tmr = tmr;
            this.tmrSummary = tmrSummary;
            this.tmrTemp = tmrTemp;
            this.afterTmr = afterTmr;
            this.afterTmrSummary = afterTmrSummary;
            this.afterTmrTemp = afterTmrTemp;
            this.tmr3 = tmr3;
            this.tmrSummary3 = tmrSummary3;
            this.tmrTemp3 = tmrTemp3;
            this.tmr4 = tmr4;
            this.tmrSummary4 = tmrSummary4;
            this.tmrTemp4 = tmrTemp4;
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
        String summary = null;
        String tmr = null;
        String tmrSummary = null;
        String tmrTemp = null;
        String afterTmr = null;
        String afterTmrSummary = null;
        String afterTmrTemp = null;
        String tmr3 = null;
        String tmrSummary3 = null;
        String tmrTemp3 = null;
        String tmr4 = null;
        String tmrSummary4 = null;
        String tmrTemp4 = null;

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

                findTag(parser, "forecastGroup");
                findTag(parser, "forecast");
                findTag(parser, "textSummary");
                summary = readSummary(parser);

                findTag(parser, "forecast");
//                findTag(parser, "forecast");
                findTag(parser, "period");
                tmr = readTmr(parser);
                findTag(parser, "textSummary");
                tmrSummary = readTmrSummary(parser);
                findTag(parser, "temperature");
                tmrTemp = readTmrTemp(parser);

                findTag(parser, "forecast");
                findTag(parser, "forecast");
                findTag(parser, "period");
                afterTmr = readTmr(parser);
                findTag(parser, "textSummary");
                afterTmrSummary = readAfterTmrSummary(parser);
                findTag(parser, "temperature");
                afterTmrTemp = readAfterTmrTemp(parser);

                findTag(parser, "forecast");
                findTag(parser, "forecast");
                findTag(parser, "period");
                tmr3 = readTmr(parser);
                findTag(parser, "textSummary");
                tmrSummary3 = readAfterTmrSummary(parser);
                findTag(parser, "temperature");
                tmrTemp3 = readAfterTmrTemp(parser);

                findTag(parser, "forecast");
                findTag(parser, "forecast");
                findTag(parser, "period");
                tmr4 = readTmr(parser);
                findTag(parser, "textSummary");
                tmrSummary4 = readAfterTmrSummary(parser);
                findTag(parser, "temperature");
                tmrTemp4 = readAfterTmrTemp(parser);
            } else {
                skip(parser);
            }
        }
        return new Entry(temperature, currentCondition, windSpeed, windDirection, summary, tmr,
                tmrSummary, tmrTemp, afterTmr, afterTmrSummary, afterTmrTemp, tmr3, tmrSummary3,
                tmrTemp3, tmr4, tmrSummary4, tmrTemp4);
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
        //tagSkipper(parser, 3);
        findTag(parser,"speed");
        parser.require(XmlPullParser.START_TAG, ns, "speed");
        String windSpeed = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "speed");
        return windSpeed;
    }

    private String readWindDirection(XmlPullParser parser) throws IOException, XmlPullParserException {
        //tagSkipper(parser, 8);
        findTag(parser,"direction");
        parser.require(XmlPullParser.START_TAG, ns, "direction");
        String direction = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "direction");
        return direction;
    }

    private String readSummary(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "textSummary");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "textSummary");

        return summary;
    }

    private String readTmr(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "period");
        String tmr = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "period");

        return tmr;
    }

    private String readTmrSummary(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "textSummary");
        String tmrSummary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "textSummary");

        return tmrSummary;
    }

    private String readTmrTemp(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "temperature");
        String tmrTemp = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "temperature");

        return tmrTemp;
    }

    private String readAfterTmr(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "period");
        String afterTmr = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "period");

        return afterTmr;
    }

    private String readAfterTmrSummary(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "textSummary");
        String afterTmrSummary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "textSummary");

        return afterTmrSummary;
    }

    private String readAfterTmrTemp(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "temperature");
        String afterTmrTemp = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "temperature");

        return afterTmrTemp;
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

    private void tagSkipper(XmlPullParser par, int amount) throws XmlPullParserException, IOException {
        for (int i = 0; i < amount; i++)
            par.next();
    }

    private void findTag(XmlPullParser par, String tag) throws XmlPullParserException, IOException {
        Boolean found = false;
        String tagName;
        while (!found) {
            par.next();
            if(par.getEventType() != XmlPullParser.START_TAG)
                continue;
            tagName = par.getName();

            if (tagName.equals(tag))
                found = true;
        }
    }
}