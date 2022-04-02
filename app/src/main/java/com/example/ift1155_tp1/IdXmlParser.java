package com.example.ift1155_tp1;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IdXmlParser implements Serializable {
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
            if (name.equals("site")) {
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
        public final String code;
        public final String nameEn;
        public final String nameFr;
        public final String province;

        private Entry(String code, String nameEn, String nameFr, String province) {
            this.code = code;
            this.nameEn = nameEn;
            this.nameFr = nameFr;
            this.province = province;
        }
    }

    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "site");
        String code = parser.getAttributeValue(null, "code");;
        String nameEn = null;
        String nameFr = null;
        String province = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("nameEn"))
                nameEn = readNameEn(parser);
            else if (name.equals("nameFr"))
                nameFr = readNameFr(parser);
            else if (name.equals("provinceCode"))
                province = readProvince(parser);
            else
                skip(parser);
        }

        return new Entry(code, nameEn, nameFr, province);
    }

    private String readNameEn(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "nameEn");
        String nameEn = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "nameEn");
        return nameEn;
    }

    private String readNameFr(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "nameFr");
        String nameFr = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "nameFr");
        return nameFr;
    }

    private String readProvince(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "provinceCode");
        String province = readText(parser);
        //parser.require(XmlPullParser.END_TAG, ns, "provinceCode");
        return province;
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
