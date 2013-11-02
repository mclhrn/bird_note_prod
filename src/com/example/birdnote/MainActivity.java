package com.example.birdnote;

import com.example.birdnote.services.MyLocation;
import com.example.birdnote.services.MyLocation.LocationResult;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	public static double LAT;
	public static double LNG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);

		Button reference_guide = (Button) findViewById(R.id.ref_guide_icon);
		reference_guide.setOnClickListener(this);

		Button search_btn = (Button) findViewById(R.id.search_icon);
		search_btn.setOnClickListener(this);

		Button birds_seen_btn = (Button) findViewById(R.id.birds_seen_icon);
		birds_seen_btn.setOnClickListener(this);

		Button wishlist_btn = (Button) findViewById(R.id.wishlist_icon);
		wishlist_btn.setOnClickListener(this);

		Button map_view_btn = (Button) findViewById(R.id.map_view_icon);
		map_view_btn.setOnClickListener(this);
		
		LocationResult locationResult = new LocationResult() {
		    @Override
		    public void gotLocation(Location location) {
		    	System.out.println(location.getLatitude());
		    	System.out.println(location.getLongitude());
		    	LAT = location.getLatitude();
		    	LNG = location.getLongitude();
		    }
		};
		
		MyLocation myLocation = new MyLocation();
		myLocation.getLocation(this, locationResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.ref_guide_icon) {
			startActivity(new Intent(this, ReferenceGuide.class));
		}
		if (v.getId() == R.id.search_icon) {
			startActivity(new Intent(this, Search.class));
		}
		if (v.getId() == R.id.birds_seen_icon) {
			startActivity(new Intent(this, BirdsSeen.class));
		}
		if (v.getId() == R.id.wishlist_icon) {
			startActivity(new Intent(this, Wishlist.class));
		}
		if (v.getId() == R.id.map_view_icon) {
			startActivity(new Intent(this, MyLocations.class));
		}
	}
}
