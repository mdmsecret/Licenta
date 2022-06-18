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


        userEditText = findViewById(R.id.idUserNameText);
        passwordEditText = findViewById(R.id.idPasswordText);
        loginButton = findViewById(R.id.idLoginButton);


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


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ok=0;

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
                        Intent intent = new Intent(LoginActivity.this, MainPage.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        //finish();
                    }
                }
                if (ok==0) {
                    Toast.makeText(LoginActivity.this, "User sau parola incorecte", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
