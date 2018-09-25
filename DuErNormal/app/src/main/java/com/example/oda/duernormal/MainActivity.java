package com.example.oda.duernormal;
import com.example.oda.duernormal.Thoughts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;


import java.util.EventListener;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    DatabaseReference mFiredatabase;
    TextView signedInText;
    Button mLoginButton;
    Button mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        //Login private:


        mAuth = FirebaseAuth.getInstance();
        //mFiredatabaseHandle = new FiredatabaseHandler(mFiredatabaseHandler);
        // Not sure why I can not use mFiredatabaseHandler as dbref is used below

        mFiredatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebasedatabase", mFiredatabase.toString());
        //mFiredatabase = FirebaseDatabase.getInstance().getReference("https://duernormal-1522016527947,firebaseio,com/").child("Thoughts");
        //FirebaseDatabase fb = FirebaseDatabase.getInstance();
        //DatabaseReference dbref = fb.getReference();
        //mFiredatabase.child("Thoughts").child("Category").addValueEventListener(new ValueEventListener() {

        //);


        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The buttons
        Button mBkropp = findViewById(R.id.mBkropp);
        Button mButseendet = findViewById(R.id.mButseendet);
        Button mBsosialt = findViewById(R.id.mBsosialt);
        Button mBforeldre = findViewById(R.id.mBforeldre);
        Button mBpersonlighet = findViewById(R.id.mBpersonlighet);
        Button mBgenerelt = findViewById(R.id.mBgenerelt);
        Button mBtilfeldige = findViewById(R.id.mBtilfeldige);

        mLoginButton = findViewById(R.id.mLoginButton);
        mLogoutButton = findViewById(R.id.mLogoutButton);
        signedInText = findViewById(R.id.signintext);

        mBkropp.setOnClickListener(this);
        mButseendet.setOnClickListener(this);
        mBsosialt.setOnClickListener(this);
        mBforeldre.setOnClickListener(this);
        mBpersonlighet.setOnClickListener(this);
        mBgenerelt.setOnClickListener(this);
        mBtilfeldige.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
        mLogoutButton.setVisibility(View.INVISIBLE);

        //case R.id.mLoginButton:
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //MyFireDBHandler

        }
/*        @Override
        protected void onStart () {
            super.onStart();
            String txt = "hey";
            Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
            Log.d("Printing Thoughts.class", Thoughts.class.toString()); //Prints the file/class-name (com.example.oda.duernormal.Thoughts.class)
            //Log.d("Try", mFiredatabase.getKey());


            ValueEventListener listen = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Thoughts thoughts = dataSnapshot.getValue(Thoughts.class);
                    Log.d("HMMMMMM", "Booo");
                    Log.d("FROM DATA", thoughts.mCategory);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Errormsg", "Error!!");
                }

            };
            mFiredatabase.addValueEventListener(listen);

    };
    */

    @Override
    protected void onStart () {
        super.onStart();
        String txt = "hey";
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
        Log.d("Printing Thoughts.class", Thoughts.class.toString()); //Prints the file/class-name (com.example.oda.duernormal.Thoughts.class)
        //Log.d("Try", mFiredatabase);
        //mFiredatabase.getDatabase().getReference().
        if (mFiredatabase == null){
            Log.d("isnull", "Noooo");
        }


        mFiredatabase.child("Thoughts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("E", "ER!");
                } else {
                    Log.d("IE", "ER_IKKE!");
                }
                Thoughts thoughts = dataSnapshot.getValue(Thoughts.class);
                //thought_list.add(thoughts);
                Log.d("HMMMMMM", "Booo");
                Log.d("FROM DATA", thoughts.mCategory);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Errormsg", "Error!!");
            }
        });
    };

   /*
    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Post post = dataSnapshot.getValue(Post.class);

                mAuthorView.setText(post.author);
                mTitleView.setText(post.title);
                mBodyView.setText(post.body);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(PostDetailActivity.this, "Failed to load post.", Toast.LENGTH_SHORT).show();
            }
        };
        mPostReference.addValueEventListener(postListener);

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

        // Listen for comments
        mAdapter = new CommentAdapter(this, mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter);
    }
*/

    public void SignInIn(){

        //Log.d("SignInCall", "Sign in is called");
        Toast.makeText(this, "SignIn is called", Toast.LENGTH_LONG);
        final String TAG = "AnonymousAuth";
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
     @Override
     public void onComplete(@NonNull Task<AuthResult> task){
        if(task.isSuccessful()){
        // Sign in success, update UI with the signed-in user's information
        Log.d(TAG, "signInAnonymously:success");
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("Name of user", user.toString());
        Log.d("IDuser", user.getUid());
        updateUI(user);
        }else{
        // If sign in fails, display a message to the user.
        Log.d(TAG, "signInAnonymously:failure", task.getException());
        Toast.makeText(MainActivity.this,"Authentication failed.", Toast.LENGTH_SHORT).show();
        updateUI(null);
        }
        }
        });
    };

    private void updateUI(FirebaseUser user) {
        if (user == null) {

            signedInText.setText("not signed in");


        } else {
            signedInText.setText("You are logged in");
            mLoginButton.setVisibility(View.INVISIBLE);
            mLogoutButton.setVisibility(View.VISIBLE);
        }
    };

   /* private void updateUI(FirebaseUser user) {
        hideProgressDialog();

        TextView idView = findViewById(R.id.anonymous_status_id);
        TextView emailView = findViewById(R.id.anonymous_status_email);
        boolean isSignedIn = (user != null);

        // Status text
        if (isSignedIn) {
            idView.setText(getString(R.string.id_fmt, user.getUid()));
            emailView.setText(getString(R.string.email_fmt, user.getEmail()));
        } else {
            idView.setText(R.string.signed_out);
            emailView.setText(null);
        }

        // Button visibility
        findViewById(R.id.button_anonymous_sign_in).setEnabled(!isSignedIn);
        findViewById(R.id.button_anonymous_sign_out).setEnabled(isSignedIn);
        findViewById(R.id.button_link_account).setEnabled(isSignedIn);
    }
*/

    @Override
        public void onClick(View v) {
        String cat;
        switch (v.getId()) {
            case R.id.mBkropp:
                cat = "kropp";
                Intent i = new Intent(MainActivity.this, DisplayKropp.class);
                i.putExtra("cat", cat);
                startActivity(i);
                break;

            case R.id.mButseendet:
                cat = "utseendet";
                Intent j = new Intent(MainActivity.this, DisplayKropp.class);
                j.putExtra("cat", cat);
                startActivity(j);
                break;

            case R.id.mBpersonlighet:
                cat = "personlighet";
                Intent n = new Intent(MainActivity.this, DisplayKropp.class);
                n.putExtra("cat", cat);
                startActivity(n);
                break;

            case R.id.mBforeldre:
                cat = "foreldre";
                Intent l = new Intent(MainActivity.this, DisplayKropp.class);
                l.putExtra("cat", cat);
                startActivity(l);
                break;
            case R.id.mBsosialt:
                cat = "sosialt";
                Intent m = new Intent(MainActivity.this, DisplayKropp.class);
                m.putExtra("cat", cat);
                startActivity(m);
                break;
            case R.id.mBgenerelt:
                cat = "generelt";
                //TODO her må vi telle hele databasen. PLuss, kan vi få de random?
                Intent k = new Intent(MainActivity.this, DisplayKropp.class);
                k.putExtra("cat", cat);
                startActivity(k);
                break;
            case R.id.mLoginButton:
                SignInIn();
                break;

        }

    }};

//updateUI(currentUser);
//private static final String TAG = "AnonymousAuth";

/*public String SignIn(){
        String TAG = "AnonymousAuth";
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
@Override
public void onComplete(@NonNull Task<AuthResult> task){
        if(task.isSuccessful()){
        // Sign in success, update UI with the signed-in user's information
        //Log.d(TAG, "signInAnonymously:success");
        FirebaseUser user=mAuth.getCurrentUser();
        //updateUI(user);
        }else{
        // If sign in fails, display a message to the user.
        //Log.d(TAG, "signInAnonymously:failure", task.getException());
        Toast.makeText(MainActivity.this,"Authentication failed.",
        Toast.LENGTH_SHORT).show();
        //updateUI(null);
        }

        // ...
        }
        }
        return user;
        };
*/


// TODO: legge inn flere i flere kategorier
//aktivere stemmeknappene
// TODO: Hvordan ta inn statistikken og hvordan få oppdatert databasen?
// Genere brukeranonymitet vha QR-kode
// Legge til en kolonne i databasen som identifiserer om det er mann eller dame  så de senere kan sorteres på det
// (Vil du se spesifikke for menn damer eller mer generelle?) Må også kunne sammenslå kjønn.
// I tillegg kolonner på statdamer 12-15 år, etc etc.
//Forenkle stemmingen?
//Grafer. Se graf over hvordan den aldersgruppen har utviklet seg
// Personlig utvikling.