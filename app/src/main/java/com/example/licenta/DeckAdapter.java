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

    private final ArrayList<Event> eventData;
    private final Context context;

    public DeckAdapter(ArrayList<Event> eventData, Context context) {
        this.eventData = eventData;
        this.context = context;
    }

    @Override
    public int getCount() {
       return eventData.size();
    }

    @Override
    public Object getItem(int position) {
        return eventData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        }
       ((TextView) v.findViewById(R.id.idTVCourseName)).setText(eventData.get(position).getTitle());
        ((TextView) v.findViewById(R.id.idTVCourseDescription)).setText(eventData.get(position).getDescription());
        ((TextView) v.findViewById(R.id.idTVCourseDuration)).setText(eventData.get(position).getHour());
        ((TextView) v.findViewById(R.id.idTVCourseTracks)).setText(eventData.get(position).getPlace());
        return v;
    }
}