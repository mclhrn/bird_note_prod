package com.example.birdnote;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.birdnote.db.BirdsDataSource;
import com.example.birdnote.model.BirdsSeenModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyLocations extends Activity {

	BirdsDataSource datasource;
	private List<BirdsSeenModel> birdsSeen;

	LatLng myMarker;
	static final LatLng CURRENT_LOC = new LatLng(MainActivity.LAT, MainActivity.LNG);
	private static final String LOGTAG = "Birds";
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);

 		datasource = new BirdsDataSource(this);
		datasource.open();

		birdsSeen = datasource.findBirdsForMap();
		Log.i(LOGTAG, birdsSeen.toString());

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		for (int i = 0; i < birdsSeen.size(); i++) {
			
			map.addMarker(new MarkerOptions().position(myMarker = new LatLng(birdsSeen.get(i)
																.getLatitude(), birdsSeen.get(i)
																.getLongitude())).title(
																		birdsSeen.get(i).getName()));
		}
		
		map.addMarker(new MarkerOptions().position(CURRENT_LOC).title(
				"You are here"));

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(CURRENT_LOC, 17));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
	}

	protected void onResume() {
		super.onResume();
		datasource.open();
	}

	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}