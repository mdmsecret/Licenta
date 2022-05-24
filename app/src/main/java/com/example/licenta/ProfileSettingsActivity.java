package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.DBHandler;
import com.example.licenta.LoginActivity;
import com.example.licenta.R;
import com.example.licenta.RegisterActivity;

import java.util.ArrayList;

public class ProfileSettingsActivity extends AppCompatActivity {
    private Button saveChangesButton, deleteUserButton;
    private EditText firstName,lastName,username,email,password,confirmPassword;
    private DBHandler dbHandler;

    public static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_settings);

        saveChangesButton = findViewById(R.id.saveChangesButton);
        deleteUserButton =findViewById(R.id.deleteUserButton);
        dbHandler = new DBHandler(ProfileSettingsActivity.this);
        firstName=findViewById(R.id.editFistName);
        lastName=findViewById(R.id.editLastName);
        //username=findViewById(R.id.editUsername);
        email=findViewById(R.id.editEmail);
        password=findViewById(R.id.editPassword);
        confirmPassword=findViewById(R.id.editCofirmPassword);
        //String currentUser= getCurrentUser();
        String currentUser="mdmsecret";
        ArrayList<Users> usersArrayList= dbHandler.readUsers();
        for(Users us : usersArrayList) {
            if (us.getUsername().equals(currentUser) ) {
                firstName.setText(us.getFirstName());
                lastName.setText(us.getLastName());
                //username.setText(us.getUsername());
                email.setText(us.getEmail());
                //password.setText(us.getPassword());

            }
        }
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.updateUser(lastName.getText().toString(),firstName.getText().toString(),currentUser,email.getText().toString(),password.getText().toString(),1);
                Intent intent = new Intent(ProfileSettingsActivity.this, OptionsActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Title");
                builder.setMessage("Message");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHandler.deleteUser(getCurrentUser());
                                // Toast.makeText(MainPage.this, getCurrentUser(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileSettingsActivity.this, MainActivity.class);// New activity
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