package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventSettingsActivity2 extends Activity
{
    private RecyclerView courseRV;

    // Arraylist for storing data
    private ArrayList<ListModel> courseModelArrayList;
    private DBEventHandler eventHandler;
    private ArrayList<Event> array;
    private Button notInterestedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_settings2);
        courseRV = findViewById(R.id.idRVCourse);
        eventHandler= new DBEventHandler(EventSettingsActivity2.this);
        array = eventHandler.readEvent();

        // here we have created new array list and added data to it.
       /*
        courseModelArrayList.add(new ListModel("DSA in Java", 4, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("Java Course", 3, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("C++ COurse", 4, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("DSA in C++", 4, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("Kotlin for Android", 4, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("Java for Android", 4, R.drawable.gfgimage));
        courseModelArrayList.add(new ListModel("HTML and CSS", 4, R.drawable.gfgimage));
*/
        courseModelArrayList = new ArrayList<>();
        String currUser=getCurrentUser();
        //courseModelArrayList.add(new ListModel("HTML and CSS","dsa","disao"));
        for (int i = 0; i < array.size(); i++) {
            Event event_aug=array.get(i);
            if(event_aug.getOwner().contains(currUser)){
                courseModelArrayList.add(new ListModel("nr"+i,event_aug.getDate(),event_aug.getHour(),event_aug));
            }
        }

        // we are initializing our adapter class and passing our arraylist to it.
        ListAdapter courseAdapter = new ListAdapter(this, courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

    }

    }
