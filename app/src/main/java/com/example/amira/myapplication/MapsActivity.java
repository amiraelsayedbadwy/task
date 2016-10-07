package com.example.amira.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    gpstarcker tracker;
    private GoogleMap mMap;
    private Marker marker;
    double lat;
    double lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tracker = new gpstarcker(MapsActivity.this);
        tracker.getLocation();
        lat = tracker.getLatitude();
        lng = tracker.getLongitude();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


        }
        mMap.setMyLocationEnabled(true);
      //  mMap.getUiSettings().setCompassEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng cairo = new LatLng(lat, lng);// beygeb location da 3la map mesh loction bet3e  5als wa mesh ft7le gps
        LatLng cair = new LatLng(30.127810,31.279392);
        LatLng cair1 = new LatLng(30.127820,31.279397);
        LatLng cair2 = new LatLng(30.127819,31.279395);
        LatLng cair3 = new LatLng(30.127822,31.279396);
        mMap.addMarker(new MarkerOptions().position(cairo).title("mark here").draggable(true));
        mMap.addMarker(new MarkerOptions().position(cair).title("mark here").draggable(true));
        mMap.addMarker(new MarkerOptions().position(cair1).title("mark here").draggable(true));
        mMap.addMarker(new MarkerOptions().position(cair2).title("mark here").draggable(true));
        mMap.addMarker(new MarkerOptions().position(cair3).title("mark here").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cairo));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Intent intent = new Intent("android.intent.action.DIAL");

                /** Starting the Dialer activity */
                startActivity(intent);
                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.mainmenue, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_sethybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.menu_zoomin:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;

            case R.id.menu_zoomout:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
        }
        return true;
    }
}
