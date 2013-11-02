package com.example.birdnote.db;

import java.util.ArrayList;
import java.util.List;

import com.example.birdnote.model.BillLength;
import com.example.birdnote.model.Colour;
import com.example.birdnote.model.TailShape;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class IdentifyDataSource {
	
	private static final String [] allBillLengthCols = {
		BirdsDBOpenHelper.BILL_LENGTH_COLUMN_ID,
		BirdsDBOpenHelper.BILL_LENGTH_COLUMN_LENGTH };
	
	private static final String [] allColourCols = {
		BirdsDBOpenHelper.COLOUR_COLUMN_ID,
		BirdsDBOpenHelper.COLOUR_COLUMN_COLOUR };
	
	private static final String [] allTailCols = {
		BirdsDBOpenHelper.TAIL_SHAPE_COLUMN_ID,
		BirdsDBOpenHelper.TAIL_SHAPE_COLUMN_SHAPE };

	private static final String LOGTAG = "Birds";
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	public IdentifyDataSource(Context context) {
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
	 * BillLength Table
	 * 
	 * */
	// create record in billLength table
	public BillLength createBillLength(BillLength billLength) {
		
		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.BILL_LENGTH_COLUMN_LENGTH, billLength.getBillLength());
		long insertid = database.insert(BirdsDBOpenHelper.TABLE_BILL_LENGTH, null, values);
		billLength.setId(insertid);
		
		return billLength;
	}

	// return all rows from billLength table
	public List<BillLength> findAllBillLengthValues() {
		
		List<BillLength> billLengths = new ArrayList<BillLength>();
		
		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_BILL_LENGTH, allBillLengthCols, null, null, null, null, null);
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows from Bill Length table");
		
		if (cursor.getColumnCount() > 0) {
			while (cursor.moveToNext()) {
				BillLength billLength = new BillLength();
				billLength.setId(cursor.getLong(cursor.getColumnIndex(BirdsDBOpenHelper.BILL_LENGTH_COLUMN_ID)));
				billLength.setBillLength(cursor.getString(cursor.getColumnIndex(BirdsDBOpenHelper.BILL_LENGTH_COLUMN_LENGTH)));
				billLengths.add(billLength);
			}
		}
		return billLengths;
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
	 * Colour Table
	 * 
	 * */
	// create record in colour table
	public Colour createColour(Colour colour) {
		
		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.COLOUR_COLUMN_COLOUR, colour.getColour());
		long insertid = database.insert(BirdsDBOpenHelper.TABLE_COLOUR, null, values);
		colour.setId(insertid);
		
		return colour;
	}

	// return all rows from colour table
	public List<Colour> findAllColourValues() {
		
		List<Colour> colours = new ArrayList<Colour>();
		
		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_COLOUR, allColourCols, null, null, null, null, null);
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows from Colour table");
		
		if (cursor.getColumnCount() > 0) {
			while (cursor.moveToNext()) {
				Colour colour = new Colour();
				colour.setId(cursor.getLong(cursor.getColumnIndex(BirdsDBOpenHelper.COLOUR_COLUMN_ID)));
				colour.setColour(cursor.getString(cursor.getColumnIndex(BirdsDBOpenHelper.COLOUR_COLUMN_COLOUR)));
				colours.add(colour);
			}
		}
		return colours;
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
	 * Tail Shape Table
	 * 
	 * */
	// create record in tail table
	public TailShape createShape(TailShape shape) {
		
		ContentValues values = new ContentValues();

		values.put(BirdsDBOpenHelper.TAIL_SHAPE_COLUMN_SHAPE, shape.getShape());
		long insertid = database.insert(BirdsDBOpenHelper.TABLE_TAIL_SHAPE, null, values);
		shape.setId(insertid);
		
		return shape;
	}

	// return all rows from tail table
	public List<TailShape> findAllTailShapeValues() {
		
		List<TailShape> shapes = new ArrayList<TailShape>();
		
		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_TAIL_SHAPE, allTailCols, null, null, null, null, null);
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows from Tail Shape table");
		
		if (cursor.getColumnCount() > 0) {
			while (cursor.moveToNext()) {
				TailShape shape = new TailShape();
				shape.setId(cursor.getLong(cursor.getColumnIndex(BirdsDBOpenHelper.TAIL_SHAPE_COLUMN_ID)));
				shape.setShape(cursor.getString(cursor.getColumnIndex(BirdsDBOpenHelper.TAIL_SHAPE_COLUMN_SHAPE)));
				shapes.add(shape);
			}
		}
		return shapes;
	}
}
