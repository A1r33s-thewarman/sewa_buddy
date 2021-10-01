package com.seva_buddyv2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.Customer_IAdapter;
import com.seva_buddyv2.IAdapter;
import com.seva_buddyv2.R;
import com.seva_buddyv2.Uploads;
import com.seva_buddyv2.provide_home;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView cus_re;

    private Customer_IAdapter Iadapter;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    private List<Uploads> uploads;
EditText searchtext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.customer_home, container, false);

        searchtext = (EditText)v.findViewById(R.id.searchtext);




        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input

                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });


        uploads = new ArrayList<>();
        Iadapter = new Customer_IAdapter(getContext(),uploads);


        cus_re = v.findViewById(R.id.cus_re);
        cus_re.setHasFixedSize(true);
        cus_re.setLayoutManager(new LinearLayoutManager(getContext()));
        cus_re.setAdapter(Iadapter);
        cus_re.setLayoutManager(new GridLayoutManager(getContext(), 1));
        DatabaseRef = FirebaseDatabase.getInstance().getReference("ads");
        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploads.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Uploads upload = postSnapshot.getValue(Uploads.class);
                    upload.setKey(postSnapshot.getKey());
                    //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();
                    //  Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                    uploads.add(upload);



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

    public void search(String s){

        uploads.clear();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference dateRef = rootRef.child("ads");
        Query query = dateRef.orderByChild("title").startAt(s).endAt(s + "\uf8ff");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Uploads upload = postSnapshot.getValue(Uploads.class);
                    upload.setKey(postSnapshot.getKey());
                    //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();
                    //  Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                    uploads.add(upload);
                }
                Iadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}