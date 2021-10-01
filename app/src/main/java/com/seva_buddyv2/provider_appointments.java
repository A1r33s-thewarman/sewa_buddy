package com.seva_buddyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.ui.slideshow.Appointments_IAdapter;

import java.util.ArrayList;
import java.util.List;

public class provider_appointments extends AppCompatActivity {
    RecyclerView recy;

    private Appointments_IAdapter Iadapter;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    FirebaseUser user;
    private List<make_appointment_upload> appointments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_appointments);



        recy  = (RecyclerView)findViewById(R.id.recy);
        appointments = new ArrayList<>();


        Iadapter = new Appointments_IAdapter(provider_appointments.this,appointments);

        user = FirebaseAuth.getInstance().getCurrentUser();

        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(provider_appointments.this));
        recy.setAdapter(Iadapter);
        recy.setLayoutManager(new GridLayoutManager(provider_appointments.this, 1));
        DatabaseRef = FirebaseDatabase.getInstance().getReference("appointments");
        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointments.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String stake = postSnapshot.child("stake").getValue(String.class);

                        make_appointment_upload upload = postSnapshot.getValue(make_appointment_upload.class);
                        upload.setKey(postSnapshot.getKey());
                        //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();
                        //  Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                        appointments.add(upload);





                }
                Iadapter.notifyDataSetChanged();
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(provider_appointments.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //  ProgressCircle.setVisibility(View.INVISIBLE);
            }
        });



    }
}