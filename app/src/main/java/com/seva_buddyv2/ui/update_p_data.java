package com.seva_buddyv2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.seva_buddyv2.R;
import com.seva_buddyv2.p_Upload;
import com.seva_buddyv2.provider_addnew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class update_p_data extends AppCompatActivity {
    ImageButton imageButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageTask mUploadTask;
    private Uri mImageUri;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    Button upload;
    String l_name;
    Spinner location;
    EditText title,editTextTextMultiLine2,price;

    String  Key,H_rate,img_url,Location,Rate,Title,Desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_p_data);

        imageButton = (ImageButton)findViewById( R.id.imageButton ) ;
        title = (EditText)findViewById( R.id.title );
        location = (Spinner)findViewById( R.id.location );
        editTextTextMultiLine2 = (EditText)findViewById( R.id.editTextTextMultiLine2 );
        price= (EditText)findViewById( R.id.price );
        upload = (Button) findViewById( R.id.view ) ;


        List<String> list2 = new ArrayList<String>();

        list2.add("Ampara");
        list2.add("Anuradhapura");
        list2.add("Badulla");
        list2.add("Batticaloa");
        list2.add("Colombo");
        list2.add("Galle");
        list2.add("Gampaha");
        list2.add("Hambantota");
        list2.add("Jaffna");
        list2.add("Kalutara");
        list2.add("Kandy");
        list2.add("Kegalle");
        list2.add("Kilinochchi");
        list2.add("Kurunegala");
        list2.add("Mannar");
        list2.add("Matale");
        list2.add("Matara");
        list2.add("Monaragala");
        list2.add("Mullaitivu");
        list2.add("Nuwara Eliya");
        list2.add("Polonnaruwa");
        list2.add("Puttalam");
        list2.add("Ratnapura");
        list2.add("Trincomalee");
        list2.add("Vavuniya");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2){
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);


                return view;
            }};
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        location.setAdapter(adapter);







        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                l_name = parent.getItemAtPosition(position).toString();

                Toast.makeText(update_p_data.this,l_name,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            img_url = extras.getString("img_url");
            Key = extras.getString("key3");
            Location = extras.getString("location");
            H_rate = extras.getString("h_rate");
            Title = extras.getString("title");
            Desc = extras.getString("desc");

        }

        mStorageRef = FirebaseStorage.getInstance().getReference("ads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ads");

        title.setText(Title);
        //location.setText(Location);


        editTextTextMultiLine2.setText(Desc);
        price.setText(H_rate);

        Picasso.get().load(img_url).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageButton);

        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfile();
            }
        } );

        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_files();
            }
        } );




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(imageButton);
        }
    }

    private void open_files() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }
    private String getFileurl(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadfile() {

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileurl(mImageUri));
            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Toast.makeText(update_p_data.this, "Upload successful", Toast.LENGTH_LONG).show();


                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                            new OnCompleteListener<Uri>() {

                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String imglink = task.getResult().toString();



                                    p_Upload upload = new p_Upload(title.getText().toString(),imglink,l_name,editTextTextMultiLine2.getText().toString(),price.getText().toString(),"0");



                                    mDatabaseRef.child(Key).setValue(upload);




                                }
                            });

                }
            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(update_p_data.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }


        else {
            if(img_url !=null){

                String imglink = img_url;



                p_Upload upload = new p_Upload(title.getText().toString(),imglink,l_name,editTextTextMultiLine2.getText().toString(),price.getText().toString(),"0");



                mDatabaseRef.child(Key).setValue(upload);


            }
        }
    }

}