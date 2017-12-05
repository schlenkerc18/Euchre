package com.hfad.euchreai;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends Activity {

    ImageView iv_deck, iv_card1, iv_card2, iv_card3, iv_card4, iv_card5, iv_card6, iv_card7, iv_card8, iv_card9;

    ArrayList<Integer> cards;
    ArrayList<Cards> cardsPlayed = new ArrayList<Cards>();
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<VirtualPlayer> virtualPlayers = new ArrayList<VirtualPlayer>();
    public int[] score = new int[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setUpPlayers();
        score[0] = 0;
        score[1] = 1;

        iv_deck = (ImageView) findViewById(R.id.iv_deck);
        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        iv_card5 = (ImageView) findViewById(R.id.iv_card5);
        iv_card6 = (ImageView) findViewById(R.id.iv_card6);
        iv_card7 = (ImageView) findViewById(R.id.iv_card7);
        iv_card8 = (ImageView) findViewById(R.id.iv_card8);
        iv_card9 = (ImageView) findViewById(R.id.iv_card9);

        iv_card1.setVisibility(View.INVISIBLE);
        iv_card2.setVisibility(View.INVISIBLE);
        iv_card3.setVisibility(View.INVISIBLE);
        iv_card4.setVisibility(View.INVISIBLE);
        iv_card5.setVisibility(View.INVISIBLE);
        iv_card6.setVisibility(View.INVISIBLE);
        iv_card7.setVisibility(View.INVISIBLE);
        iv_card8.setVisibility(View.INVISIBLE);
        iv_card9.setVisibility(View.INVISIBLE);

        cards = new ArrayList<>();

        cards.add(109); //nine of clubs
        cards.add(110); //ten of clubs
        cards.add(112); //jack of clubs
        cards.add(113); //queen of clubs
        cards.add(114); //king of clubs
        cards.add(111); //ace of clubs
        cards.add(209); //nine of diamonds
        cards.add(210); //ten of diamonds
        cards.add(212); //jack of diamonds
        cards.add(213); //queen of diamonds
        cards.add(214); //king of diamonds
        cards.add(211); //ace of diamonds
        cards.add(309); //nine of hearts
        cards.add(310); //ten of hearts
        cards.add(312); //jack of hearts
        cards.add(313); //queen of hearts
        cards.add(314); //king of hearts
        cards.add(311); //ace of hearts
        cards.add(409); //nine of spades
        cards.add(410); //ten of spades
        cards.add(412); //jack of spades
        cards.add(413); //queen of spades
        cards.add(414); //king of spades
        cards.add(411); //ace of spades

        iv_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shuffle the cards
                Collections.shuffle(cards);

                //deal the first 5 cards
                assignImage(cards.get(0), iv_card1);
                assignImage(cards.get(1), iv_card2);
                assignImage(cards.get(2), iv_card3);
                assignImage(cards.get(3), iv_card4);
                assignImage(cards.get(4), iv_card5);
                assignImage(cards.get(5), iv_card6);
                assignImage(cards.get(6), iv_card7);
                assignImage(cards.get(7), iv_card8);

                iv_card1.setVisibility(View.VISIBLE);
                iv_card2.setVisibility(View.VISIBLE);
                iv_card3.setVisibility(View.VISIBLE);
                iv_card4.setVisibility(View.VISIBLE);
                iv_card5.setVisibility(View.VISIBLE);
                iv_card6.setVisibility(View.VISIBLE);
                iv_card7.setVisibility(View.VISIBLE);
                iv_card8.setVisibility(View.VISIBLE);

                Toast.makeText(GameActivity.this, "Cards dealt!", Toast.LENGTH_SHORT).show();
            }
        });

        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(cards.get(0), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card1.setVisibility(View.INVISIBLE);
                humanPlayCard(cards.get(0).toString());
            }
        });

        iv_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(cards.get(1), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card2.setVisibility(View.INVISIBLE);
                humanPlayCard(cards.get(1).toString());

            }
        });

        iv_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(cards.get(2), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card3.setVisibility(View.INVISIBLE);
                humanPlayCard(cards.get(2).toString());

            }
        });

        iv_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(cards.get(3), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card4.setVisibility(View.INVISIBLE);
                humanPlayCard(cards.get(3).toString());

            }
        });

        iv_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage(cards.get(4), iv_card9);
                iv_card9.setVisibility(View.VISIBLE);
                iv_card5.setVisibility(View.INVISIBLE);
                humanPlayCard(cards.get(4).toString());
            }
        });
    }

    public void assignImage(int card, ImageView image) {
        switch (card) {
            case 109:
                image.setImageResource(R.drawable.nine_of_clubs);
                break;
            case 110:
                image.setImageResource(R.drawable.ten_of_clubs);
                break;
            case 112:
                image.setImageResource(R.drawable.jack_of_clubs2);
                break;
            case 113:
                image.setImageResource(R.drawable.queen_of_clubs2);
                break;
            case 114:
                image.setImageResource(R.drawable.king_of_clubs2);
                break;
            case 111:
                image.setImageResource(R.drawable.ace_of_clubs);
                break;
            case 209:
                image.setImageResource(R.drawable.nine_of_diamonds);
                break;
            case 210:
                image.setImageResource(R.drawable.ten_of_diamonds);
                break;
            case 212:
                image.setImageResource(R.drawable.jack_of_diamonds2);
                break;
            case 213:
                image.setImageResource(R.drawable.queen_of_diamonds2);
                break;
            case 214:
                image.setImageResource(R.drawable.king_of_diamonds2);
                break;
            case 211:
                image.setImageResource(R.drawable.ace_of_diamonds);
                break;
            case 309:
                image.setImageResource(R.drawable.nine_of_hearts);
                break;
            case 310:
                image.setImageResource(R.drawable.ten_of_hearts);
                break;
            case 312:
                image.setImageResource(R.drawable.jack_of_hearts2);
                break;
            case 313:
                image.setImageResource(R.drawable.queen_of_hearts2);
                break;
            case 314:
                image.setImageResource(R.drawable.king_of_hearts2);
                break;
            case 311:
                image.setImageResource(R.drawable.ace_of_hearts);
                break;
            case 409:
                image.setImageResource(R.drawable.nine_of_spades);
                break;
            case 410:
                image.setImageResource(R.drawable.ten_of_spades);
                break;
            case 412:
                image.setImageResource(R.drawable.jack_of_spades2);
                break;
            case 413:
                image.setImageResource(R.drawable.queen_of_spades2);
                break;
            case 414:
                image.setImageResource(R.drawable.king_of_spades2);
                break;
            case 411:
                image.setImageResource(R.drawable.ace_of_spades2);
                break;
        }
    }

    public int getWinner() {
        if (score[0] >= 10) {
            return 0;
        }

        if (score[1] >= 10) {
            return 1;
        }

        return -1;
    }

    public boolean gameOver() {
        if (score[0] >=10 || score[1] >= 10) {
            return true;
        }

        return false;
    }

    private void setUpPlayers() {
        players.add(new Player("Human"));
        virtualPlayers.add(new VirtualPlayer("AI 1"));
        virtualPlayers.add(new VirtualPlayer("AI 2"));
        virtualPlayers.add(new VirtualPlayer("AI 3"));
    }

    public void humanPlayCard(String cardBeingPlayed) {
        //removeCardFromHand(cardBeingPlayed);
    }




//    public enum Values {
//        NINENOTRUMP(1),
//        TENNOTRUMP(2),
//        JACKNOTRUMP(3),
//        QUEENNOTRUMP(4),
//        KINGNOTRUMP(5),
//        ACENOTRUMP(10),
//        NINETRUMP(12),
//        TENTRUMP(15),
//        QUEENTRUMP(20),
//        KINGTRUMP(25),
//        ACETRUMP(30),
//        LEFTJACK(31),
//        RIGHTJACK(35),
//        NOVALUE(-1);
//    }
}
