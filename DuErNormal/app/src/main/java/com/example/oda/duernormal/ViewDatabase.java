package com.example.oda.duernormal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.stats.WakeLock;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

import java.util.ArrayList;
import java.util.Collections;
import android.widget.Adapter;
import java.util.List;

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
    String maa;
    DataSnapshot map2;
    DataSnapshot dataInstance_DS;
    DataSnapshot dataInstance_A;
    StatThought tKropp22;
    StatThought valueof;
    String text_set;
    Long stat_set;
    Iterator<DataSnapshot> it;
    Iterator<StatThought> rev;
    Iterable<DataSnapshot> children;
    String whereAt;
    List<StatThought> mList=new ArrayList();
    String mNext;
    StatThought mNexts;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kropp);
        initialize_views();
        mBnext.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        final String category = intent.getExtras().getString("cat");
        Log.d("Cat in firebase is", category);
       // mAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(this);

        mFiredatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebasedatabase", mFiredatabase.toString());

        //String txt = "hey";
        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

        if (mFiredatabase == null){
            Log.d("isnull", "Noooo");
        }
        vel = mFiredatabase.child("Thoughts").child("Category").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("InOnDataChange", "on data change");


                Log.d("Nr of children", Long.toString(dataSnapshot.getChildrenCount()));

                dataInstance_DS = dataSnapshot;
                Log.d("datainst", dataInstance_DS.toString());
                children = dataInstance_DS.getChildren();
                it = children.iterator();

                sitat.setText(it.next().getValue(StatThought.class).getText());
                //WakeLock
                //Log.d();

                mList.clear();
                for(DataSnapshot children: dataSnapshot.getChildren()){
                    StatThought statThought=children.getValue(StatThought.class);
                    mList.add(statThought);
                }
                Collections.reverse(mList);

                rev = mList.iterator();
                Log.d("reversed", rev.next().getText());
                Log.d("reversed", rev.next().getText());
                //Adapter.notifyDataSetChanged();
                //map2 = dataSnapshot.child("tKropp2");
                //tKropp22 = map2.getValue(StatThought.class);
                //Log.d("t1", tKropp22.getStat().toString());
                //Log.d("t2", tKropp22.getText());

                //Object object = map.get("tKropp2");

                //Log.d("trying mapping", "Value is: " + map);
/*
                for(Map.Entry<String, Object> entry: map.entrySet()) {
                    System.out.println("mapping");
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
*/
                //Map<String, Object> map =(Map<String, Object>) dataSnapshot.getValue();

                //mFiredatabase.child("Thoughts").removeEventListener(vel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Errormsg", "Error!!");
            }
        });

        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "clicked");

                /* String listenTo = "tKropp";
                listenTo = listenTo + "2";
                listenTo = "tKropp2";
                Log.d("listenTo",listenTo);
                dataInstance_A = dataInstance_DS.child(listenTo);
                valueof = dataInstance_A.getValue(StatThought.class);
                Log.d("valueof", valueof.getStat().toString());
                Log.d("valueof", valueof.getText());
                */

                if(!it.hasNext()) {
                    children = dataInstance_DS.getChildren();
                    it = children.iterator();
                }
                StatThought child = it.next().getValue(StatThought.class);
                //it.

                Log.d("childID is now", child.getThoughtID().toString());
                Log.d("children is now", dataInstance_DS.getKey());
                //String a = "hey";
                //sitat.setText("hey");
                //CharSequence text_set = child.getText();
                Log.d("valueof", child.getStat().toString());
                Log.d("valueof", child.getText());
                sitat.setText(child.getText());
                statDamNr.setText(child.getStat().toString());
                statMennNr.setText(child.getStat().toString()); //This is now
                //Log.d("valueof", text_set);

            }
        });


        /*
        onCLick
        check which ID
        reverse order of thing
        database.next()
         */




    }
    public void initialize_views () {
        sitat = findViewById(R.id.sitat);
        statDamNr = findViewById(R.id.statistikkDamNr);
        statMennNr = findViewById(R.id.statistikkMennNr);
        statDamset = findViewById(R.id.statistikkDamset);
        statMennset = findViewById(R.id.statistikkMennset);
        mBnext = findViewById(R.id.mBnext_kropp);
    }
}
