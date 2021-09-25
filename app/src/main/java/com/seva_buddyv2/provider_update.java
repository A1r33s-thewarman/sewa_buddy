package com.seva_buddyv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seva_buddyv2.ui.ad_update;

public class provider_update extends AppCompatActivity {
    EditText title,location,h_rate;
    //firebase connectivity
    private FirebaseAuth mAuth;

    FirebaseUser user;
    ad_update ad_updater;
    DatabaseReference reference ;
    Button buttonl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //input details
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_update);


        title = (EditText)findViewById( R.id.title);
        location = (EditText)findViewById( R.id. location);
        h_rate = (EditText)findViewById( R.id.rate );


        buttonl = (Button)findViewById( R.id.view );
        buttonl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        } );

    }
    private void update() {

        //data insertion

        ad_updater = new ad_update();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("uploads");

        ad_updater.settitle(title.getText().toString());
        ad_updater.setlocation(location.getText().toString() );
        ad_updater.setrate(h_rate.getText().toString() );


        reference.child(user.getUid()).setValue(ad_updater);
        Intent intent = new Intent( provider_update.this, provide_home.class);
        startActivity(intent);

    }
}