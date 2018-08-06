package com.example.oda.duernormal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The buttons
        Button mBkropp = findViewById(R.id.mBkropp);
        Button mButseendet = findViewById(R.id.mButseendet);
        Button mBsosialt = findViewById(R.id.mBsosialt);
        Button mBforeldre = findViewById(R.id.mBforeldre);
        Button mBpersonlighet = findViewById(R.id.mBpersonlighet);
        Button mBgenerelt = findViewById(R.id.mBgenerelt);
        Button mBtilfeldige = findViewById(R.id.mBtilfeldige);

        mBkropp.setOnClickListener(this);
        mButseendet.setOnClickListener(this);
        mBsosialt.setOnClickListener(this);
        mBforeldre.setOnClickListener(this);
        mBpersonlighet.setOnClickListener(this);
        mBgenerelt.setOnClickListener(this);
        mBtilfeldige.setOnClickListener(this);


        //mBkropp.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
            //    startActivity(new Intent(MainActivity.this, DisplayKropp.class));
            //}
        //});

    }

    @Override
    public void onClick(View v) {
        String cat;
        switch(v.getId()) {
            case R.id.mBkropp:
                cat  = "kropp";
                Intent i = new Intent(MainActivity.this, DisplayKropp.class);
                i.putExtra("cat", cat);
                startActivity(i);
                break;

            case R.id.mButseendet:
                cat  = "utseendet";
                Intent j = new Intent(MainActivity.this, DisplayKropp.class);
                j.putExtra("cat", cat);
                startActivity(j);
                break;

            case R.id.mBpersonlighet:
                cat  = "personlighet";
                Intent n = new Intent(MainActivity.this, DisplayKropp.class);
                n.putExtra("cat", cat);
                startActivity(n);
                break;

            case R.id.mBforeldre:
                cat  = "foreldre";
                Intent l = new Intent(MainActivity.this, DisplayKropp.class);
                l.putExtra("cat", cat);
                startActivity(l);
                break;
            case R.id.mBsosialt:
                cat  = "sosialt";
                Intent m = new Intent(MainActivity.this, DisplayKropp.class);
                m.putExtra("cat", cat);
                startActivity(m);
                break;
            case R.id.mBgenerelt:
                cat  = "generelt";
                //TODO her må vi telle hele databasen. PLuss, kan vi få de random?
                Intent k = new Intent(MainActivity.this, DisplayKropp.class);
                k.putExtra("cat", cat);
                startActivity(k);
                break;
        }

    }
}





// TODO: legge inn flere i flere kategorier
//aktivere stemmeknappene
// TODO: Hvordan ta inn statistikken og hvordan få oppdatert databasen?
// Genere brukeranonymitet vha QR-kode
// Legge til en kolonne i databasen som identifiserer om det er mann eller dame  så de senere kan sorteres på det
// (Vil du se spesifikke for menn damer eller mer generelle?) Må også kunne sammenslå kjønn.
// I tillegg kolonner på statdamer 12-15 år, etc etc.
//Forenkle stemmingen?
//Grafer. Se graf over hvordan den aldersgruppen har utviklet seg
// Personlig utvikling.