package com.example.licenta;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import java.util.UUID;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Copy extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    TimePickerDialog picker;
    EditText eHourText,eDateText,eTitle,eDescription,ePlace,eMaxPers;
    Button btnCreate;
    TextView tvw;
    private DBEventHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_page);


    }
}
