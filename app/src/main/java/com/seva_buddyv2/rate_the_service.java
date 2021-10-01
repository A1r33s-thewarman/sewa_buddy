package com.seva_buddyv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

public class rate_the_service extends AppCompatActivity {
 Button rateb;
 RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_the_service);

        rate = (RatingBar)findViewById(R.id.rate);
        rateb = (Button) findViewById(R.id.rateb);




    }
}