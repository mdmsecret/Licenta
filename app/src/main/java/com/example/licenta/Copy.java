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

        setContentView(R.layout.event_create);
        dbHandler = new DBEventHandler(Copy.this);
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(Copy.this,
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
        eHourText =(EditText) findViewById(R.id.editTextTime);
        eHourText.setInputType(InputType.TYPE_NULL);
        eHourText.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(Copy.this,
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
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                /*String title = eTitle.getText().toString();
                String hour=eHourText.getText().toString();
                String description=eDescription.getText().toString();
                String place=ePlace.getText().toString();
                String date=eDateText.getText().toString();
                String status_aug=eMaxPers.getText().toString();
                Integer status=Integer.parseInt(status_aug);
                ArrayList<String> participantsArray=new ArrayList<String>();
                participantsArray.add("apa");
                participantsArray.add("apa2");
                if (title.isEmpty()||hour.isEmpty()||description.isEmpty()||place.isEmpty()||date.isEmpty()||status_aug.isEmpty()) {
                    Toast.makeText(Copy.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //String owner=getCurrentUser();
                int i;
                for (i=0;i<15;i++) {
                    System.out.println("yes");
                    String id = UUID.randomUUID().toString();
                    String title = UUID.randomUUID().toString();;
                    String hour=UUID.randomUUID().toString();;
                    String description=UUID.randomUUID().toString();;
                    String place=UUID.randomUUID().toString();;
                    String date=UUID.randomUUID().toString();
                    String status_aug=UUID.randomUUID().toString();
                    Integer status=1;
                    ArrayList<String> participantsArray=new ArrayList<String>();
                    participantsArray.add("apa");
                    participantsArray.add("apa2");
                    dbHandler.addNewEvent(title, description, date, hour, "mdm", status, place, participantsArray);
                }
                // after adding the data we are displaying a toast message.
                //Toast.makeText(RegisterActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Copy.this, MainPage.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish(); // Call once you redirect to another activity


            }
        });


    }
}
