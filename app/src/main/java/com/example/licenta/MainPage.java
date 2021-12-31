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

public class MainPage extends AppCompatActivity {
    private Button deleteAccountButton;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        deleteAccountButton = findViewById(R.id.idDeleteAccountButton);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainPage.this);

        // below line is to add on click listener for our add course button.
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                builder.setCancelable(true);
                builder.setTitle("Title");
                builder.setMessage("Message");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHandler.deleteUser(getCurrentUser());
                               // Toast.makeText(MainPage.this, getCurrentUser(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainPage.this, MainActivity.class);// New activity
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }
}
