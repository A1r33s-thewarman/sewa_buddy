package com.seva_buddyv2.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.Customer_IAdapter;
import com.seva_buddyv2.R;
import com.seva_buddyv2.Uploads;
import com.seva_buddyv2.make_appointment_upload;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

RecyclerView recy;

    private Appointments_IAdapter Iadapter;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    FirebaseUser user;
    private List<make_appointment_upload> appointments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.customer_appointment, container, false);

        // to do code

        recy  = (RecyclerView)v.findViewById(R.id.recy);
        appointments = new ArrayList<>();


        Iadapter = new Appointments_IAdapter(getContext(),appointments);

         user = FirebaseAuth.getInstance().getCurrentUser();

        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setAdapter(Iadapter);
        recy.setLayoutManager(new GridLayoutManager(getContext(), 1));
        DatabaseRef = FirebaseDatabase.getInstance().getReference("appointments");
        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointments.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(user.getUid().equals( postSnapshot.getKey().toString())){
                        make_appointment_upload upload = postSnapshot.getValue(make_appointment_upload.class);
                        upload.setKey(postSnapshot.getKey());
                        //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();
                        //  Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                        appointments.add(upload);
                    }




                }
                Iadapter.notifyDataSetChanged();
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //  ProgressCircle.setVisibility(View.INVISIBLE);
            }
        });




        return v;


    }




}