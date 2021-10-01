package com.seva_buddyv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class make_appointment extends AppCompatActivity {
    CalendarView calender;
    Button button2;
    EditText editTextTime;

    String Key, sDate;
    Uploads list;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        button2 = (Button)findViewById( R.id.button2 );
        calender = (CalendarView)findViewById( R.id.calendarView2 );
        editTextTime = (EditText)findViewById( R.id.editTextTime );

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Key = extras.getString("key");


        }




        calender = (CalendarView)findViewById(R.id.calendarView2);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {


                sDate = dayOfMonth +"/" + (month+1) + "/" + year;
            }
        });







        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(make_appointment.this,sDate,Toast.LENGTH_SHORT).show();


                add_data(sDate,editTextTime.getText().toString());


            }
        } );
    }



    private void add_data(String date, String message) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments").child(user.getUid().toString());


        make_appointment_upload upload = new make_appointment_upload(date,message,Key);



        databaseReference.setValue(upload);




    }



}