/*package com.example.birdnote.list;

import org.json.JSONObject;

import com.example.birdnote.model.Bird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	LayoutInflater inflater=null;
	Bird bird;
	
	String [] parentList = {"Status", "Identification", "Diet", "Breeding", "Wintering Habits", "Where to See", "Conservation Status"};
	
	MyExpandableListAdapter(LayoutInflater inflater, Bird bird) {
	    this.inflater = inflater;
	    this.bird = bird;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return parentList[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return(groupPosition);
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		 if (convertView == null) {
		      convertView=
		          inflater.inflate(android.R.layout.simple_expandable_list_item_1,
		                           parent, false);
		    }

		    TextView tv=
		        ((TextView)convertView.findViewById(android.R.id.text1));
		    tv.setText(getGroup(groupPosition).toString());

		    return(convertView);
		  }

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
*/