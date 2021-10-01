package com.seva_buddyv2.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.seva_buddyv2.R;
import com.seva_buddyv2.Uploads;
import com.seva_buddyv2.ui.update_p_data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class admin_adapter  extends RecyclerView.Adapter<admin_adapter.ImageViewHolder> {
    private Context mContext;
    private List<admin_upload> mUploads;
    private admin_adapter.OnItemClickListener mListener;

    public admin_adapter(Context context, List<admin_upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public admin_adapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_view, parent, false);
        return new admin_adapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(admin_adapter.ImageViewHolder holder, int position) {


        admin_upload uploadCurrent = mUploads.get(position);


        holder.name.setText(uploadCurrent.getname() );
        holder.address.setText(uploadCurrent.getaddress()  );
        holder.email.setText(uploadCurrent.getemail()  );
        holder.phone.setText(uploadCurrent.getphone()  );


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView name,address,email,phone,type;


        public ImageViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById( R.id.name );
            address= itemView.findViewById( R.id.address );
            email = itemView.findViewById( R.id.email );
            phone = itemView.findViewById( R.id.phone );
            type = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);



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

    public void setOnItemClickListener(admin_adapter.OnItemClickListener listener) {
        mListener = listener;
    }
}