package com.example.oda.duernormal;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

//@IgnoreExtraProperties
public class Thoughts {
    String Category;

    public Thoughts(){

    }
    public Thoughts(String Category){
        this.Category = Category;
    }
}
