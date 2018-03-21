package com.hfad.euchreai;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static com.hfad.euchreai.GameActivity.iv_card10;

/**
 * Created by Schlenker18 on 3/8/2018.
 */

public class PassPickUpDialog extends Dialog {
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
        setCancelable(false);

        pass = (Button) findViewById(R.id.pass_button2);
        pickUp = (Button) findViewById(R.id.pickup_button);

        //pass.setOnClickListener(this);
        //pickUp.setOnClickListener(this);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameSetUp.humanPreRoundPass();
                Log.v("----PsPUpDialog45----", "User passed");
                dismiss();
            }
        });

        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameSetUp.humanPreRoundCall();
                //need to tell player to choose card to discard
                GameActivity.setUpDiscard();
                //GameActivity.discardListener();
                //GameSetUp.assignImage(GameSetUp.currentRound.turnedUpCard.toString(), iv_card10);
                Log.v("--PsPUpDialog49--", "dealerNeedstoDiscard:" + Round.dealerNeedsToDiscard);
                Log.v("----PsPUpDialog50----", "User picked up");
                dismiss();
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.pass_button2:
//                GameSetUp.humanPreRoundPass();
//                Log.v("----PsPUpDialog45----", "User passed");
//                dismiss();
//            case R.id.pickup_button:
//                GameSetUp.humanPreRoundCall();
//                //need to tell player to choose card to discard
//                GameActivity.setUpDiscard();
//                Log.v("--PsPUpDialog49--", "dealerNeedstoDiscard:" + Round.dealerNeedsToDiscard);
//                Log.v("----PsPUpDialog50----", "User picked up");
//                dismiss();
//                break;
//            default:
//                break;
//        }
//        dismiss();
//    }
}
