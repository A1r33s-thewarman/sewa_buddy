package com.seva_buddyv2;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Customer_IAdapter extends RecyclerView.Adapter<IAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Uploads> mUploads;
    private IAdapter.OnItemClickListener mListener;

    public Customer_IAdapter(Context context, List<Uploads> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public Customer_IAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.provider_item, parent, false);
        return new IAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IAdapter.ImageViewHolder holder, int position) {


        Uploads uploadCurrent = mUploads.get(position);

        String h_rate = uploadCurrent.geth_rate();
        String location = uploadCurrent.getlocation();
        String rate = uploadCurrent.getrate();

        String title = uploadCurrent.gettitle();
        holder.h_rate.setText(h_rate );
        holder.location.setText( location );
        holder.rate.setRating( Float.parseFloat( rate ) );

        holder.title.setText(title);
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
        public TextView title,location,h_rate;
        public RatingBar rate;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            rate = itemView.findViewById( R.id.rate );
            location = itemView.findViewById( R.id.location );
            h_rate = itemView.findViewById( R.id.h_rate );
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imgview99);
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

    public void setOnItemClickListener(IAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
