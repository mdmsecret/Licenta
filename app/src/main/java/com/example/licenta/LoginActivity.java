package com.example.licenta;

import static com.example.licenta.MainActivity.setCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText userEditText,passwordEditText;
    private Button loginButton,registerButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // initializing all our variables.
        userEditText = findViewById(R.id.idUserNameText);
        passwordEditText = findViewById(R.id.idPasswordText);
        loginButton = findViewById(R.id.idLoginButton);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(LoginActivity.this);
        registerButton=findViewById(R.id.idRegisterButtonLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);// New activity
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });

        // below line is to add on click listener for our add course button.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ok=0;
                // below line is to get data from all edit text fields.
                String userText = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                ArrayList<Users> usersArrayList= dbHandler.readUsers();
                try {
                    password=AESUtils.encrypt(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for(Users us : usersArrayList) {
                    ok=0;
                    if (us.getUsername().equals(userText) && us.getPassword().equals(password) && us.getStatus().equals(0)) {
                        setCurrentUser(userText);
                        ok=1;
                        dbHandler.updateUser(us.getLastName(),us.getFirstName(),us.getUsername(),us.getPassword(),us.getEmail(),1);
                        Intent intent = new Intent(LoginActivity.this, MainPage.class);// New activity
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        //finish();
                    }
                }
                if (ok==0) {
                    Toast.makeText(LoginActivity.this, "User sau parola incorecte", Toast.LENGTH_SHORT).show();
                }
                // validating if the text fields are empty or not.

            }
        });
    }
}
