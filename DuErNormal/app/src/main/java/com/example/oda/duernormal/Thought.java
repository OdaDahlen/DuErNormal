package com.example.oda.duernormal;

import android.util.Log;

public class Thought {
    // fields
    private int thoughtID;
    private String thoughtText;

    //
    //TODO: when DB works, add stats (possibly with age intervals)
    //private double thoughtStatWomen;
    //private double thoughtStatMen;

    // constructors
    public Thought() {}
    public Thought(int thoughtID, String thoughttext) {
        Log.d("Error2", "Print1 in Thought");
        this.thoughtID = thoughtID;
        Log.d("Error2", "Print2 in Thought");
        this.thoughtText = thoughttext;
    }
    public Thought(String thoughttext) {
        //Log.d("Error2", "Print1 in Thought");
        //this.thoughtID = thoughtID;
        Log.d("Error2", "Print2 in Thought");
        this.thoughtText = thoughttext;
    }
    // properties
    public void setID(int thoughtID) {
        this.thoughtID = thoughtID;
    }
    public int getID() {
        return this.thoughtID;
    }
    public void setThoughtText(String thoughtText) {
        this.thoughtText = thoughtText;
    }
    public String gettoughttext() {
        return this.thoughtText;
    }
}
