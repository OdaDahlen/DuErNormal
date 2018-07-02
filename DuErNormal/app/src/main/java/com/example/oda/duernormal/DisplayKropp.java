package com.example.oda.duernormal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayKropp extends AppCompatActivity {

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


    private DisplayStatements[] mKroppStatements = new DisplayStatements[]{
            new DisplayStatements(R.string.kropp1_tjukk, 60, 50),
            new DisplayStatements(R.string.kropp2_stygg_kropp, 40, 90.0),
            new DisplayStatements(R.string.kropp3_for_tynn, 0.1, 0.1),
            new DisplayStatements(R.string.kropp4_små_muskler, 0.1, 0.1)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kropp);

        sitat = (TextView) findViewById(R.id.sitat);
        statDamNr= (TextView) findViewById(R.id.statistikkDamNr);
        statDamset = (TextView) findViewById(R.id.statistikkDamset);
        statMennNr = (TextView) findViewById(R.id.statistikkMennNr);
        statMennset = (TextView) findViewById(R.id.statistikkMennset);

        mBnext = (Button) findViewById(R.id.mBnext_kropp);
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

        mBnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndex += 1;
                mIndex = mIndex % len;
                change();

            }
        });

    }

    public void change(){
        mSitat = mKroppStatements[mIndex].getstatementID();
        sitat.setText(mSitat);
        mStatDam = mKroppStatements[mIndex].getStatDamer();
        mStatMenn = mKroppStatements[mIndex].getStatMenn();

        statDamNr.setText("" + mStatDam);
        statMennNr.setText("" + mStatMenn);
    }
}
