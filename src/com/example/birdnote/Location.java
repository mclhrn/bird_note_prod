package com.example.birdnote;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location extends Activity {
  static final LatLng WIT = new LatLng(52.245, -7.137);
  //static final LatLng KIEL = new LatLng(53.551, 9.993);
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.location);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    Marker wit = map.addMarker(new MarkerOptions().position(WIT)
        .title("Waterford Institute of Technology"));
//    Marker kiel = map.addMarker(new MarkerOptions()
//        .position(KIEL)
//        .title("Kiel")
//        .snippet("Kiel is cool")
//        );

    // Move the camera instantly to hamburg with a zoom of 17.
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(WIT, 17));

    // Zoom in, animating the camera.
    map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

} 