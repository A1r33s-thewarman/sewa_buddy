package com.seva_buddyv2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seva_buddyv2.ui.register;

public class AccountCreation extends AppCompatActivity {
EditText Name,address,phone_no,email;
    private FirebaseAuth mAuth;

    FirebaseUser user;
    com.seva_buddyv2.ui.register register;
    DatabaseReference reference ;
    Button buttonl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        Name = (EditText)findViewById( R.id.editTextTextPersonName2);
        address = (EditText)findViewById( R.id. editTextTextMultiLine);
        phone_no = (EditText)findViewById( R.id.editTextPhone );
        email = (EditText)findViewById( R.id.editTextTextEmailAddress );

        buttonl = (Button)findViewById( R.id.button );
        buttonl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert_data();
            }
        } );

    }
    private void insert_data() {

        register = new register();

        if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Name.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter address!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(phone_no.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter phone no!", Toast.LENGTH_LONG).show();
            return;
        }



        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("users").child( "normal_user" );

        register.setname(Name.getText().toString());
        register.setaddress(address.getText().toString() );
        register.setemail(email.getText().toString() );
        register.setphone( phone_no.getText().toString()  );
        register.settype( "usr");

        reference.child(user.getUid()).setValue(register);
        Intent intent = new Intent(AccountCreation.this, NavigationActivity.class);
        startActivity(intent);

    }
}