package com.hfad.euchreai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
