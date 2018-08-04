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
        switch(v.getId()) {
            case R.id.mBkropp:
                startActivity(new Intent(MainActivity.this, DisplayKropp.class));
                break;
            case R.id.mButseendet:
                startActivity(new Intent(MainActivity.this, DisplayKropp.class));
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