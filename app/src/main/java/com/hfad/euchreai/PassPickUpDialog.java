package com.hfad.euchreai;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Schlenker18 on 3/8/2018.
 */

public class PassPickUpDialog extends Dialog implements android.view.View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button pass, pickUp;
    public ImageView turnedUpCard;

    public PassPickUpDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_pickup_dialog);
        turnedUpCard = (ImageView) findViewById(R.id.turned_up_card);
        GameActivity.assignImage(Round.turnedUpCard.toString(), turnedUpCard);

        pass = (Button) findViewById(R.id.pass_button2);
        pickUp = (Button) findViewById(R.id.pickup_button);

        pass.setOnClickListener(this);
        pickUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pass_button2:
                Log.v("----PsPUpDialog40----", "User passed");
                dismiss();
            case R.id.pickup_button:
                Log.v("----PsPUpDialog43----", "User picked up");
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
