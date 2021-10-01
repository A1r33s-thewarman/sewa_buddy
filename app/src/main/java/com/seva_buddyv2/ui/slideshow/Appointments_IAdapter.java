package com.seva_buddyv2.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

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
import com.seva_buddyv2.rate_the_service;
import com.seva_buddyv2.ui.home.view_ad;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Appointments_IAdapter  extends RecyclerView.Adapter<Appointments_IAdapter.ImageViewHolder> {
    private Context mContext;
    private List<make_appointment_upload> mUploads;
    private Appointments_IAdapter.OnItemClickListener mListener;

    public Appointments_IAdapter(Context context, List<make_appointment_upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public Appointments_IAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.appointment_item, parent, false);
        return new Appointments_IAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Appointments_IAdapter.ImageViewHolder holder, int position) {


        make_appointment_upload uploadCurrent = mUploads.get(position);


        holder.key2.setText(uploadCurrent.getKey());

        holder.date.setText(uploadCurrent.getdate() );
        holder.desc.setText(uploadCurrent.getmessage() );





    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView title,date,desc,key,key2;
        private DatabaseReference DatabaseRef;
        private ValueEventListener DBListener;
        Button button4,button3;
        public ImageView imageView;
        FirebaseUser user;
        DatabaseReference nm;
        public ImageViewHolder(View itemView) {
            super(itemView);
            key2 = itemView.findViewById( R.id.key2 );
            button4= itemView.findViewById( R.id.button4 );
            button3= itemView.findViewById( R.id.button3 );
            key= itemView.findViewById( R.id.key );
            date = itemView.findViewById( R.id.textView11 );
            desc = itemView.findViewById( R.id.textView12 );
            title = itemView.findViewById(R.id.ctitle);
            imageView = itemView.findViewById(R.id.imgviewcus);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);



            user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseRef = FirebaseDatabase.getInstance().getReference("ads");
            DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        String img_url = postSnapshot.child("image_url").getValue(String.class);
                        String title2 = postSnapshot.child("title").getValue(String.class);

                        title.setText(title2);
                        Picasso.get()
                                .load(img_url)
                                .placeholder(R.mipmap.ic_launcher)
                                .fit()
                                .centerCrop()
                                .into(imageView);


                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(mContext, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });


            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, rate_the_service.class);
                    intent.putExtra("id",key2.getText().toString());
                    mContext.startActivity(intent);

                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nm = FirebaseDatabase.getInstance().getReference("appointments").child(key2.getText().toString());
                    nm.getRef().removeValue();
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);


    }

    public void setOnItemClickListener(Appointments_IAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
