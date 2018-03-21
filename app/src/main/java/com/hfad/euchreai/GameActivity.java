package com.hfad.euchreai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static com.hfad.euchreai.GameSetUp.currentRound;
import static com.hfad.euchreai.GameSetUp.players;
import static com.hfad.euchreai.GameSetUp.score;

public class GameActivity extends Activity {

    static ImageView iv_deck, iv_card1, iv_card2, iv_card3, iv_card4, iv_card5, iv_card6, iv_card7, iv_card8, iv_card9, iv_card10, trumpPicture;
    static TextView cardToDiscard, yourTricks, compTricks, yourScore, compScore;
    static Button pass_button3, pickup_button2;
    public static int[] score = new int[2];
    public static int[] tricks = new int[2];
    public static ArrayList<Cards> humanHand;
    static GameSetUp game = new GameSetUp();

    /*
        -creates an instance of a new Game
        -sets up all buttons, textviews, imageviews, initializes scores
        -assigns imagages to player Hand
        -calls setUpInitialRound()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.v("--GameActivity27--", "Hands " + GameSetUp.getAllHands());
        Log.v("--GameActivity28--", "Hand " + GameSetUp.getHumanHand());
        Log.v("--GameActivity29--", "ComHand1: " + GameSetUp.getComHand1());
        Log.v("--GameActivity30--", "ComHand2: " + GameSetUp.getComHand2());
        Log.v("--GameActivity31--", "ComHand3: " + GameSetUp.getComHand3());

        //setUp buttons for picking up and passing
        pickup_button2 = (Button) findViewById(R.id.pickup_button2);
        pass_button3 = (Button) findViewById(R.id.pass_button3);
        pickup_button2.setVisibility(View.INVISIBLE);
        pass_button3.setVisibility(View.INVISIBLE);

        //setting up score and trick id's
        yourScore = (TextView) findViewById(R.id.yourScore);
        compScore = (TextView) findViewById(R.id.compScore);
        yourTricks = (TextView) findViewById(R.id.yourTricks);
        compTricks = (TextView) findViewById(R.id.compTricks);


        //why did I do this?
        cardToDiscard = (TextView) findViewById(R.id.cardToDiscard);

        iv_deck = (ImageView) findViewById(R.id.iv_deck);
        assignImage(Round.turnedUpCard.toString(), iv_deck);

        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        assignImage(players.get(0).hand.get(0).toString(), iv_card1);

        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        assignImage(players.get(0).hand.get(1).toString(), iv_card2);

        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        assignImage(players.get(0).hand.get(2).toString(), iv_card3);

        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        assignImage(players.get(0).hand.get(3).toString(), iv_card4);

        iv_card5 = (ImageView) findViewById(R.id.iv_card5);
        assignImage(players.get(0).hand.get(4).toString(), iv_card5);

        //trumpPicture
        trumpPicture = (ImageView) findViewById(R.id.trumpPicture);
        showTrump(Round.currentTrick.trump);

        //left player card
        iv_card6 = (ImageView) findViewById(R.id.iv_card6);
        //top player card
        iv_card7 = (ImageView) findViewById(R.id.iv_card7);
        //right player card
        iv_card8 = (ImageView) findViewById(R.id.iv_card8);
        //human card
        iv_card9 = (ImageView) findViewById(R.id.iv_card9);

        //used when picking up
        iv_card10 = (ImageView) findViewById(R.id.iv_card10);

        iv_card6.setVisibility(View.INVISIBLE);
        iv_card7.setVisibility(View.INVISIBLE);
        iv_card8.setVisibility(View.INVISIBLE);
        iv_card9.setVisibility(View.INVISIBLE);

        updateGame();
        setUpIntitialRound();

        Log.v("--GameActivity93--", "trump: " + Round.currentTrick.trump);
    }

    /*
        sets Up dialog when user presses this button
        -User will have option to stay in game or
        leave without saving
     */
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

    /*
        takes string as input and assigns an image based on string
     */
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
            case "Back of Card":
                image.setImageResource(R.drawable.card_back_red);
        }
    }

    /*
        function to show User that AI has played card
     */
    public static void showAIPlaycard(String cardToShow) {
        int playerIndex = currentRound.currentTrick.currentPlayer;
        for (int i = 0; i < 4; i++) {
            if (playerIndex == 1) {
                //screen shows left player playing card
                iv_card6.setVisibility(View.VISIBLE);
                assignImage(cardToShow, iv_card6);
            } else if (playerIndex == 2) {
                //screen shows top player playing card
                iv_card7.setVisibility(View.VISIBLE);
                assignImage(cardToShow, iv_card7);
            } else {
                //screen shows right player playing card
                iv_card8.setVisibility(View.VISIBLE);
                assignImage(cardToShow, iv_card8);
            }
        }
    }

    //this function shows the Trump for the round on the bottom right of screen
    public static void showTrump(Cards.SUIT trump) {
        if (trump == Cards.SUIT.CLUBS) {
            trumpPicture.setImageResource(R.drawable.clubs_icon);
        }

        if (trump == Cards.SUIT.SPADES) {
            trumpPicture.setImageResource(R.drawable.spades_icon);
        }

        if (trump == Cards.SUIT.HEARTS) {
            trumpPicture.setImageResource(R.drawable.hearts_icon);
        }

        if (trump == Cards.SUIT.DIAMONDS) {
            trumpPicture.setImageResource(R.drawable.diamonds_icon);
        }
    }

    /*
        - calls humanPlayCard which will remove card from hand
        - if game is finished, it report result to user
        - calls update game
        - calls setUpInitialRound
     */
    public static void cardPlayed(String cardText) {
        game.humanPlayCard(cardText);
        updateGame();
        if(GameSetUp.gameOver){
            //Toast.makeText(GameActivity.this, "Game Over!", Toast.LENGTH_LONG).show();
            if(GameSetUp.score[0] > GameSetUp.score[1]) {
                //Toast.makeText(GameActivity.this, "You won!", Toast.LENGTH_LONG).show();
            }
            else {
                //Toast.makeText(GameActivity.this, "You lost!", Toast.LENGTH_LONG).show();
            }
            return;
        }
        setUpIntitialRound();
    }

    /*
        -if game is not in preGameState, calls updateGame()
        -if dealerNeedsToDiscard, calls setUpDiscard()
        -if not trump has been called by computer, will prompt user to pickUp or pass
     */
    private static void setUpIntitialRound() {
        Log.v("----GameActivity269----", "Initial Round ");
        //Toast.makeText(GameActivity.this, "Initial Round", Toast.LENGTH_LONG).show();

        if(!GameSetUp.currentRound.isInPreGameState){
            Log.v("--GameActivity273--", "Update");
            updateGame();
            return;
        }

        else if(GameSetUp.currentRound.dealerNeedsToDiscard){
            Log.v("---GameActivity279---", "Dealer discard");
            setUpDiscard();
            return;
        }

        boolean[] disabled = {false,false,false,false,false};
        setPlayersCardsClickable(disabled);

        if (!currentRound.isCurrentPlayerAI() && currentRound.trump == null) {
            pickup_button2.setVisibility(View.VISIBLE);
            pass_button3.setVisibility(View.VISIBLE);
            pickup_button2.setText("Pick Up");
            pass_button3.setText("Pass");

            pass_button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GameSetUp.humanPreRoundPass();
                    Log.v("--GameActivity297--", "User passed");
                    showTrump(Round.currentTrick.trump);
                    pickup_button2.setVisibility(View.INVISIBLE);
                    pass_button3.setVisibility(View.INVISIBLE);
                }
            });

            pickup_button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GameSetUp.humanPreRoundCall();
                    Log.v("--GameActivity306--", "User picked up");
                    showTrump(Round.turnedUpCard.suit);
                    pickup_button2.setVisibility(View.INVISIBLE);
                    pass_button3.setVisibility(View.INVISIBLE);
                }
            });
        }
        // need a way to tell dealer to pass or pick up
        //PassPickUpDialog ppd = new PassPickUpDialog(GameActivity.this);
        //ppd.show();
    }

    /*
        - Sets played cards to invisible
        - updates scores if Round is over
        - updates tricks if hands have been played
        - sets humanPlayable cards to clickable
     */
    private static void updateGame() {
        Log.v("--GameActivity319--", "Game Being Updated");
        String trump = "";
        if(GameSetUp.currentRound.trump == null) {
            trump = "trumpNotSet";
        }

        else {
            trump = GameSetUp.currentRound.trump.toString();
        }

        //clears cards
        iv_card6.setVisibility(View.INVISIBLE);
        iv_card7.setVisibility(View.INVISIBLE);
        iv_card8.setVisibility(View.INVISIBLE);
        iv_card9.setVisibility(View.INVISIBLE);

        //resets scores
        score[0] = GameSetUp.score[0];
        yourScore.setText(Integer.toString(score[0]));
        score[1] = GameSetUp.score[1];
        compScore.setText(Integer.toString(score[1]));

        //resets tricks
        tricks[0] = GameSetUp.currentRound.trickCount[0];
        yourTricks.setText(Integer.toString(tricks[0]));
        tricks[1] = GameSetUp.currentRound.trickCount[1];
        compTricks.setText(Integer.toString(tricks[1]));

        boolean[] cardsClickable = {false, false, false, false, false};
        if (!GameSetUp.currentRound.isInPreGameState && GameSetUp.currentRound.outPlayer != 0) {
            cardsClickable = game.getPlayableCardsForHuman();
            Log.v("--GameActivity376--", "cardsClickable: " + Arrays.toString(cardsClickable));
        }
        setPlayersCardsClickable(cardsClickable);

        if (GameSetUp.currentRound.outPlayer == 0) {
            //set up button that allows user to continue game
        }
    }

    /*
        print function to show which player is dealing
     */
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

    /*
        Sets UI to allow user to discard a card
     */
    public static void setUpDiscard() {
        Log.v("--GameActivity328--", "outPlayer: " + Round.outPlayer);
        if (GameSetUp.currentRound.outPlayer == 0){
            GameSetUp.dealerDiscardForRoundStart(GameSetUp.currentRound.turnedUpCard.toString());
            updateGame();
            return;
        }

        updateGame();

        assignImage(Round.turnedUpCard.toString(), iv_card10);
        iv_card1.setClickable(true);
        iv_card2.setClickable(true);
        iv_card3.setClickable(true);
        iv_card4.setClickable(true);
        iv_card5.setClickable(true);
        iv_card10.setClickable(true);

        cardToDiscard.setText("Select a Card to Discard");
        showDiscard();
    }

    /*
        resets the humanHand to include the kitty
        and exclude the discard card
     */
    public static ArrayList<Cards> resetHumanHand() {
        ArrayList<Cards> newHand = new ArrayList<Cards>();

        for (int i = 0; i < 4; i++) {
            newHand.add(players.get(0).hand.get(i));
        }
        newHand.add(players.get(0).hand.get(4));

        return newHand;
    }

    /*
        the selected card will be discarded from the players hand
        and will be replaced by the kitty.  The kitty will then be
        added to the players hand
     */
    public static void showDiscard() {
        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_card1.setVisibility(View.INVISIBLE);
                iv_card10.setVisibility(View.INVISIBLE);
                assignImage(Round.turnedUpCard.toString(), iv_card1);
                players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(players.get(0).hand.get(0).toString());
                players.get(currentRound.currentTrick.currentPlayer).hand.add(Round.turnedUpCard);
                resetHumanHand();
                Log.v("--GameActivity351--", "New Hand: " + resetHumanHand());
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.currentPlayer++;
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });

        iv_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_card2.setVisibility(View.INVISIBLE);
                iv_card10.setVisibility(View.INVISIBLE);
                assignImage(Round.turnedUpCard.toString(), iv_card2);
                players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(players.get(0).hand.get(1).toString());
                players.get(currentRound.currentTrick.currentPlayer).hand.add(Round.turnedUpCard);
                //resetHumanHand();
                Log.v("--GameActivity351--", "New Hand: " + resetHumanHand());
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.incrementTurn();
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });

        iv_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_card3.setVisibility(View.INVISIBLE);
                iv_card10.setVisibility(View.INVISIBLE);
                assignImage(Round.turnedUpCard.toString(), iv_card3);
                players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(players.get(0).hand.get(2).toString());
                players.get(currentRound.currentTrick.currentPlayer).hand.add(Round.turnedUpCard);
                //resetHumanHand();
                Log.v("--GameActivity351--", "New Hand: " + resetHumanHand());
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.incrementTurn();
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });

        iv_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_card4.setVisibility(View.INVISIBLE);
                iv_card10.setVisibility(View.INVISIBLE);
                assignImage(Round.turnedUpCard.toString(), iv_card4);
                players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(players.get(0).hand.get(3).toString());
                players.get(currentRound.currentTrick.currentPlayer).hand.add(Round.turnedUpCard);
                //resetHumanHand();
                Log.v("--GameActivity351--", "New Hand: " + resetHumanHand());
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.incrementTurn();
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });

        iv_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_card5.setVisibility(View.INVISIBLE);
                iv_card10.setVisibility(View.INVISIBLE);
                assignImage(Round.turnedUpCard.toString(), iv_card5);
                players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(players.get(0).hand.get(4).toString());
                players.get(currentRound.currentTrick.currentPlayer).hand.add(Round.turnedUpCard);
                //resetHumanHand();
                Log.v("--GameActivity351--", "New Hand: " + resetHumanHand());
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.incrementTurn();
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });

        iv_card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_card10.setVisibility(View.INVISIBLE);
                iv_card1.setClickable(false);
                iv_card2.setClickable(false);
                iv_card3.setClickable(false);
                iv_card4.setClickable(false);
                iv_card5.setClickable(false);
                iv_card10.setClickable(false);
                cardToDiscard.setText("");
                GameSetUp.currentRound.currentTrick.incrementTurn();
                GameSetUp.currentRound.isInPreGameState = false;
                GameSetUp.makeGameReadyForHuman();
            }
        });
    }

    /*
        sets up dialog for user to pick trump
     */
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

    public static void setPlayersCardsClickable(boolean[] clickable){
        if (clickable[0] == true) {
            iv_card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_card9.setVisibility(View.VISIBLE);
                    assignImage(players.get(0).hand.get(0).toString(), iv_card9);
                    cardPlayed(players.get(0).hand.get(0).toString());
                    iv_card1.setVisibility(View.INVISIBLE);
                }
            });
        }

        if (clickable[1] == true) {
            iv_card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assignImage(players.get(0).hand.get(1).toString(), iv_card9);
                    iv_card9.setVisibility(View.VISIBLE);
                    cardPlayed(players.get(0).hand.get(1).toString());
                    iv_card2.setVisibility(View.INVISIBLE);
                }
            });
        }

        if (clickable[2] == true) {
            iv_card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assignImage(players.get(0).hand.get(2).toString(), iv_card9);
                    iv_card9.setVisibility(View.VISIBLE);
                    cardPlayed(players.get(0).hand.get(2).toString());
                    iv_card3.setVisibility(View.INVISIBLE);
                }
            });
        }

        if (clickable[3] == true) {
            iv_card4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assignImage(players.get(0).hand.get(3).toString(), iv_card9);
                    iv_card9.setVisibility(View.VISIBLE);
                    cardPlayed(players.get(0).hand.get(3).toString());
                    iv_card4.setVisibility(View.INVISIBLE);
                }
            });
        }

        if (clickable.length > 4) {
            if (clickable[4] == true) {
                iv_card5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        assignImage(players.get(0).hand.get(4).toString(), iv_card9);
                        iv_card9.setVisibility(View.VISIBLE);
                        cardPlayed(players.get(0).hand.get(4).toString());
                        iv_card5.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
    }
}
