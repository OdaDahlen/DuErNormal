package com.example.oda.duernormal;

/**
 * Created by oda on 06.01.18.
 */

public class DisplayStatements {
    private int mStatementID;
    private double mStatDamer;
    private double mStatMenn;

    // Planen er å kunne utvide på alder etterhvert

    public DisplayStatements(int statementResourceID, double statDamer, double statMenn){
        mStatementID = statementResourceID;
        mStatDamer = statDamer;
        mStatMenn = statMenn;
    }

    public int getstatementID() {
        return mStatementID;
    }

    public void setStatementID(int statementID) {
        mStatementID = statementID;
    }

    public double getStatDamer() {
        return mStatDamer;
    }

    public double getStatMenn() {
        return mStatMenn;
    }


    public void setStatDamer(double statD) {
        mStatDamer = statD;
    }

    public void setStatMenn(double statM) {
        mStatMenn = statM;
    }
}


