package com.example.licenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.licenta.Event;
import com.example.licenta.R;

import java.util.ArrayList;

public class DeckAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private final ArrayList<Event> eventData;
    private final Context context;

    // on below line we have created constructor for our variables.
    public DeckAdapter(ArrayList<Event> eventData, Context context) {
        this.eventData = eventData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return eventData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return eventData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.idTVCourseName)).setText(eventData.get(position).getTitle());
        ((TextView) v.findViewById(R.id.idTVCourseDescription)).setText(eventData.get(position).getDescription());
        ((TextView) v.findViewById(R.id.idTVCourseDuration)).setText(eventData.get(position).getHour());
        ((TextView) v.findViewById(R.id.idTVCourseTracks)).setText(eventData.get(position).getPlace());
        //((ImageView) v.findViewById(R.id.idIVCourse)).setImageResource(eventData.get(position).getImgId());
        return v;
    }
}