package com.example.oda.duernormal;

import java.util.ArrayList;
import java.util.List;

public class StatThought {

    private Long Stat;
    private String Text;
    private Integer ThoughtID;
    List mList = new ArrayList();

    public StatThought() {
    }


    public List getmList() {
        return mList;
    }

    public Long getStat() {
        return Stat;
    }

    public String getText() {
        return Text;
    }

    public Integer getThoughtID() {return ThoughtID;}



}
