package com.example.oda.duernormal;

import android.content.Intent;
import android.os.Bundle;
//import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.widget.Toast;

//import com.google.android.gms.stats.WakeLock;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.ListIterator;

import java.util.ArrayList;
//import java.util.Collections;
//import android.widget.Adapter;
import java.util.List;

public class ViewDatabase extends AppCompatActivity {
    //
    DatabaseReference mFiredatabase;
    ValueEventListener vel;
    Button mBnext;
    ImageButton forwButton;
    ImageButton backButton;
    TextView sitat;
    TextView sitatTest;
    TextView statDamNr;
    TextView statMennNr;
    TextView statDamset;
    TextView statMennset;
    int mSitat;
    int mIndex = 1;
    int listIndex;
    int rowNr = 0;
    double mStatDam;
    double mStatMenn;
    private FirebaseAuth mAuth;
    String maa;
    DataSnapshot map2;
    DataSnapshot dataInstance_DS;
    Iterator<DataSnapshot> it;
    ListIterator<StatThought> theList;
    Iterable<DataSnapshot> children;
    List<StatThought> mList=new ArrayList();
    List<StatThought> mListB=new ArrayList();
    String lastText;
    Integer numberOfChildren;
    Integer counter = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_clean);
        initialize_views();
        forwButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        final String category = intent.getExtras().getString("cat");
        Log.d("Cat in firebase is", category);

        FirebaseApp.initializeApp(this);

        mFiredatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebasedatabase", mFiredatabase.toString());

        //String txt = "hey";
        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

        if (mFiredatabase == null){
            Log.d("isnull", "Your database seems to be empty...");
        }
        vel = mFiredatabase.child("Thoughts").child("Category").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("InOnDataChange", "on data change");

                Log.d("Nr of children", Long.toString(dataSnapshot.getChildrenCount()));
                numberOfChildren = (int) (long) dataSnapshot.getChildrenCount();
                dataInstance_DS = dataSnapshot;
                Log.d("datainst", dataInstance_DS.toString());
                children = dataInstance_DS.getChildren();

                mList.clear();
                //Integer index = 0;
                for(DataSnapshot children: dataSnapshot.getChildren()){
                    StatThought statThought=children.getValue(StatThought.class);
                    mList.add(statThought);
                    //mList.add(index, statThought);
                    //index = index +1;
                    //Log.d("index", index.toString());
                }
                //Log.d("index", index.toString());
                mListB = mList;

                //theList = mList.listIterator();
                Integer lengthOfList = mListB.size();

                Integer counter = 0;

                //sitat.setText(mListB.get(0).getText());
                sitat.setText(R.string.kroppStart);
                Log.d("length", lengthOfList.toString());
                Log.d("text", mListB.get(0).getText());
                //Collections.reverse(mList);
                //mFiredatabase.child("Thoughts").removeEventListener(vel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Errormsg", "Error!!");
            }
        });



        forwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer lengthOfList = mListB.size();
                Log.d("clicked", "clicked");
                Log.d("counter", counter.toString());
                Log.d("lengthoflist", lengthOfList.toString());
                if(counter < lengthOfList){
                    Log.d("counter", counter.toString());
                    Log.d( "name", mListB.get(counter).getText());
                    sitat.setText(mListB.get(counter).getText());
                    counter += 1;

                }
                if (counter == lengthOfList) {
                    counter = 0;
                    //sitat.setText(mListB.get(counter).getText());
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer lengthOfList = mListB.size();
                Log.d("back", "back is clicked");


                if(counter <= lengthOfList && counter > 0){
                    Log.d("counter", counter.toString());
                    Log.d( "name", mListB.get(counter).getText());
                    sitat.setText(mListB.get(counter).getText());
                    counter -= 1;
                    Log.d("counterback", counter.toString());

                }

                if (counter == 0) {
                    counter = lengthOfList;
                    //sitat.setText(mListB.get(counter).getText());
                }

            }
        });

                /*if(!theList.hasNext()) {
                    // If we reach the end of the list, we start from the beginning of the list.
                    Log.d("endoflist", "End of list, starting it again.");
                    theList = mList.listIterator();
                    listIndex = 1;
                }

                // The last text will be an item of the list
                lastText = theList.next().getText();
                sitat.setText(lastText);
                listIndex = +1;


                /* String listenTo = "tKropp";
                listenTo = listenTo + "2";
                listenTo = "tKropp2";
                Log.d("listenTo",listenTo);
                dataInstance_A = dataInstance_DS.child(listenTo);
                valueof = dataInstance_A.getValue(StatThought.class);
                Log.d("valueof", valueof.getStat().toString());
                Log.d("valueof", valueof.getText());
                */


                /*
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
                lastText = child.getText();
                sitat.setText(lastText);
                //statDamNr.setText(child.getStat().toString());
                //statMennNr.setText(child.getStat().toString()); //This is now
                //Log.d("valueof", text_set);
                */


                /*
                Log.d("back","back clicked");
                if(!rev.hasNext()) {
                    children = dataInstance_DS.getChildren();
                    rev = mList.iterator();
                }
                */
                /*
                int i;
                String newText;
                children = dataInstance_DS.getChildren();
                rev = mList.iterator();
                for(i=1; i<numberOfChildren; i++){
                    newText = rev.next().getText();
                    if (lastText == newText){
                        sitat.setText(rev.next().getText());
                    }
                }
                sitat.setText(rev.next().getText());
*/

               /* if(!theList.hasPrevious()) {
                    mList.

                    Log.d("back2","List does not have previous:");
                    Integer indx = theList.nextIndex();
                    Log.d("indx", indx);
                    //somehow me must go to end here!! this will obviously not work as we are trying to go backwards on a list from the start.....
                    // omg Oda...
                    lastText = theList.[indx-1].
                    lastText = theList..getText();
                    //Log.d("backtext", lastText);
                }
                lastText = theList.previous().getText();
                sitat.setText(lastText);
                Log.d("Text2", lastText);
                //String m;
                //for(m in mList){
                //    if(rev.next().getText() == lastText){


                    //}
                //}
            }
        });   /*


        onCLick
        check which ID
        reverse order of thing
        database.next()
         */



    }
    public void initialize_views () {
        sitat = findViewById(R.id.sitat);
        backButton = findViewById(R.id.backwButton);
        forwButton = findViewById(R.id.forwButton);


        //statDamNr = findViewById(R.id.statistikkDamNr);
        //statMennNr = findViewById(R.id.statistikkMennNr);
        //statDamset = findViewById(R.id.statistikkDamset);
        //statMennset = findViewById(R.id.statistikkMennset);
        //mBnext = findViewById(R.id.mBnext_kropp);



    }
}
