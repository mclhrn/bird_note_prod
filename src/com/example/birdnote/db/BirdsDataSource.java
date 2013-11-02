package com.example.birdnote.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.birdnote.model.Bird;
import com.example.birdnote.model.BirdsSeenModel;

public class BirdsDataSource {

	private static final String LOGTAG = "Birds";
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;

	// array of string references for all columns in birds table
	private static final String[] birdsAllColumns = {
			BirdsDBOpenHelper.BIRDS_COLUMN_ID,
			BirdsDBOpenHelper.BIRDS_COLUMN_NAME,
			BirdsDBOpenHelper.BIRDS_COLUMN_LATIN_NAME,
			BirdsDBOpenHelper.BIRDS_COLUMN_STATUS,
			BirdsDBOpenHelper.BIRDS_COLUMN_IDENTIFICATION,
			BirdsDBOpenHelper.BIRDS_COLUMN_DIET,
			BirdsDBOpenHelper.BIRDS_COLUMN_BREEDING,
			BirdsDBOpenHelper.BIRDS_COLUMN_WINTERING_HABITS,
			BirdsDBOpenHelper.BIRDS_COLUMN_WHERE_TO_SEE,
			BirdsDBOpenHelper.BIRDS_COLUMN_CONSERVATION,
			BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_THUMB,
			BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_LARGE,
			BirdsDBOpenHelper.BIRDS_COLUMN_PRIMARY_COLOUR,
			BirdsDBOpenHelper.BIRDS_COLUMN_SECONDARY_COLOUR,
			BirdsDBOpenHelper.BIRDS_COLUMN_CROWN_COLOUR,
			BirdsDBOpenHelper.BIRDS_COLUMN_BILL_COLOUR,
			BirdsDBOpenHelper.BIRDS_COLUMN_BILL_LENGTH,
			BirdsDBOpenHelper.BIRDS_COLUMN_TAIL_SHAPE };

	public BirdsDataSource(Context context) {
		dbhelper = new BirdsDBOpenHelper(context);
	}

	// open db
	public void open() {
		Log.i(LOGTAG, "Database Open.");
		database = dbhelper.getWritableDatabase();
	}

	// close db
	public void close() {
		Log.i(LOGTAG, "Database Closed.");
		dbhelper.close();
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Main Bird Table
	 */
	// create record in bird table
	public Bird create(Bird bird) {

		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_NAME, bird.getName());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_LATIN_NAME,
				bird.getLatinName());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_STATUS, bird.getStatus());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_IDENTIFICATION,
				bird.getIdentification());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_DIET, bird.getDiet());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_BREEDING, bird.getBreeding());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_WINTERING_HABITS,
				bird.getWinteringHabits());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_WHERE_TO_SEE,
				bird.getWhereToSee());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_CONSERVATION,
				bird.getConservation());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_THUMB,
				bird.getImageThumb());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_LARGE,
				bird.getImageLarge());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_PRIMARY_COLOUR,
				bird.getPrimaryColour());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_SECONDARY_COLOUR,
				bird.getSecondaryColour());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_CROWN_COLOUR,
				bird.getCrownColour());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_BILL_LENGTH,
				bird.getBillLength());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_BILL_COLOUR,
				bird.getBillColour());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_TAIL_SHAPE,
				bird.getTailShape());

		long insertid = database.insert(BirdsDBOpenHelper.TABLE_BIRDS, null,
				values);
		bird.setId(insertid);
		return bird;
	}

	// returns all rows from from bird table
	public List<Bird> findAll() {

		// Set up cursor to hold db query result
		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_BIRDS,
				birdsAllColumns, null, null, null, null, null);
		Log.i(LOGTAG, "There are " + cursor.getColumnCount()
				+ " columns in the Birds table.");

		List<Bird> birds = cursorToList(cursor);

		return birds;
	}

	// toggle output of reference guide
	public List<Bird> refAtoZ() {
		// Set up cursor to hold db query result
		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_BIRDS,
				birdsAllColumns, null, null, null, null,
				BirdsDBOpenHelper.BIRDS_COLUMN_NAME + " desc");
		Log.i(LOGTAG, "There are " + cursor.getColumnCount()
				+ " columns in the Birds table.");

		List<Bird> birds = cursorToList(cursor);

		return birds;
	}

	// converts cursor to a list for bird table
	private List<Bird> cursorToList(Cursor cursor) {

		List<Bird> birds = new ArrayList<Bird>();

		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Bird bird = new Bird();

				bird.setId(cursor.getLong(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_ID)));
				bird.setName(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_NAME)));
				bird.setLatinName(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_LATIN_NAME)));
				bird.setStatus(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_STATUS)));
				bird.setIdentification(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_IDENTIFICATION)));
				bird.setDiet(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_DIET)));
				bird.setBreeding(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_BREEDING)));
				bird.setWinteringHabits(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_WINTERING_HABITS)));
				bird.setWhereToSee(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_WHERE_TO_SEE)));
				bird.setConservation(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_CONSERVATION)));
				bird.setImageThumb(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_THUMB)));
				bird.setImageLarge(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_IMAGE_LARGE)));
				bird.setPrimaryColour(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_PRIMARY_COLOUR)));
				bird.setSecondaryColour(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_SECONDARY_COLOUR)));
				bird.setCrownColour(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_CROWN_COLOUR)));
				bird.setBillLength(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_BILL_LENGTH)));
				bird.setBillColour(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_BILL_COLOUR)));
				bird.setTailShape(cursor.getInt(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_TAIL_SHAPE)));

				birds.add(bird);
			}
		}
		return birds;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * BirdsSeen Table
	 */
	// adds a bird to the birdsSeen table
	public boolean addToBirdsSeen(Bird bird, double lat, double lng) {
		// Log.i(LOGTAG, bird.toString());
		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_ID, bird.getId());
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_LATITUDE, lat);
		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_LONGITUDE, lng);

		long result = database.insert(BirdsDBOpenHelper.TABLE_BIRDS_SEEN, null,
				values);

		return (result != -1);
	}

	// remove a bird from the birdsSeen table
	public boolean removeFromBirdsSeen(Bird bird) {
		String where = BirdsDBOpenHelper.BIRDS_COLUMN_ID + "=" + bird.getId();
		int result = database.delete(BirdsDBOpenHelper.TABLE_BIRDS_SEEN, where,
				null);
		return (result == 1);
	}

	// retrieves all birds from birdsSeen table
	public List<Bird> findBirdsSeen() {

		String query = "SELECT birds.* FROM " + "birds JOIN birdsSeen ON "
				+ "birds.bird_id = birdsSeen.bird_id";
		Cursor cursor = database.rawQuery(query, null);

		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");

		List<Bird> birds = cursorToList(cursor);
		return birds;
	}

	// toggle output of birds seen list
	public List<Bird> seenAtoZ() {
		String query = "SELECT birds.* FROM " + "birds JOIN birdsSeen ON "
				+ "birds.bird_id = birdsSeen.bird_id ORDER BY "
				+ BirdsDBOpenHelper.BIRDS_COLUMN_ID + " DESC";
		Cursor cursor = database.rawQuery(query, null);

		List<Bird> birds = cursorToList(cursor);
		return birds;
	}

	// retrieves all birds from birdsSeen table for display on map
	public List<BirdsSeenModel> findBirdsForMap() {

		// Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_BIRDS_SEEN,
		// birdsSeenColumns, null, null, null, null, null);

		String query = "Select birds.name, birdsSeen.latitude, birdsSeen.longitude FROM birds, birdsSeen where birds.bird_id=birdsSeen.bird_id ";
		Cursor cursor = database.rawQuery(query, null);

		List<BirdsSeenModel> birdsSeen = cursorToLocationList(cursor);

		return birdsSeen;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Wishlist Table
	 */
	// adds a bird to the wishList table
	public boolean addToWishList(Bird bird) {
		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.BIRDS_COLUMN_ID, bird.getId());
		long result = database.insert(BirdsDBOpenHelper.TABLE_BIRDS_WISHLIST,
				null, values);

		return (result != -1);
	}

	// remove a bird from the wishList table
	public boolean removeFromWishList(Bird bird) {
		String where = BirdsDBOpenHelper.BIRDS_COLUMN_ID + "=" + bird.getId();
		int result = database.delete(BirdsDBOpenHelper.TABLE_BIRDS_WISHLIST,
				where, null);
		return (result == 1);
	}

	// retrieves all birds from wishList table
	public List<Bird> findWishList() {
		String query = "SELECT birds.* FROM " + "birds JOIN wishList ON "
				+ "birds.bird_id = wishList.bird_id";
		Cursor cursor = database.rawQuery(query, null);

		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");

		List<Bird> birds = cursorToList(cursor);
		return birds;
	}

	// toggle output of wishlist
	public List<Bird> wishAtoZ() {
		String query = "SELECT birds.* FROM " + "birds JOIN wishList ON "
				+ "birds.bird_id = wishList.bird_id ORDER BY "
				+ BirdsDBOpenHelper.BIRDS_COLUMN_ID + " DESC";
		Cursor cursor = database.rawQuery(query, null);

		List<Bird> birds = cursorToList(cursor);
		return birds;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * BirdsSeen Table for MapView display
	 */
	// cursor to list for map view
	private List<BirdsSeenModel> cursorToLocationList(Cursor cursor) {

		List<BirdsSeenModel> birdsSeenList = new ArrayList<BirdsSeenModel>();

		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				BirdsSeenModel birdsSeen = new BirdsSeenModel();

				birdsSeen.setName(cursor.getString(cursor
						.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_NAME)));
				birdsSeen
						.setLatitude(cursor.getDouble(cursor
								.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_LATITUDE)));
				birdsSeen
						.setLongitude(cursor.getDouble(cursor
								.getColumnIndex(BirdsDBOpenHelper.BIRDS_COLUMN_LONGITUDE)));

				birdsSeenList.add(birdsSeen);
			}
		}
		Log.i(LOGTAG, "Returned " + cursor.getCount()
				+ " rows in map view display");
		return birdsSeenList;
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *  returns user search results 
	 */
	
	public List<Bird> userSearch(long currentPrimaryColourId,
			long currentSecondaryColourId, long currentCrownColourId,
			long currentBillLengthId, long currentBillColourId,
			long currentTailShapeId, String orderBy) {

		String whereClause = null;

		if(currentPrimaryColourId > 0){
			whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_PRIMARY_COLOUR + " = " + currentPrimaryColourId;	
		}
		if(currentSecondaryColourId > 0){
			if(whereClause == null)
				whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_SECONDARY_COLOUR + " = " + currentSecondaryColourId;
			else
				whereClause = whereClause + " and " + BirdsDBOpenHelper.BIRDS_COLUMN_SECONDARY_COLOUR + " = " + currentSecondaryColourId;	
		}
		if(currentCrownColourId > 0){
			if(whereClause == null)
				whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_CROWN_COLOUR + " = " + currentCrownColourId;
			else
				whereClause = whereClause + " and " + BirdsDBOpenHelper.BIRDS_COLUMN_CROWN_COLOUR + " = " + currentCrownColourId;	
		}
		if(currentBillLengthId > 0){
			if(whereClause == null)
				whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_BILL_LENGTH + " = " + currentBillLengthId;
			else
				whereClause = whereClause + " and " + BirdsDBOpenHelper.BIRDS_COLUMN_BILL_LENGTH + " = " + currentBillLengthId;	
		}
		if(currentBillColourId > 0){
			if(whereClause == null)
				whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_BILL_COLOUR + " = " + currentBillColourId;
			else
				whereClause = whereClause + " and " + BirdsDBOpenHelper.BIRDS_COLUMN_BILL_COLOUR + " = " + currentBillColourId;	
		}
		if(currentTailShapeId > 0){
			if(whereClause == null)
				whereClause = BirdsDBOpenHelper.BIRDS_COLUMN_TAIL_SHAPE + " = " + currentTailShapeId;
			else
				whereClause = whereClause + " and " + BirdsDBOpenHelper.BIRDS_COLUMN_TAIL_SHAPE + " = " + currentTailShapeId;	
		}
		
		 // Set up cursor to hold db query result
		 Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_BIRDS,
		 birdsAllColumns, whereClause, null, null, null, orderBy);

		List<Bird> birds = cursorToList(cursor);

		return birds;
	}
}