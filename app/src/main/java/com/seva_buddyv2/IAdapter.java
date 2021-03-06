package com.seva_buddyv2;

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
import com.seva_buddyv2.ui.update_p_data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IAdapter extends RecyclerView.Adapter<IAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Uploads> mUploads;
    private IAdapter.OnItemClickListener mListener;

    public IAdapter(Context context, List<Uploads> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public IAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
holder.positon2.setText(String.valueOf(position) );
holder.location.setText( location );
holder.rate.setRating( Float.parseFloat( rate ) );
        holder.s_key.setText( uploadCurrent.getKey() );
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
        public TextView title,location,h_rate,s_key,positon2;
        public RatingBar rate;
        public Button delete,update;
        public ImageView imageView;
        DatabaseReference nm;

        public ImageViewHolder(View itemView) {
            super(itemView);
            positon2 = itemView.findViewById( R.id.positon2 );
            s_key= itemView.findViewById( R.id.s_key );
            rate = itemView.findViewById( R.id.rate );
            location = itemView.findViewById( R.id.location );
            h_rate = itemView.findViewById( R.id.h_rate );
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imgview99);
            delete = itemView.findViewById( R.id.delete69 );
            update = itemView.findViewById( R.id.update );
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
           // nm = FirebaseDatabase.getInstance("https://seva-buddyv2-default-rtdb.firebaseio.com").getReference("uploads");

            delete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"deleting key :  "+s_key.getText().toString(),Toast.LENGTH_SHORT).show();
                    nm = FirebaseDatabase.getInstance("https://seva-buddyv2-default-rtdb.firebaseio.com")
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                            .getReference("uploads").child( s_key.getText().toString() );
=======
                            .getReference("ads").child( s_key.getText().toString() );
>>>>>>> Stashed changes
=======
                            .getReference("ads").child( s_key.getText().toString() );
>>>>>>> Stashed changes
                    nm.getRef().removeValue();
                }
            } );

            update.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                    Toast.makeText(mContext,"deleting ",Toast.LENGTH_SHORT).show();
=======
=======
>>>>>>> Stashed changes

                    Uploads uploads = mUploads.get(Integer.parseInt(positon2.getText().toString()));


                    Intent i = new Intent(mContext, update_p_data.class);
                    i.putExtra("key3",s_key.getText().toString());
                    i.putExtra("h_rate",uploads.geth_rate());
                    i.putExtra("img_url",uploads.getimage_url());
                    i.putExtra("location",uploads.getlocation());
                    i.putExtra("rate",uploads.getrate());
                    i.putExtra("title",uploads.gettitle());
                    i.putExtra("desc",uploads.getdesc());
                    mContext.startActivity(i);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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

    public void setOnItemClickListener(IAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}