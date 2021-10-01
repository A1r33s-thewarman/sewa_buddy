package com.seva_buddyv2.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.AccountCreation;
import com.seva_buddyv2.NavigationActivity;
import com.seva_buddyv2.R;
import com.seva_buddyv2.ui.register;


public class GalleryFragment extends Fragment {
    EditText Name1,Address,Phone_no,Email;
    com.seva_buddyv2.ui.register update;
    private FirebaseAuth mAuth;
    DatabaseReference reference ;
    FirebaseUser user;
    Button view;
    private ValueEventListener DBListener;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.customer_update, container, false);

        Name1 = (EditText)v.findViewById( R.id.cusname);
        Address = (EditText)v.findViewById( R.id. cusaddress);
        Phone_no = (EditText)v.findViewById( R.id.phoneno );
        Email = (EditText)v.findViewById( R.id.cusemail );
        view= (Button)v.findViewById(R.id.view);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("users").child( "normal_user" ).child(user.getUid().toString());
        DBListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {





                String address = (dataSnapshot.child("address").getValue().toString());
                String email = (dataSnapshot.child("email").getValue().toString());
                String name = (dataSnapshot.child("name").getValue().toString());
                String phone = (dataSnapshot.child("phone").getValue().toString());
               // String type = (dataSnapshot.child("type").getValue().toString());

                Name1.setText(name);
                Address.setText(address);
                Phone_no.setText(phone);
                Email.setText(email);

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_data();
            }
        });



        return v;
    }

    private void insert_data() {

        update = new register();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("users").child( "normal_user" );

        update.setname(Name1.getText().toString());
        update.setaddress(Address.getText().toString() );
        update.setemail(Email.getText().toString() );
        update.setphone( Phone_no.getText().toString()  );
        update.settype( "usr");

        reference.child(user.getUid()).setValue(update);


    }
}