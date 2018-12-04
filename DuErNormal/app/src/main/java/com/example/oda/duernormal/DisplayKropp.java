package com.example.oda.duernormal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

public class DisplayKropp extends AppCompatActivity {

    TextView sitat;
    TextView sitatTest;
    TextView statDamNr;
    TextView statMennNr;
    TextView statDamset;
    TextView statMennset;
    Button mBnext;
    int mSitat;
    int mIndex = 1;
    int rowNr = 0;
    double mStatDam;
    double mStatMenn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String category = intent.getExtras().getString("cat");
        Log.d("Cat IMPORTED is:", category);
        setContentView(R.layout.activity_display_test);
        initialize_views();
        Log.d("After", "After initalizing the views");
        mBnext.setVisibility(View.VISIBLE);
        //TODO: set text for the
        statDamset.setText(R.string.statdam);
        statMennset.setText(R.string.statmann);
        Log.d("Error2", "Print1 in DisplayKropp");
        final MyDBHandler dbTest = new MyDBHandler(this);

        //final int length_of_db = dbTest.find_nr_of_rows_in_database();
        final int length_of_db = dbTest.find_nr_of_rows_in_database_by_cat2(category);

        Log.d("length of db", String.valueOf(length_of_db));
        if(length_of_db <= 0){
            Log.d("Error", "Length of db is 0");
        }

        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rowNr = rowNr % length_of_db;
                Log.d("check_row_1", Integer.toString(rowNr));
                Log.isLoggable("Rownr", rowNr);
                sitat.setText(dbTest.findThoughtByCategory_test(category, rowNr));
                Log.i("hei", dbTest.findThoughtByCategory_test(category, rowNr));
                statDamNr.setText(dbTest.findStatWomen(category, rowNr));
                statMennNr.setText(dbTest.findStatMen(category, rowNr));
                rowNr += 1;
                Log.d("check_row_2", Integer.toString(rowNr));
                //change();
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
        public String returnThought() {
            Thought tanketest = new Thought("JEg føler meg DuM", "Kropp", 1, 60,60);
            MyDBHandler dbTest = new MyDBHandler(this);
            Log.d("Error2", "Print2 in DisplayKropp");
            dbTest.addHandler(tanketest);
            Log.d("Error3", "Print3 in DisplayKropp");
            Log.i("tag", dbTest.findByID(11));
            return dbTest.findByID(11);
        }

    }