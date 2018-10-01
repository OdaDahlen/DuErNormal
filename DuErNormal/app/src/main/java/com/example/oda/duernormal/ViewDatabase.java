package com.example.oda.duernormal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ViewDatabase extends AppCompatActivity {
    DatabaseReference mFiredatabase;
    ValueEventListener vel;
    Button mBnext;
    TextView sitat;
    TextView sitatTest;
    TextView statDamNr;
    TextView statMennNr;
    TextView statDamset;
    TextView statMennset;
    int mSitat;
    int mIndex = 1;
    int rowNr = 0;
    double mStatDam;
    double mStatMenn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kropp);
        initialize_views();
        mBnext.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        final String category = intent.getExtras().getString("cat");
        Log.d("Cat imported is", category);
       // mAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(this);

        mFiredatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebasedatabase", mFiredatabase.toString());

        String txt = "hey";
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

        if (mFiredatabase == null){
            Log.d("isnull", "Noooo");
        }
        vel = mFiredatabase.child("Thoughts").child("Category").child("Kropp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("InOnDataChange", "on data change");

                Map<String, Object> map =(Map<String, Object>) dataSnapshot.getValue();

                Log.d("trying mapping", "Value is: " + map);
                for(Map.Entry<String, Object> entry: map.entrySet()) {
                    System.out.println("mapping");
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }

                Log.d("HMMMMMM", "Booo");

                mFiredatabase.child("Thoughts").removeEventListener(vel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Errormsg", "Error!!");
            }
        });

        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showData(category);
            }
        });


    }
    public void initialize_views () {
        sitat = findViewById(R.id.sitat_edit);
        statDamNr = findViewById(R.id.statistikkDamNr);
        statMennNr = findViewById(R.id.statistikkMennNr);
        statDamset = findViewById(R.id.statistikkDamset);
        statMennset = findViewById(R.id.statistikkMennset);
        mBnext = findViewById(R.id.mBnext_kropp);
    }
}
