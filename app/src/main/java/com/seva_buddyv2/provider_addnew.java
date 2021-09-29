package com.seva_buddyv2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class provider_addnew extends AppCompatActivity {
    ImageButton imageButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageTask mUploadTask;
    private Uri mImageUri;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    Button upload;
    EditText title,location,editTextTextMultiLine2,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_addnew);

        imageButton = (ImageButton)findViewById( R.id.imageButton ) ;
        title = (EditText)findViewById( R.id.title );
        location = (EditText)findViewById( R.id.location );
        editTextTextMultiLine2 = (EditText)findViewById( R.id.editTextTextMultiLine2 );
        price= (EditText)findViewById( R.id.price );
        upload = (Button) findViewById( R.id.view ) ;
        mStorageRef = FirebaseStorage.getInstance().getReference("ads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ads");


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


                    Toast.makeText(provider_addnew.this, "Upload successful", Toast.LENGTH_LONG).show();


                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                            new OnCompleteListener<Uri>() {

                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String imglink = task.getResult().toString();



                                        p_Upload upload = new p_Upload(title.getText().toString(),imglink,location.getText().toString(),editTextTextMultiLine2.getText().toString(),price.getText().toString());

                                        String uploadId = mDatabaseRef.push().getKey();

                                        mDatabaseRef.child(uploadId).setValue(upload);




                                }
                            });

                }
            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(provider_addnew.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }


        else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

}
