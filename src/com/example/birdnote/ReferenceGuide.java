package com.example.birdnote;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.example.birdnote.db.BirdsDataSource;
import com.example.birdnote.list.CustomBaseAdapter;
import com.example.birdnote.model.Bird;
import com.example.birdnote.xml.BirdsPullParser;

public class ReferenceGuide extends ListActivity implements TextWatcher {

	private List<Bird> birds;
	boolean isBirdsSeen;
	boolean refAtoZ = false;
    
	// create reference to database
	BirdsDataSource datasource;

	public final static String LOGTAG = null;

	ArrayAdapter<Bird> adapter;
	EditText inputSearch;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reference_guide);

		ListView lv = (ListView) findViewById(android.R.id.list);
		lv.setTextFilterEnabled(true);
		
		inputSearch = (EditText) findViewById(R.id.ref_input_search);
		inputSearch.addTextChangedListener(this);
		
		// open connection to db
		datasource = new BirdsDataSource(this);
		datasource.open();

		// get list of birds from db
		birds = datasource.findAll();
		if (birds.size() == 0) {
			createData();
			birds = datasource.findAll();
		}

		// Toggle display a to z or z to a
		ImageButton atoz = (ImageButton) findViewById(R.id.ref_atoz);
		atoz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!refAtoZ) {
					birds = datasource.refAtoZ();
					refreshDisplay();
					refAtoZ = true;
				} 
				else {
					birds = datasource.findAll();
					refreshDisplay();
					refAtoZ = false;
				}
			}
		});

		isBirdsSeen = false;

		refreshDisplay();
	}

	private void refreshDisplay() {
		adapter = new CustomBaseAdapter(this, birds);
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

	private void createData() {

		BirdsPullParser parser = new BirdsPullParser();
		List<Bird> birds = parser.parseXML(this);

		for (Bird bird : birds) {
			datasource.create(bird);
		}
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		
		Bird bird = birds.get(position);
		
		Intent intent = new Intent(this, Profile.class);
		intent.putExtra("com.example.birdnote.model.Bird", bird);
		intent.putExtra("isBirdsSeen", isBirdsSeen);

		startActivity(intent);
	}
	

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence cs, int start, int before, int count) {
		if (count < before) {
			((CustomBaseAdapter) adapter).resetData();
		}
		adapter.getFilter().filter(cs.toString());   
	}
}