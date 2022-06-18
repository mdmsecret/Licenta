package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventSettingsActivity2 extends Activity
{
    private RecyclerView eventRV;

    private ArrayList<ListModel> eventModelArrayList;
    private DBEventHandler eventHandler;
    private ArrayList<Event> array;
    private TextView event;
    private Button notInterestedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_settings2);
        eventRV = findViewById(R.id.idRVEvent);
        eventHandler = new DBEventHandler(EventSettingsActivity2.this);
        array = eventHandler.readEvent();


        eventModelArrayList = new ArrayList<>();
        String currUser = getCurrentUser();
        ArrayList<String> dummy = new ArrayList<>();
        if (array.size() == 0) {
            eventModelArrayList.add(new ListModel("Dummy", "Dummy", "Dummy", new Event("Dummy", "Dummy", "Dummy", "Dummy", "Dummy", "Dummy", "Dummy", dummy, 1)));
        } else {

            for (int i = 0; i < array.size(); i++) {
                Event event_aug = array.get(i);
                if (event_aug.getOwner().equals(currUser)) {
                    eventModelArrayList.add(new ListModel( "Date:"+event_aug.getDate(),event_aug.getTitle(), "Hour:\n"+event_aug.getHour(), event_aug));
                }
            }
        }
        ListAdapter courseAdapter = new ListAdapter(this, eventModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        eventRV.setLayoutManager(linearLayoutManager);
        eventRV.setAdapter(courseAdapter);
        event=findViewById(R.id.textView14);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EventSettingsActivity2.this, EventSettingsActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    }
