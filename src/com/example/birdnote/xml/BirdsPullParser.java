package com.example.birdnote.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.birdnote.model.Bird;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

public class BirdsPullParser {

	private static final String LOGTAG = "BIRDS";
	
	private static final String BIRD_ID = "id";
	private static final String BIRD_NAME = "name";
	private static final String BIRD_LATIN_NAME = "latin";
	private static final String BIRD_STATUS = "status";
	private static final String BIRD_IDENTIFICATION = "identification";
	private static final String BIRD_DIET = "diet";
	private static final String BIRD_BREEDING = "breeding";
	private static final String BIRD_WINTERING_HABITS = "wintering_habits";
	private static final String BIRD_WHERE_TO_SEE = "where_to_see";
	private static final String BIRD_CONSERVATION = "conservation_status";
	private static final String BIRD_IMAGE_THUMB = "image_thumb";
	private static final String BIRD_IMAGE_LARGE = "image_large";
	private static final String BIRD_PRIMARY_COLOUR = "primary_colour_id";
	private static final String BIRD_SECONDARY_COLOUR = "secondary_colour_id";
	private static final String BIRD_CROWN_COLOUR = "crown_colour_id";
	private static final String BIRD_BILL_LENGTH = "bill_length_id";
	private static final String BIRD_BILL_COLOUR = "bill_colour_id";
	private static final String BIRD_TAIL_SHAPE = "tail_shape_id";
	
	private Bird currentBird  = null;
	private String currentTag = null;
	List<Bird> birds = new ArrayList<Bird>();

	public List<Bird> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.getResources().openRawResource(com.example.birdnote.R.raw.birds);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return birds;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentBird != null && currentTag != null) {
			if (currentTag.equals(BIRD_ID)) {
				Integer id = Integer.parseInt(xmlText);
				currentBird.setId(id);
			} 
			else if (currentTag.equals(BIRD_NAME)) {
				currentBird.setName(xmlText);
			}
			else if (currentTag.equals(BIRD_LATIN_NAME)) {
				currentBird.setLatinName(xmlText);
			}
			else if (currentTag.equals(BIRD_STATUS)) {
				currentBird.setStatus(xmlText);
			}
			else if (currentTag.equals(BIRD_IDENTIFICATION)) {
				currentBird.setIdentification(xmlText);
			}
			else if (currentTag.equals(BIRD_DIET)) {
				currentBird.setDiet(xmlText);
			}
			else if (currentTag.equals(BIRD_BREEDING)) {
				currentBird.setBreeding(xmlText);
			}
			else if (currentTag.equals(BIRD_WINTERING_HABITS)) {
				currentBird.setWinteringHabits(xmlText);
			}
			else if (currentTag.equals(BIRD_WHERE_TO_SEE)) {
				currentBird.setWhereToSee(xmlText);
			}
			else if (currentTag.equals(BIRD_CONSERVATION)) {
				currentBird.setConservation(xmlText);
			}
			else if (currentTag.equals(BIRD_IMAGE_THUMB)) {
				currentBird.setImageThumb(xmlText);
			}
			else if (currentTag.equals(BIRD_IMAGE_LARGE)) {
				currentBird.setImageLarge(xmlText);
			}
			else if (currentTag.equals(BIRD_PRIMARY_COLOUR)) {
				Integer pColour = Integer.parseInt(xmlText);
				currentBird.setPrimaryColour(pColour);
			}
			else if (currentTag.equals(BIRD_SECONDARY_COLOUR)) {
				Integer sColour = Integer.parseInt(xmlText);
				currentBird.setSecondaryColour(sColour);
			}
			else if (currentTag.equals(BIRD_CROWN_COLOUR)) {
				Integer cColour = Integer.parseInt(xmlText);
				currentBird.setCrownColour(cColour);
			}
			else if (currentTag.equals(BIRD_BILL_LENGTH)) {
				Integer bLength = Integer.parseInt(xmlText);
				currentBird.setBillLength(bLength);
			}
			else if (currentTag.equals(BIRD_BILL_COLOUR)) {
				Integer bColour = Integer.parseInt(xmlText);
				currentBird.setBillColour(bColour);
			}
			else if (currentTag.equals(BIRD_TAIL_SHAPE)) {
				Integer tShape = Integer.parseInt(xmlText);
				currentBird.setTailShape(tShape);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("bird")) {
			currentBird = new Bird();
			birds.add(currentBird);
		}
		else {
			currentTag = name;
		}
	}
}