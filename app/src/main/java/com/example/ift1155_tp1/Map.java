package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    private final GoogleMap googleMap = null;
    private int mapType = GoogleMap.MAP_TYPE_NORMAL;
    float cameraZoom = 6;
    LatLng cameraLatLng;
    List villes;
    String nomProvince;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState != null) {
            mapType = savedInstanceState.getInt("map_type", GoogleMap.MAP_TYPE_NORMAL);

            double savedLat = savedInstanceState.getDouble("lat");
            double savedLng = savedInstanceState.getDouble("lng");
            cameraLatLng = new LatLng(savedLat,savedLng);

            cameraZoom = savedInstanceState.getFloat("zoom", 10);
        }

        Intent ville = getIntent();
        nomProvince = ville.getStringExtra("Province");
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapType(mapType);

        villes = getVilles(R.raw.qc);

        LatLng sfLatLng = new LatLng(45.500918, -73.615656);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(sfLatLng).title("Pavillon Andre-Aisenstadt")
                .snippet("DIRO!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        LatLng sLatLng = new LatLng(45.508316, -73.612824);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(sLatLng).title("Cepsum")
                .snippet("Sweat!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        LatLng latlng = new LatLng(((GeoXmlParser.Entry) villes.get(0)).lat, -((GeoXmlParser.Entry) villes.get(0)).lon);
        cameraLatLng = latlng;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraLatLng, cameraZoom));

        //googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        for (int i = 0; i < villes.size(); i++) {

            GeoXmlParser.Entry ville = (GeoXmlParser.Entry) villes.get(i);
            Log.i("coordMarkers", ville.nameEn + ": " + "Lat: " + ville.lat + " Long: " + ville.lon);
            LatLng coord = new LatLng(ville.lat, -ville.lon);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.addMarker(new MarkerOptions()
                    .position(coord).title(ville.nameEn)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
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

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Province", nomProvince);
        super.onBackPressed();
    }
}
