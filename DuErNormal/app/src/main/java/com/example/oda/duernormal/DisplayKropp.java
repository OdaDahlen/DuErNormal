package com.example.oda.duernormal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


    // Dette burde sikkert hentes inn fra en liste/DB:
    private DisplayGen[] mKroppStatements = new DisplayGen[]{
            new DisplayGen(R.string.kropp1_tjukk, 60, 50),
            new DisplayGen(R.string.kropp2_stygg_kropp, 40, 90.0),
            new DisplayGen(R.string.kropp3_for_tynn, 0.1, 0.1),
            new DisplayGen(R.string.kropp4_små_muskler, 0.1, 0.1)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        initialize_views();

        mBnext.setVisibility(View.VISIBLE);

        final int len = mKroppStatements.length;

        // comment


        mSitat = mKroppStatements[mIndex].getstatementID();
        sitat.setText(mSitat);
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
        Integer lengthofrow = dbTest.find_nr_of_rows_in_database(5);
        Log.d("length of row", Integer.toString(lengthofrow));


        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sitat.setText(dbTest.findThoughtByCategory("Kropp"));
                sitat.setText(dbTest.findThoughtByCategory_test("Kropp", rowNr));
                Log.i("hei", dbTest.findThoughtByCategory_test("Kropp", rowNr));
                //mIndex += 1;
                rowNr += 1;
                rowNr = rowNr % 5;
                Log.d("check_row", Integer.toString(rowNr));
                //mIndex = mIndex % len;
                //change();
                //sitatTest.setText(returnThought());
                Log.i("tag", "taggg");

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