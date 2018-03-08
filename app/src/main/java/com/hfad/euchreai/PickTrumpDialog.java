package com.hfad.euchreai;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Schlenker18 on 3/7/2018.
 */

public class PickTrumpDialog extends Dialog implements android.view.View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button hearts, clubs, diamonds, spades, pass;
    public int options;

    public PickTrumpDialog(Activity a, int i) {
        super(a);
        this.c = a;
        options = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_trump_dialog);

        for (int i = 0; i < 3; i++) {
            if (GameSetUp.currentRound.callableSuits[i] == Cards.SUIT.HEARTS) {
                hearts = (Button) findViewById(R.id.hearts);
                hearts.setOnClickListener(this);
            }

            else if (GameSetUp.currentRound.callableSuits[i] == Cards.SUIT.DIAMONDS) {
                diamonds = (Button) findViewById(R.id.diamonds);
                diamonds.setOnClickListener(this);
            }

            else if (GameSetUp.currentRound.callableSuits[i] == Cards.SUIT.SPADES) {
                spades = (Button) findViewById(R.id.spades);
                spades.setOnClickListener(this);
            }

            else {
                clubs = (Button) findViewById(R.id.clubs);
                clubs.setOnClickListener(this);
            }
        }

        if (options == 4) {
            pass = (Button) findViewById(R.id.pass_button);
            pass.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hearts:
                Round.trump = Cards.SUIT.HEARTS;
                Log.v("-----test------", "trump: " + Round.trump);
                dismiss();
            case R.id.diamonds:
                Round.trump = Cards.SUIT.DIAMONDS;
                Log.v("-----test------", "trump: " + Round.trump);
                dismiss();
                break;
            case R.id.spades:
                Round.trump = Cards.SUIT.SPADES;
                Log.v("-----test------", "trump: " + Round.trump);
                dismiss();
                break;
            case R.id.clubs:
                Round.trump = Cards.SUIT.CLUBS;
                Log.v("-----test------", "trump: " + Round.trump);
                dismiss();
                break;
            case R.id.pass_button:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
