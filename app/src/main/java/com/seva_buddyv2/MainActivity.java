package com.seva_buddyv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seva_buddyv2.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton button;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    ImageView imageView,imageButton2;
    private int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;
    FirebaseUser user;
    String uid;
    int count = 0;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        checklogin();

        button = (ImageButton) findViewById( R.id.imageButton2 );
     //   imageButton2= (ImageView)findViewById( R.id.imageButton2 );
imageView = (ImageView)findViewById( R.id.imageView2 );
//firebase connection
        mAuth = FirebaseAuth.getInstance();

        //google account fetch
        GoogleSignInOptions googleso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestIdToken( getString( R.string.default_web_client_id ) )
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient( this, googleso );


        button.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                register_user();

            }
        } );
    }

    private void register_user() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult( signInIntent, RC_SIGN_IN );
    }

    public void openAccType() {
        Intent intent = new Intent( this, accType.class );
        startActivity( intent );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( data );
            handleSignInResult( task );
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount acc = completedTask.getResult( ApiException.class );
            Toast.makeText( MainActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT ).show();
          //  openAccType();
            FirebaseGoogleAuth( acc );
        } catch (ApiException e) {
            Toast.makeText( MainActivity.this, "Error : " + e, Toast.LENGTH_SHORT ).show();
            FirebaseGoogleAuth( null );
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null

        if (acct != null) {

            AuthCredential authCredential = GoogleAuthProvider.getCredential( acct.getIdToken(), null );
            mAuth.signInWithCredential( authCredential ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
<<<<<<< Updated upstream
                        if (user.getEmail().equals("dinithkaushalya55@gmail.com")) {
=======
                        if (user.getEmail().equals("harshaerandikaofficials@gmail.com")) {
>>>>>>> Stashed changes

                            Intent intent = new Intent(getApplication(), admin.class);
                            startActivity(intent);
                            finish();


                        }else {
                            openAccType();
                        }

                    }

                }
            } );
        }else{
<<<<<<< Updated upstream
         //   Toast.makeText( MainActivity.this, "wada nathuththo ", Toast.LENGTH_SHORT ).show();
=======
         //   Toast.makeText( MainActivity.this, "not working ", Toast.LENGTH_SHORT ).show();
>>>>>>> Stashed changes
        }
    }

    private void checklogin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();




        if (user != null) {

<<<<<<< Updated upstream
            if (user.getEmail().equals("dinithkaushalya55@gmail.com")) {
=======
            if (user.getEmail().equals("harshaerandikaofficials@gmail.com")) {
>>>>>>> Stashed changes

                Intent intent = new Intent(getApplication(), admin.class);
                startActivity(intent);
                finish();


            }else {


                DatabaseRef = FirebaseDatabase.getInstance().getReference("users").child("provider");
                DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            uid = postSnapshot.getKey().toString();


                            if (uid.equals(user.getUid().toString())) {
                                count = 1;

                            } else {

                            }


                        }
                        if (count == 0) {
                            Intent intent = new Intent(getApplication(), NavigationActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(getApplication(), provide_home.class);
                            startActivity(intent);
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplication(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        //  ProgressCircle.setVisibility(View.INVISIBLE);
                    }
                });


            }

        }

        else {


        }



    }

}