package com.example.birdnote;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.birdnote.db.BirdsDBOpenHelper;
import com.example.birdnote.db.BirdsDataSource;
import com.example.birdnote.list.CustomBaseAdapter;
import com.example.birdnote.model.Bird;

public class SearchResults extends ListActivity {

	private List<Bird> birds;
	
	long currentPrimaryColourId;
	long currentSecondaryColourId;
	long currentCrownColourId;
	long currentBillLengthId;
	long currentBillColourId;
	long currentTailShapeId;
	String orderBy = null;
	boolean isBirdsSeen;
	boolean searchAtoZ = false;
	
	// create reference to database
	BirdsDataSource datasource;

	public final static String LOGTAG = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);

		Bundle b = getIntent().getExtras();
		
		currentPrimaryColourId = b.getLong("currentPrimaryColourId");
		currentSecondaryColourId = b.getLong("currentSecondaryColourId");
		currentCrownColourId = b.getLong("currentCrownColourId");
		currentBillLengthId = b.getLong("currentBillLengthId");
		currentBillColourId = b.getLong("currentBillColourId");
		currentTailShapeId = b.getLong("currentTailShapeId");
		isBirdsSeen = b.getBoolean("isBirdsSeen");
		
		// open connection to db
		datasource = new BirdsDataSource(this);
		datasource.open();

		// get list of search results from db
		birds = datasource.userSearch(currentPrimaryColourId, 
				currentSecondaryColourId, currentCrownColourId, 
				currentBillLengthId, currentBillColourId, 
				currentTailShapeId, orderBy);
		
		ImageButton atoz = (ImageButton) findViewById(R.id.search_atoz);
		atoz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!searchAtoZ) {
					
					orderBy = BirdsDBOpenHelper.BIRDS_COLUMN_NAME + " DESC";
					birds = datasource.userSearch(currentPrimaryColourId, 
							currentSecondaryColourId, currentCrownColourId, 
							currentBillLengthId, currentBillColourId, 
							currentTailShapeId, orderBy);
					refreshDisplay();
					searchAtoZ = true;
					
				} 
				else {
					
					orderBy = null;
					birds = datasource.userSearch(currentPrimaryColourId, 
							currentSecondaryColourId, currentCrownColourId, 
							currentBillLengthId, currentBillColourId, 
							currentTailShapeId, orderBy);
					refreshDisplay();
					searchAtoZ = false;
					
				}
			}
		});
		
		TextView searchResults = (TextView) findViewById(R.id.num_results);
		searchResults.setText(birds.size() + " birds");
		
		refreshDisplay();
	}

	private void refreshDisplay() {
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
		intent.putExtra("isBirdsSeen", isBirdsSeen);

		startActivity(intent);
	}
}
