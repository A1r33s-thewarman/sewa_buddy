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
import com.seva_buddyv2.ui.provider_register;
import com.seva_buddyv2.ui.register;

public class provider_AccountCreation extends AppCompatActivity {
EditText Name,address,phone_no,email,nic;
//firebase connectivity
    private FirebaseAuth mAuth;

    FirebaseUser user;
    provider_register p_register;
    DatabaseReference reference ;
    Button buttonl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //input details
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_account_creation);
        Name = (EditText)findViewById( R.id.editTextTextPersonName2);
        address = (EditText)findViewById( R.id. editTextTextMultiLine);
        phone_no = (EditText)findViewById( R.id.editTextPhone );
        email = (EditText)findViewById( R.id.editTextTextEmailAddress );
        nic = (EditText)findViewById( R.id.editTextnic );

        buttonl = (Button)findViewById( R.id.button );
        buttonl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert_data();
            }
        } );

    }
    private void insert_data() {

        //data insertion

        p_register = new provider_register();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("users").child( "provider" );

        p_register.setname(Name.getText().toString());
        p_register.setaddress(address.getText().toString() );
        p_register.setemail(email.getText().toString() );
        p_register.setphone( phone_no.getText().toString()  );
        p_register.setnic( nic.getText().toString()  );

        reference.child(user.getUid()).setValue(p_register);
        Intent intent = new Intent( provider_AccountCreation.this, NavigationActivity.class);
        startActivity(intent);

    }
}