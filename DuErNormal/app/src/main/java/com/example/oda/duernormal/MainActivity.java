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

import java.util.HashMap;
import java.util.Map;


import java.util.EventListener;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    TextView signedInText;
    Button mLoginButton;
    Button mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        //Login private:


        mAuth = FirebaseAuth.getInstance();


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

        FirebaseUser currentUser = mAuth.getCurrentUser();

        }

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

    @Override
        public void onClick(View v) {
        String cat;
        switch (v.getId()) {
            case R.id.mBkropp:
                cat = "Kropp";
                Intent i = new Intent(MainActivity.this, ViewDatabase.class);
                i.putExtra("cat", cat);
                startActivity(i);
                break;

            case R.id.mButseendet:
                cat = "Utseendet";
                Intent j = new Intent(MainActivity.this, ViewDatabase.class);
                j.putExtra("cat", cat);
                startActivity(j);
                break;

            case R.id.mBpersonlighet:
                cat = "Personlighet";
                Intent n = new Intent(MainActivity.this, ViewDatabase.class);
                n.putExtra("cat", cat);
                startActivity(n);
                break;

            case R.id.mBforeldre:
                cat = "Familieliv";
                Intent l = new Intent(MainActivity.this, ViewDatabase.class);
                l.putExtra("cat", cat);
                startActivity(l);
                break;
            case R.id.mBsosialt:
                cat = "Sosialt";
                Intent m = new Intent(MainActivity.this, ViewDatabase.class);
                m.putExtra("cat", cat);
                startActivity(m);
                break;
            /*
            case R.id.mBgenerelt:
                cat = "generelt";
                //TODO her må vi telle hele databasen. PLuss, kan vi få de random?
                Intent k = new Intent(MainActivity.this, DisplayKropp.class);
                k.putExtra("cat", cat);
                startActivity(k);
                break;
                */
            case R.id.mBgenerelt:
                cat = "generelt";
                //TODO her må vi telle hele databasen. PLuss, kan vi få de random?

                break;
            case R.id.mLoginButton:
                SignInIn();
                break;

        }

    }};


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
