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


    // Dette burde sikkert hentes inn fra en liste/DB:
    private DisplayThought[] mKroppStatements = new DisplayThought[]{
            new DisplayThought(R.string.kropp1_tjukk, 60, 50),
            new DisplayThought(R.string.kropp2_stygg_kropp, 40, 90.0),
            new DisplayThought(R.string.kropp3_for_tynn, 0.1, 0.1),
            new DisplayThought(R.string.kropp4_små_muskler, 0.1, 0.1)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kropp);

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

    public void initialize_views(){
        sitat = findViewById(R.id.sitat);
        statDamNr = findViewById(R.id.statistikkDamNr);
        statMennNr = findViewById(R.id.statistikkDamset);
        statDamset = findViewById(R.id.statistikkMennNr);
        statMennset = findViewById(R.id.statistikkMennset);
        mBnext = findViewById(R.id.mBnext_kropp);
    }

    public void findThought(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Studentstudent =
                dbHandler.findHandler(studentname.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getID()) + " " + student.getStudentName() + System.getProperty("line.separator"));
            studentid.setText("");
            studentname.setText("");
        } else {
            lst.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }
    }
}
