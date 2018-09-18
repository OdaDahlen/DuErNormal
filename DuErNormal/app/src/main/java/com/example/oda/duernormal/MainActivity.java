package com.example.oda.duernormal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Login private:


        mAuth = FirebaseAuth.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The buttons
        Button mBkropp = findViewById(R.id.mBkropp);
        Button mButseendet = findViewById(R.id.mButseendet);
        Button mBsosialt = findViewById(R.id.mBsosialt);
        Button mBforeldre = findViewById(R.id.mBforeldre);
        Button mBpersonlighet = findViewById(R.id.mBpersonlighet);
        Button mBgenerelt = findViewById(R.id.mBgenerelt);
        Button mBtilfeldige = findViewById(R.id.mBtilfeldige);

        mBkropp.setOnClickListener(this);
        mButseendet.setOnClickListener(this);
        mBsosialt.setOnClickListener(this);
        mBforeldre.setOnClickListener(this);
        mBpersonlighet.setOnClickListener(this);
        mBgenerelt.setOnClickListener(this);
        mBtilfeldige.setOnClickListener(this);


        //mBkropp.setOnClickListener(new View.OnClickListener() {
        // @Override
        //public void onClick(View view) {
        //    startActivity(new Intent(MainActivity.this, DisplayKropp.class));
        //}
        //});

    }

    @Override
    public void onClick(View v) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        //private static final String TAG = "AnonymousAuth";

        String TAG = "AnonymousAuth";
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.d(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                        }

                        // ...
                    }
                });
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