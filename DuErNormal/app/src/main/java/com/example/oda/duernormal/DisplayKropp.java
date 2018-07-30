package com.example.oda.duernormal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        setContentView(R.layout.activity_display_test);

        initialize_views();

        mBnext.setVisibility(View.VISIBLE);

        //TODO: set text for the
        mStatDam = mKroppStatements[mIndex].getStatDamer();
        mStatMenn = mKroppStatements[mIndex].getStatMenn();

        statDamNr.setText("" + mStatDam);
        statMennNr.setText("" + mStatMenn);

        statDamset.setText(R.string.statdam);
        statMennset.setText(R.string.statmann);
        Log.d("Error2", "Print1 in DisplayKropp");
        final MyDBHandler dbTest = new MyDBHandler(this);
        //Log.d("Check", dbTest.findStatByCategory("Kropp"));
        //sitat.setText(dbTest.findThoughtByCategory("Kropp"));



        // This will be removed as we did not need an arraylist, but a single nr as all columns are same length.
        /*ArrayList<Integer> length_of_db = new ArrayList<Integer>();
        length_of_db = dbTest.find_nr_of_rows_in_database();
        final int col_length = length_of_db.get(0).intValue();
        final String col1 = length_of_db.get(0).toString();
        */

        final int length_of_db = dbTest.find_nr_of_rows_in_database();

        Log.d("length of col", String.valueOf(length_of_db);

        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sitat.setText(dbTest.findThoughtByCategory_test("Kropp", rowNr));
                Log.i("hei", dbTest.findThoughtByCategory_test("Kropp", rowNr));
                statDamNr.setText(dbTest.findStatByCategory("Kropp", rowNr));
                rowNr += 1;
                rowNr = rowNr % length_of_db;
                Log.d("check_row", Integer.toString(rowNr));
                //mIndex = mIndex % len;
                //change();
                //sitatTest.setText(returnThought());

            }
        });
    }

        public void change() {
            mSitat = mKroppStatements[mIndex].getstatementID();
            sitat.setText(mSitat);
            mStatDam = mKroppStatements[mIndex].getStatDamer();
            mStatMenn = mKroppStatements[mIndex].getStatMenn();

            statDamNr.setText("" + mStatDam);
            statMennNr.setText("" + mStatMenn);
        }

        public void initialize_views () {
            sitat = findViewById(R.id.sitat_edit);
            sitatTest = findViewById(R.id.sitatTest1);
            statDamNr = findViewById(R.id.statistikkDamNr);
            statMennNr = findViewById(R.id.statistikkDamset);
            statDamset = findViewById(R.id.statistikkMennNr);
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