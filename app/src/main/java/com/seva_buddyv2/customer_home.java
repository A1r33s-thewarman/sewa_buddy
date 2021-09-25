package com.seva_buddyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customer_home extends AppCompatActivity implements  IAdapter.OnItemClickListener {
    RecyclerView re;
    private IAdapter Iadapter;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    private List<Uploads> uploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.provider_home);
        uploads = new ArrayList<>();
        Iadapter = new IAdapter(provide_home.this, uploads);

        // List<Upload> mAdapter = new ArrayList<>();
        re = findViewById(R.id.re);
        re.setHasFixedSize(true);
        re.setLayoutManager(new LinearLayoutManager(provide_home.this));
        re.setAdapter(Iadapter);
        re.setLayoutManager(new GridLayoutManager(provide_home.this, 1));
        // mUploads = new ArrayList<>();

        DatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploads.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Uploads upload = postSnapshot.getValue(Uploads.class);
                    //  upload.setKey(postSnapshot.getKey());
                    //  String name = dataSnapshot.child(postSnapshot.getKey()).child("name").getValue().toString();
                    //  Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                    uploads.add(upload);



                }
                Iadapter.notifyDataSetChanged();
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(provide_home.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //  ProgressCircle.setVisibility(View.INVISIBLE);
            }
        });




    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText( provide_home.this,"Clicked"+position,Toast.LENGTH_SHORT ).show();
    }
}
