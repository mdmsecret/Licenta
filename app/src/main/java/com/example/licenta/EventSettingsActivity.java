package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventSettingsActivity extends Activity
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
        setContentView(R.layout.event_settings);
        courseRV = findViewById(R.id.idRVCourse);
        eventHandler= new DBEventHandler(EventSettingsActivity.this);
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
        //courseModelArrayList.add(new ListModel("HTML and CSS","dsa","disao"));
        for (int i = 0; i < array.size(); i++) {
            Event event_aug=array.get(i);
            String currUser=getCurrentUser();
            if(event_aug.getParticipants().contains(currUser)){
                courseModelArrayList.add(new ListModel( "Date:"+event_aug.getDate(),event_aug.getTitle(), "Hour:\n"+event_aug.getHour(), event_aug));
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
