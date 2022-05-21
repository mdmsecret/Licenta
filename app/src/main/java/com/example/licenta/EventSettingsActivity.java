package com.example.licenta;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.licenta.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventSettingsActivity extends Activity
{
    // Array of strings...
    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_settings);
        ListView listView = (ListView) findViewById(R.id.listview);
        final TextView textView = (TextView) findViewById(R.id.textview);
        String[] players = new String[]{"CR7", "Messi", "Hazard", "Neymar"};
        List<String> Players_list = new ArrayList<String>(Arrays.asList(players));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String text="The best football player is : " + selectedItem;
                Toast.makeText(EventSettingsActivity.this, text, Toast.LENGTH_SHORT).show();

            }
        });
    }
}