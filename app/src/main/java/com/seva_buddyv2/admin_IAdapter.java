package com.seva_buddyv2;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class admin_IAdapter extends RecyclerView.Adapter<admin_IAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Uploads> mUploads;
    private admin_IAdapter.OnItemClickListener mListener;
    Context context;
    public admin_IAdapter(Context context, List<Uploads> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public admin_IAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.admin_item, parent, false);
        return new admin_IAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(admin_IAdapter.ImageViewHolder holder, int position) {


        Uploads uploadCurrent = mUploads.get(position);

        String h_rate = uploadCurrent.geth_rate();
        String location = uploadCurrent.getlocation();
        String rate = uploadCurrent.getrate();

        String title = uploadCurrent.gettitle();
        holder.location.setText( location );
        holder.rate.setRating( Float.parseFloat( rate ) );
        holder.s_key.setText( uploadCurrent.getKey() );
        Picasso.get()
                .load(uploadCurrent.getimage_url())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView name,location,NIC,s_key;
        public RatingBar rate;
        public Button delete;
        public ImageView imageView;
        DatabaseReference nm;

        public ImageViewHolder(View itemView) {
            super(itemView);
            s_key= itemView.findViewById( R.id.s_key );
            location = itemView.findViewById( R.id.location );
            NIC = itemView.findViewById( R.id.h_rate );
            imageView = itemView.findViewById(R.id.imgview99);
            delete = itemView.findViewById( R.id.delete69 );

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            // nm = FirebaseDatabase.getInstance("https://seva-buddyv2-default-rtdb.firebaseio.com").getReference("users");

            delete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nm = FirebaseDatabase.getInstance("https://seva-buddyv2-default-rtdb.firebaseio.com")
                            .getReference("users").child( s_key.getText().toString() );
                    nm.getRef().removeValue();
                }
            } );
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

    public void setOnItemClickListener(admin_IAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}