package com.hfad.euchreai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    DatabaseHelper myDb;
    int initializer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
    }

//    public void initializeTables() {
//        //setting all tables values to 0
//        myDb.initializeGames();
//        myDb.initializeHands();
//        myDb.initializeTricks();
//        myDb.initializeTrumpCalls();
//        myDb.initializeLoners();
//        myDb.initializeMisc();
//        initializer++;
//    }

    public void onGameActivity(View v) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void onStatsActivity(View v) {
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);
    }

    public void onRulebookActivity(View v) {
        Intent intent = new Intent(MainActivity.this, RulebookActivity.class);
        startActivity(intent);
    }
}
