package com.example.oda.duernormal;

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
    public Thought(int thoughtID, String thoughtText) {
        this.thoughtID = thoughtID;
        this.thoughtText = thoughtText;
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
