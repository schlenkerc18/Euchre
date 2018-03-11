package com.hfad.euchreai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends Activity {

    ImageView iv_deck, iv_card1, iv_card2, iv_card3, iv_card4, iv_card5, iv_card6, iv_card7, iv_card8, iv_card9;
    public int[] score = new int[2];
    public int[] tricks = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameSetUp game = new GameSetUp();

        Log.v("---------test--------", "Hands " + GameSetUp.getAllHands());
        Log.v("---------test--------", "Hand " + GameSetUp.getHumanHand());
        Log.v("-----test-----", "ComHand1: " + GameSetUp.getComHand1());
        Log.v("-----test-----", "ComHand2: " + GameSetUp.getComHand2());
        Log.v("-----test-----", "ComHand3: " + GameSetUp.getComHand3());



        final ArrayList<Cards> humanHand = GameSetUp.getHumanHand();
        final ArrayList<Cards> computerHand1 = GameSetUp.getComHand1();
        final ArrayList<Cards> computerHand2 = GameSetUp.getComHand2();
        final ArrayList<Cards> computerHand3 = GameSetUp.getComHand3();



        iv_deck = (ImageView) findViewById(R.id.iv_deck);
        assignImage(Round.turnedUpCard.toString(), iv_deck);

        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        if (humanHand.get(0) != null) {
            assignImage(humanHand.get(0).toString(), iv_card1);
        }

        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        if (humanHand.get(1) != null) {
            assignImage(humanHand.get(1).toString(), iv_card2);
        }

        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        if (humanHand.get(2) != null) {
            assignImage(humanHand.get(2).toString(), iv_card3);
        }

        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        if (humanHand.get(3) != null) {
            assignImage(humanHand.get(3).toString(), iv_card4);
        }

        iv_card5 = (ImageView) findViewById(R.id.iv_card5);
        if (humanHand.get(4) != null) {
            assignImage(humanHand.get(4).toString(), iv_card5);
        }

        //left player card
        iv_card6 = (ImageView) findViewById(R.id.iv_card6);
        //top player card
        iv_card7 = (ImageView) findViewById(R.id.iv_card7);
        //right player card
        iv_card8 = (ImageView) findViewById(R.id.iv_card8);
        //human card
        iv_card9 = (ImageView) findViewById(R.id.iv_card9);

        iv_card6.setVisibility(View.INVISIBLE);
        iv_card7.setVisibility(View.INVISIBLE);
        iv_card8.setVisibility(View.INVISIBLE);
        iv_card9.setVisibility(View.INVISIBLE);

        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(humanHand.get(0).toString(), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card1.setVisibility(View.INVISIBLE);
            }
        });

        iv_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(humanHand.get(1).toString(), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card2.setVisibility(View.INVISIBLE);
            }
        });

        iv_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(humanHand.get(2).toString(), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card3.setVisibility(View.INVISIBLE);
            }
        });

        iv_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(humanHand.get(3).toString(), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card4.setVisibility(View.INVISIBLE);
            }
        });

        iv_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(humanHand.get(4).toString(), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card5.setVisibility(View.INVISIBLE);
            }
        });

        iv_card1.setClickable(false);
        iv_card2.setClickable(false);
        iv_card3.setClickable(false);
        iv_card4.setClickable(false);
        iv_card5.setClickable(false);

        //Log.v("test", "CHECK score[0]: " + Integer.toString(score[0]));

        setUpIntitalRound();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to exit? Any unsaved progress will be lost.");
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void assignImage(String card, ImageView image) {
        switch (card) {
            case "Nine of Clubs":
                image.setImageResource(R.drawable.nine_of_clubs);
                break;
            case "Ten of Clubs":
                image.setImageResource(R.drawable.ten_of_clubs);
                break;
            case "Jack of Clubs":
                image.setImageResource(R.drawable.jack_of_clubs2);
                break;
            case "Queen of Clubs":
                image.setImageResource(R.drawable.queen_of_clubs2);
                break;
            case "King of Clubs":
                image.setImageResource(R.drawable.king_of_clubs2);
                break;
            case "Ace of Clubs":
                image.setImageResource(R.drawable.ace_of_clubs);
                break;
            case "Nine of Diamonds":
                image.setImageResource(R.drawable.nine_of_diamonds);
                break;
            case "Ten of Diamonds":
                image.setImageResource(R.drawable.ten_of_diamonds);
                break;
            case "Jack of Diamonds":
                image.setImageResource(R.drawable.jack_of_diamonds2);
                break;
            case "Queen of Diamonds":
                image.setImageResource(R.drawable.queen_of_diamonds2);
                break;
            case "King of Diamonds":
                image.setImageResource(R.drawable.king_of_diamonds2);
                break;
            case "Ace of Diamonds":
                image.setImageResource(R.drawable.ace_of_diamonds);
                break;
            case "Nine of Hearts":
                image.setImageResource(R.drawable.nine_of_hearts);
                break;
            case "Ten of Hearts":
                image.setImageResource(R.drawable.ten_of_hearts);
                break;
            case "Jack of Hearts":
                image.setImageResource(R.drawable.jack_of_hearts2);
                break;
            case "Queen of Hearts":
                image.setImageResource(R.drawable.queen_of_hearts2);
                break;
            case "King of Hearts":
                image.setImageResource(R.drawable.king_of_hearts2);
                break;
            case "Ace of Hearts":
                image.setImageResource(R.drawable.ace_of_hearts);
                break;
            case "Nine of Spades":
                image.setImageResource(R.drawable.nine_of_spades);
                break;
            case "Ten of Spades":
                image.setImageResource(R.drawable.ten_of_spades);
                break;
            case "Jack of Spades":
                image.setImageResource(R.drawable.jack_of_spades2);
                break;
            case "Queen of Spades":
                image.setImageResource(R.drawable.queen_of_spades2);
                break;
            case "King of Spades":
                image.setImageResource(R.drawable.king_of_spades2);
                break;
            case "Ace of Spades":
                image.setImageResource(R.drawable.ace_of_spades2);
                break;
        }
    }

    public void cardPlayed(String cardText) {
        GameSetUp.humanPlayCard(cardText);
        updateGame();
        if(GameSetUp.gameOver){
            Toast.makeText(GameActivity.this, "Game Over!", Toast.LENGTH_LONG).show();
            if(GameSetUp.score[0] > GameSetUp.score[1]) {
                Toast.makeText(GameActivity.this, "You won!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(GameActivity.this, "You lost!", Toast.LENGTH_LONG).show();
            }
            return;
        }
        setUpIntitalRound();
    }

    private void setUpIntitalRound() {
        Log.v("----GameActivity257----", "Initial Round ");
        Toast.makeText(GameActivity.this, "Initial Round", Toast.LENGTH_LONG).show();

        if(!GameSetUp.currentRound.isInPreGameState){
            Log.v("--GUI--", "Update");
            updateGame();
            return;
        }

//        else if(GameSetUp.currentRound.dealerNeedsToDiscard){
//            Log.v("---GameActivity267---", "Dealer discard");
//            setUpDiscard();
//            return;
//        }

        boolean[] disabled = {false,false,false,false,false};
        setPlayersCardsClickable(disabled);

        PassPickUpDialog ppd = new PassPickUpDialog(GameActivity.this);
        ppd.show();
    }

    private void updateGame() {
        String trump = "";
        if(GameSetUp.currentRound.trump == null) {
            trump = "trumpNotSet";
        }

        else {
            trump = GameSetUp.currentRound.trump.toString();
        }

        score[0] = GameSetUp.score[0];
        score[1] = GameSetUp.score[1];

        tricks[0] = GameSetUp.currentRound.trickCount[0];
        tricks[1] = GameSetUp.currentRound.trickCount[1];
        printDealer();

        boolean[] cardsClickable = {false, false, false, false, false};
        if (!GameSetUp.currentRound.isInPreGameState && GameSetUp.currentRound.outPlayer != 0) {
            cardsClickable = GameSetUp.getPlayableCardsForHuman();
        }

        setPlayersCardsClickable(cardsClickable);
    }

    private String printDealer() {
        String out = "";
        switch(GameSetUp.currentRound.dealer){
            case 0:
                Toast.makeText(GameActivity.this, "You are dealing", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(GameActivity.this, "Left is dealing", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(GameActivity.this, "Top is dealing", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(GameActivity.this, "Right is dealing", Toast.LENGTH_LONG).show();
                break;
            default:
                System.out.println("Problem with switch for printing dealer!!!!");
        }
        return out;
    }

    private void pickTrump(){
        boolean[] disabled = {false,false,false,false,false};
        setPlayersCardsClickable(disabled);
        int options;

        if (GameSetUp.currentRound.isStickTheDealer) {
            options = 3;
        } else {
            options = 4;
        }

        PickTrumpDialog ptd = new PickTrumpDialog(GameActivity.this, options);
        ptd.show();
    }

    public void setPlayersCardsClickable(boolean[] clickable){
        iv_card1.setClickable(clickable[0]);
        iv_card2.setClickable(clickable[1]);
        iv_card3.setClickable(clickable[2]);
        iv_card4.setClickable(clickable[3]);
        iv_card5.setClickable(clickable[4]);
    }
}
