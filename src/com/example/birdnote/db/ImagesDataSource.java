//package com.example.birdnote.db;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.birdnote.model.Image;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class ImagesDataSource {
//
//	private static final String LOGTAG = "Images";
//	
//	SQLiteOpenHelper dbhelper;
//	SQLiteDatabase database;
//	
//	// array of string references for all columns in birds table
//	private static final String[] imagesAllColumns = {
//		BirdsDBOpenHelper.IMAGE_COLUMN_ID,
//		BirdsDBOpenHelper.IMAGE_COLUMN_THUMB,
//		BirdsDBOpenHelper.IMAGE_COLUMN_MAIN_PIC
//		};
//	
//	public ImagesDataSource(Context context) {
//		dbhelper = new BirdsDBOpenHelper(context);
//	}
//	
//	// open db
//	public void open() {
//		Log.i(LOGTAG, "Database Open.");
//		database = dbhelper.getWritableDatabase();
//	}
//
//	// close db
//	public void close() {
//		Log.i(LOGTAG, "Database Closed.");
//		dbhelper.close();
//	}
//	
//	// create record in db
//	public Image create(Image image) {
//		
//		ContentValues values = new ContentValues();
//		
//		values.put(BirdsDBOpenHelper.IMAGE_COLUMN_THUMB, image.getThumb());
//		values.put(BirdsDBOpenHelper.IMAGE_COLUMN_MAIN_PIC, image.getMainPic());
//		
//		long insertid = database.insert(BirdsDBOpenHelper.TABLE_IMAGES, null, values);
//		image.setId(insertid);
//		return image;
//	}
//	
//	// returns all rows from from image table
//	public List<Image> findAll() {
//		
//		// set up cursor to hold db query result
//		Cursor cursor = database.query(BirdsDBOpenHelper.TABLE_IMAGES, imagesAllColumns, null, null, null, null, null);
//		
//		List<Image> images = cursorToList(cursor);
//		
//		return images;
//	}
//	
//	// converts cursor to list
//	private List<Image> cursorToList(Cursor cursor) {
//		
//		List<Image> images = new ArrayList<Image>();
//		
//		if (cursor.getCount() > 0) {
//			while (cursor.moveToNext()) {
//				Image image  = new Image();
//				
//				image.setId(cursor.getLong(cursor.getColumnIndex(BirdsDBOpenHelper.IMAGE_COLUMN_ID)));
//				image.setThumb(cursor.getString(cursor.getColumnIndex(BirdsDBOpenHelper.IMAGE_COLUMN_THUMB)));
//				image.setMainPic(cursor.getString(cursor.getColumnIndex(BirdsDBOpenHelper.IMAGE_COLUMN_MAIN_PIC)));
//				
//				images.add(image);
//			}
//			Log.i(LOGTAG, "There are " + cursor.getColumnCount() + " columns in the image table.");
//		}
//		return images;
//	}
//}
