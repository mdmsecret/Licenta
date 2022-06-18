package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;
import static com.example.licenta.MainActivity.setCurrentUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {
    private Button profileSettingsButton,eventSettingsButtons,eventSettingsButtons2,logOut;
    private ArrayList<Users> array;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        dbHandler = new DBHandler(OptionsActivity.this);



        profileSettingsButton = findViewById(R.id.idProfileSettingstButton);



        profileSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, ProfileSettingsActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        eventSettingsButtons=findViewById(R.id.idEventsSettingsButton);

        eventSettingsButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, EventSettingsActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        eventSettingsButtons2=findViewById(R.id.idEventsSettings2Button);

        eventSettingsButtons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, EventSettingsActivity2.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        logOut=findViewById(R.id.button);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currUser = getCurrentUser();
                array = dbHandler.readUsers();


                    for (int i = 0; i < array.size(); i++) {
                        Users us = array.get(i);
                        if (us.getUsername().contains(currUser)) {
                            dbHandler.updateUser(us.getLastName(),us.getFirstName(),us.getUsername(),us.getPassword(),us.getEmail(),0);
                          }
                    }


                Intent intent = new Intent(OptionsActivity.this, StartPageActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }
}
