package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText numeEdt,prenumeEdt,userNameEdt,passwordEdt,passwordVerEdt;
    private Button registerButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // initializing all our variables.
        numeEdt = findViewById(R.id.idNumeTxt);
        prenumeEdt = findViewById(R.id.idPrenumeTxt);
        userNameEdt = findViewById(R.id.idUserNameTxt);
        passwordEdt = findViewById(R.id.idPasswordTxt);
        passwordVerEdt = findViewById(R.id.idPasswordTxtVer);
        registerButton = findViewById(R.id.idRegisterButton);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(RegisterActivity.this);

        // below line is to add on click listener for our add course button.
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String nume = numeEdt.getText().toString();
                String prenume = prenumeEdt.getText().toString();
                String username = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String passwordVer = passwordVerEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (nume.isEmpty() || prenume.isEmpty() || username.isEmpty() || password.isEmpty() || !password.equals(passwordVer)) {
                    Toast.makeText(RegisterActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandler.addNewCourse(nume, prenume, username, password);

                    // after adding the data we are displaying a toast message.
                    Toast.makeText(RegisterActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish(); // Call once you redirect to another activity
                }
            }
        });
    }
}
