package com.example.oda.duernormal;

import android.util.Log;

public class Thought {
    // fields
    private int thoughtID;
    private String thoughtText;
    private String ID_category;
    private Integer ID_nrincategory;
    private double StatDamer;
    private double StatMenn;

    //
    //TODO: when DB works, add stats (possibly with age intervals)
    //private double thoughtStatWomen;
    //private double thoughtStatMen;

    // constructors
    public Thought() {}

    public Thought(String thoughttext, String ID_category, Integer ID_nrincategory, double StatDamer, double StatMenn) {
        Log.d("Constructor", "Calling constructor");
        //this.thoughtID = thoughtID;
        this.thoughtText = thoughttext;
        this.ID_category = ID_category;
        this.ID_nrincategory = ID_nrincategory;
        this.StatDamer = StatDamer;
        this.StatMenn = StatMenn;
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

    public void setID_category(String ID_category) {
        this.ID_category = ID_category;
    }
    public String getID_category() {
        return this.ID_category;
    }

    public void setID_nrincategory(Integer ID_nrincategory) {
        this.ID_nrincategory = ID_nrincategory;
    }
    public Integer getID_nrincategory() {
        return this.ID_nrincategory;
    }
    public int getThoughtID() {
        return this.thoughtID;
    }

    public void setThoughtID(int thoughtID) {
        this.thoughtID = thoughtID;
    }

    public String getThoughtText() {
        return this.thoughtText;
    }

    public double getStatDamer() {
        return this.StatDamer;
    }

    public void setStatDamer(double statDamer) {
        this.StatDamer = statDamer;
    }

    public double getStatMenn() {
        return this.StatMenn;
    }

    public void setStatMenn(double statMenn) {
        this.StatMenn = statMenn;
    }
}
