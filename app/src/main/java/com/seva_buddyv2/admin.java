package com.seva_buddyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.ui.gallery.admin_adapter;
import com.seva_buddyv2.ui.gallery.admin_upload;
import com.seva_buddyv2.ui.slideshow.Appointments_IAdapter;

import java.util.ArrayList;
import java.util.List;

public class admin extends AppCompatActivity {
    RecyclerView recy;
    private admin_adapter Iadapter;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    FirebaseUser user;
    ImageButton anali;
    private List<admin_upload> appointments;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        anali = (ImageButton)findViewById(R.id.anali);

        anali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin.this, admin_anali.class);
                startActivity(intent);

            }
        });

        recy  = (RecyclerView)findViewById(R.id.recy);
        appointments = new ArrayList<>();


        Iadapter = new admin_adapter(admin.this,appointments);

        user = FirebaseAuth.getInstance().getCurrentUser();

        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(admin.this));
        recy.setAdapter(Iadapter);
        recy.setLayoutManager(new GridLayoutManager(admin.this, 1));
        DatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointments.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    for (DataSnapshot postSnapshot2  : postSnapshot.getChildren()) {
                        String stake = postSnapshot2.child("address").getValue(String.class);
                        Toast.makeText(admin.this, stake, Toast.LENGTH_SHORT).show();

                        admin_upload upload = postSnapshot2.getValue(admin_upload.class);
                        appointments.add(upload);
                    }

                  // String stake = postSnapshot.child("normal_user").getValue(String.class);

                   // make_appointment_upload upload = postSnapshot.getValue(make_appointment_upload.class);
                  //  upload.setKey(postSnapshot.getKey());
                    //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();


                  //  appointments.add(upload);





                }
               Iadapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(admin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });









    }
}