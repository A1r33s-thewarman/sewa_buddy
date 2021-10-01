package com.seva_buddyv2;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.seva_buddyv2.ui.home.view_ad;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Customer_IAdapter extends RecyclerView.Adapter<Customer_IAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Uploads> mUploads;
    private Customer_IAdapter.OnItemClickListener mListener;

    public Customer_IAdapter(Context context, List<Uploads> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public Customer_IAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.customer_item, parent, false);
        return new Customer_IAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Customer_IAdapter.ImageViewHolder holder, int position) {


        Uploads uploadCurrent = mUploads.get(position);

        String h_rate = uploadCurrent.geth_rate();
        String location = uploadCurrent.getlocation();
        String rate = uploadCurrent.getrate();
holder.key.setText(String.valueOf(position));
        String title = uploadCurrent.gettitle();
        holder.h_rate.setText(h_rate );
        holder.location.setText( location );
        holder.rate.setRating( 2 );

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
        public TextView title,location,h_rate,key;
        public RatingBar rate;
        LinearLayout viewo;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            key= itemView.findViewById( R.id.key );
            viewo = itemView.findViewById( R.id.viewo );
            rate = itemView.findViewById( R.id.crate );
            location = itemView.findViewById( R.id.clocation );
            h_rate = itemView.findViewById( R.id.ch_rate );
            title = itemView.findViewById(R.id.ctitle);
            imageView = itemView.findViewById(R.id.imgviewcus);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

            viewo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uploads uploadCurrent = mUploads.get(Integer.parseInt(key.getText().toString()));

                    Intent i = new Intent(mContext, view_ad.class);
                    i.putExtra("key",uploadCurrent.getKey());
                    i.putExtra("img",uploadCurrent.getimage_url());
                    i.putExtra("desc",uploadCurrent.getdesc());
                    i.putExtra("h_rate",uploadCurrent.geth_rate());
                    i.putExtra("location",uploadCurrent.getlocation());
                    i.putExtra("rate",uploadCurrent.getrate().toString());
                    i.putExtra("title",uploadCurrent.gettitle());
                    mContext.startActivity(i);
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

    public void setOnItemClickListener(Customer_IAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
