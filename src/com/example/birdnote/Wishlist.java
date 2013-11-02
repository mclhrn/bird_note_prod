package com.example.birdnote;

import java.util.List;

import com.example.birdnote.db.BirdsDataSource;
import com.example.birdnote.list.CustomBaseAdapter;
import com.example.birdnote.model.Bird;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Wishlist extends ListActivity {
	
	private static final int WISHLIST_DETAIL_ACTIVITY = 1001;
	
	private List<Bird> birds;
	boolean isWishlist;
	boolean wishAtoZ = false;
	
	// create reference to database
	BirdsDataSource datasource;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wishlist);
		
		// open connection to db
		datasource = new BirdsDataSource(this);
		datasource.open();
	
		birds = datasource.findWishList();
		
		if (birds.isEmpty()) {
			showToast();
		} 
		
		isWishlist = true;
		
		ImageButton atoz = (ImageButton) findViewById(R.id.wish_atoz);
		atoz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!wishAtoZ) {
					birds = datasource.wishAtoZ();
					refreshDisplay();
					wishAtoZ = true;
				} 
				else {
					birds = datasource.findWishList();
					refreshDisplay();
					wishAtoZ = false;
				}
			}
		});
		
		refreshDisplay();
	}
	
	public void refreshDisplay() {
		ArrayAdapter<Bird> adapter = new CustomBaseAdapter(this, birds);
		setListAdapter(adapter);
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Bird bird = birds.get(position);

		Intent intent = new Intent(this, Profile.class);
		intent.putExtra("com.example.birdnote.model.Bird", bird);
		intent.putExtra("isWishlist", isWishlist);
		
		startActivityForResult(intent, WISHLIST_DETAIL_ACTIVITY);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == WISHLIST_DETAIL_ACTIVITY && resultCode == -1) {
			datasource.open();
			birds = datasource.findWishList();
			refreshDisplay();
			isWishlist = true;
		}
	}

	private void showToast() {

		Toast toast = Toast.makeText(this,
				"There are no birds on this list yet",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
				0, 0);
		toast.show();
	}
}