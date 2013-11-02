package com.example.birdnote;

import java.util.List;

import com.example.birdnote.db.BirdsDataSource;
import com.example.birdnote.db.IdentifyDataSource;
import com.example.birdnote.login.Register;
import com.example.birdnote.login.Welcome;
import com.example.birdnote.model.BillLength;
import com.example.birdnote.model.Colour;
import com.example.birdnote.model.TailShape;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Search extends Activity implements OnClickListener{

	private static final String LOGTAG = "Birds";

	long currentPrimaryColourId = 0;
	long currentSecondaryColourId = 0;
	long currentCrownColourId = 0;
	long currentBillLengthId = 0;
	long currentBillColourId = 0;
	long currentTailShapeId = 0;

	// bill length values
	private String[] billLengthValues = { "All", "Short & Broad",
			"Short & Pointy", "Medium Sized", "Large", "Hooked", "Narrow",
			"Long & Narrow", "Very Long" };
	private List<BillLength> billLengths;

	// colour values
	private String[] colourValues = { "All", "White", "Black", "Light Blue",
			"Blue", "Dark Blue", "Light Brown", "Brown", "Dark Brown",
			"Yellow", "Orange", "Red", "Green", "Pink", "Gray", "Rust",
			"Purple" };
	private List<Colour> colours;

	// tail shape values
	private String[] tailShapeValues = { "All", "Notched", "Rounded", "Fan",
			"Forked", "Squared", "Unique" };
	private List<TailShape> shapes;
	
	boolean isBirdsSeen = false;

	// create reference to database
	IdentifyDataSource datasource;
	BirdsDataSource birdsDatasource; 
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		birdsDatasource = new BirdsDataSource(this);
		birdsDatasource.open();

		Button submitSearch = (Button) findViewById(R.id.submit_search);
		submitSearch.setOnClickListener(this);
           		
		// call database setup
		dbTask();
		
		// fill spinners
		fillSpinners();
		
		
		
		 findViewById(R.id.testWelcome).setOnClickListener(
			        new View.OnClickListener() {
			            @Override
			            public void onClick(View v) {
			                // No account, load new account view
			                Intent intent = new Intent(Search.this,
			                    Welcome.class);
			                startActivityForResult(intent, 0);
			            }
			        });
		
		
		
		
		
		
		
	}
	
	void fillSpinners() {
		Spinner s1 = (Spinner) findViewById(R.id.primary_colour);

		ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, colourValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s1.setAdapter(adapter);

		s1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentPrimaryColourId = id;
				Log.i(LOGTAG, "Current Primary Colour id is: "
						+ currentPrimaryColourId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		Spinner s2 = (Spinner) findViewById(R.id.secondary_colour);

		adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, colourValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s2.setAdapter(adapter);

		s2.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentSecondaryColourId = id;
				Log.i(LOGTAG, "Current Secondary Colour id is: "
						+ currentSecondaryColourId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		Spinner s3 = (Spinner) findViewById(R.id.crown_colour);

		adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, colourValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s3.setAdapter(adapter);

		s3.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentCrownColourId = id;
				Log.i(LOGTAG, "Current Crown Colour id is: "
						+ currentCrownColourId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		Spinner s4 = (Spinner) findViewById(R.id.bill_length);

		adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, billLengthValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s4.setAdapter(adapter);

		s4.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentBillLengthId = id;
				Log.i(LOGTAG, "Current Bill Length id is: "
						+ currentBillLengthId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		Spinner s5 = (Spinner) findViewById(R.id.bill_colour);

		adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, colourValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s5.setAdapter(adapter);

		s5.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentBillColourId = id;
				Log.i(LOGTAG, "Current Bill Colour id is: "
						+ currentBillColourId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		Spinner s6 = (Spinner) findViewById(R.id.tail_shape);

		adapter = new ArrayAdapter<Object>(this,
				R.layout.spinner_item, tailShapeValues);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		s6.setAdapter(adapter);

		s6.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentTailShapeId = id;
				Log.i(LOGTAG, "Current Tail Shape id is: "
						+ currentTailShapeId);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	// set up database
	private void dbTask() {
		// open connection to db
		datasource = new IdentifyDataSource(this);
		datasource.open();
	
		// get list of bill lengths from db
		billLengths = datasource.findAllBillLengthValues();
		if (billLengths.size() == 0) {
			createBillLengthData();
			billLengths = datasource.findAllBillLengthValues();
		}
		Log.i(LOGTAG, "Bill Length table db so far: " + billLengths.size());
		
		// get list of colours from db
		colours = datasource.findAllColourValues();
		if (colours.size() == 0) {
			createColourData();
			colours = datasource.findAllColourValues();
		}
		Log.i(LOGTAG, "Colour table db so far: " + colours.size());
		
		// get list of tail shapes from db
		shapes = datasource.findAllTailShapeValues();
		if (shapes.size() == 0) {
			createTailShapeData();
			shapes = datasource.findAllTailShapeValues();
		}
		Log.i(LOGTAG, "Tail Shape table db so far: " + shapes.size());
	
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

	// create rows in bill length table
	private void createBillLengthData() {
		BillLength billLength = new BillLength();
		for (int i = 0; i < billLengthValues.length; i++) {
			billLength.setBillLength(billLengthValues[i]);
			billLength = datasource.createBillLength(billLength);
			Log.i(LOGTAG, "Bill Lenght created with id " + billLength.getId());
		}
	}
	
	// create rows in colour table
	private void createColourData() {
		Colour colour = new Colour();
		for (int i = 0; i < colourValues.length; i++) {
			colour.setColour(colourValues[i]);
			colour = datasource.createColour(colour);
			Log.i(LOGTAG, "Colour created with id " + colour.getId());
		}
	}
	
	// create rows in tail shape table
	private void createTailShapeData() {
		TailShape shape = new TailShape();
		for (int i = 0; i < tailShapeValues.length; i++) {
			shape.setShape(tailShapeValues[i]);
			shape = datasource.createShape(shape);
			Log.i(LOGTAG, "Tail Shape created with id " + shape.getId());
		}
	}

	
	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(this, SearchResults.class);
		
    	intent.putExtra("currentPrimaryColourId", currentPrimaryColourId);
		intent.putExtra("currentSecondaryColourId", currentSecondaryColourId);
		intent.putExtra("currentCrownColourId", currentCrownColourId);
		intent.putExtra("currentBillLengthId", currentBillLengthId);
		intent.putExtra("currentBillColourId", currentBillColourId);
		intent.putExtra("currentTailShapeId", currentTailShapeId);
		intent.putExtra("isBirdsSeen", isBirdsSeen);
		startActivity(intent);
	}
}