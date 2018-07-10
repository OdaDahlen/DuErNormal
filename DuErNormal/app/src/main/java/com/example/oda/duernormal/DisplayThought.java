package com.example.oda.duernormal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayThought extends AppCompatActivity {

    TextView sitat;
    TextView statDamNr;
    TextView statMennNr;
    TextView statDamset;
    TextView statMennset;
    Button mBnext;
    int mSitat;
    int mIndex = 1;
    double mStatDam;
    double mStatMenn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kropp);

        initialize_views();

        mBnext.setVisibility(View.VISIBLE);

        //final int len = mKroppStatements.length;

        // comment


        //mSitat = mKroppStatements[mIndex].getstatementID();
        //sitat.setText(mSitat);
        //mStatDam = mKroppStatements[mIndex].getStatDamer();
        //mStatMenn = mKroppStatements[mIndex].getStatMenn();

        //statDamNr.setText("" + mStatDam);
        //statMennNr.setText("" + mStatMenn);

        //statDamset.setText(R.string.statdam);
        //statMennset.setText(R.string.statmann);

        //mBnext.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        mIndex += 1;
        //        mIndex = mIndex % len;
        //        change();

        //    }
        //});

        // }

        //public void change(){
        //mSitat = mKroppStatements[mIndex].getstatementID();
        //sitat.setText(mSitat);
        //mStatDam = mKroppStatements[mIndex].getStatDamer();
        //mStatMenn = mKroppStatements[mIndex].getStatMenn();

        //statDamNr.setText("" + mStatDam);
        //statMennNr.setText("" + mStatMenn);
        //}
    }

        public void initialize_views () {
            sitat = findViewById(R.id.sitat);
            statDamNr = findViewById(R.id.statistikkDamNr);
            statMennNr = findViewById(R.id.statistikkDamset);
            statDamset = findViewById(R.id.statistikkMennNr);
            statMennset = findViewById(R.id.statistikkMennset);
            mBnext = findViewById(R.id.mBnext_kropp);
        }


    }
