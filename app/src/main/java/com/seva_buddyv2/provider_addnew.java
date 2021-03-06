package com.seva_buddyv2;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import java.util.ArrayList;
import java.util.List;

public class provider_addnew extends AppCompatActivity {
    ImageButton imageButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageTask mUploadTask;
    private Uri mImageUri;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    Button upload;
    String l_name;
    EditText title,editTextTextMultiLine2,price;
    Spinner location;
    private FirebaseAuth mAuth;

    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_addnew);

        imageButton = (ImageButton)findViewById( R.id.imageButton ) ;
        title = (EditText)findViewById( R.id.title );
        location = (Spinner)findViewById( R.id.location );
        editTextTextMultiLine2 = (EditText)findViewById( R.id.editTextTextMultiLine2 );
        price= (EditText)findViewById( R.id.price );
        upload = (Button) findViewById( R.id.view ) ;
        mStorageRef = FirebaseStorage.getInstance().getReference("ads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ads");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


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

                Toast.makeText(provider_addnew.this,l_name,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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



                                        p_Upload upload = new p_Upload(title.getText().toString(),imglink,l_name,editTextTextMultiLine2.getText().toString(),price.getText().toString(),"0");

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
