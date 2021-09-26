package com.seva_buddyv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class make_appointment extends AppCompatActivity {
    CalendarView calendarView;
    Button button2;
    EditText editTextTime;
    Uploads list;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        button2 = (Button)findViewById( R.id.button2 );
        calendarView = (CalendarView)findViewById( R.id.calendarView );
        editTextTime = (EditText)findViewById( R.id.editTextTime );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = String.valueOf( calendarView.getDate() );
                add_data(date,editTextTime.getText().toString());
            }
        } );
    }

    private void add_data(String date, String message) {
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments");
        list = new Uploads();

        list.setdate(date);
        list.setmessage(message);
        databaseReference.setValue(list);

    }
}