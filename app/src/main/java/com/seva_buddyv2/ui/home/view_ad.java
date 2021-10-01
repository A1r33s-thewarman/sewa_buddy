package com.seva_buddyv2.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seva_buddyv2.R;
import com.seva_buddyv2.make_appointment;
import com.squareup.picasso.Picasso;

public class view_ad extends AppCompatActivity {
String Img , Desc,H_rate,Loction1,Title,Key;
Float Rate9;

ImageView imgs;
Button appointment ;
TextView ctitle,clocation,ch_rate;
RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ad);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Img = extras.getString("img");
            Key = extras.getString("key");
            Desc = extras.getString("desc");
            H_rate = extras.getString("h_rate");
            Loction1 = extras.getString("location");
            Rate9 = Float.valueOf(extras.getString("rate"));
            Title = extras.getString("title");

        }

        ctitle =(TextView)findViewById(R.id.ctitle) ;
        clocation =(TextView)findViewById(R.id.clocation) ;
        ch_rate =(TextView)findViewById(R.id.ch_rate) ;

        rate =(RatingBar)findViewById(R.id.crate) ;

if(Rate9 == null){
    Rate9 = Float.valueOf(0);
}


        ctitle.setText(Title);
        clocation.setText(Loction1);
        rate.setRating(Rate9);
        ch_rate.setText(H_rate);


        appointment = (Button)findViewById(R.id.appointment);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_ad.this, make_appointment.class);
                i.putExtra("key",Key);
                startActivity(i);
                finish();
            }
        });

        imgs = (ImageView) findViewById(R.id.imgs);


        Picasso.get()
                .load(Img)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imgs);

    }
}