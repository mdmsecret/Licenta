package com.example.licenta;
import static com.example.licenta.MainActivity.getCurrentUser;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

public class CreateEventActivity extends AppCompatActivity {


    TimePickerDialog picker;
    EditText eHourText,eDateText,eTitle,eDescription,ePlace,eMaxPers;
    Button btnCreate;

    private DBEventHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_create);
        dbHandler = new DBEventHandler(CreateEventActivity.this);
        btnCreate=findViewById(R.id.buttonCreateEvent);
        eHourText = findViewById(R.id.editTextTime);
        eTitle = findViewById(R.id.editTextTitle);
        eDescription=findViewById(R.id.editTextDescription);
        ePlace=findViewById(R.id.editTextPlace);
        eMaxPers=findViewById(R.id.editTextMaxPers);
        eDateText=findViewById(R.id.editTextDate);
        eDateText.setInputType(InputType.TYPE_NULL);
        eDateText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                eDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        eHourText = findViewById(R.id.editTextTime);
        eHourText.setInputType(InputType.TYPE_NULL);
        eHourText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eHourText.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = eTitle.getText().toString();
                String hour=eHourText.getText().toString();
                String description=eDescription.getText().toString();
                String place=ePlace.getText().toString();
                String date=eDateText.getText().toString();
                String status_aug=eMaxPers.getText().toString();
                Integer status=Integer.parseInt(status_aug);
                ArrayList<String> participantsArray=new ArrayList<String>();

                if (title.isEmpty()||hour.isEmpty()||description.isEmpty()||place.isEmpty()||date.isEmpty()||status_aug.isEmpty()) {
                    Toast.makeText(CreateEventActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                String owner=getCurrentUser();
                dbHandler.addNewEvent(title,description,date,hour,owner,status,place,participantsArray);


                Intent intent = new Intent(CreateEventActivity.this, MainPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });


    }
}
