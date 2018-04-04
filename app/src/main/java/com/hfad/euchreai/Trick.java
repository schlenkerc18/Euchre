package com.hfad.euchreai;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import static com.hfad.euchreai.GameSetUp.players;

/**
 * Created by Schlenker18 on 1/23/2018.
 * This class is being used as a controller for each trick that
 * the player goes through.
 */

public class Trick {
    public int leadingPlayer;
    public Cards.SUIT leadingSuit;
    public static Cards.SUIT leadingSuitText;
    public int currentPlayer;
    public int currentWinner;
    public Cards currentWinningCard;
    public Cards.SUIT trump;
    public static ArrayList<Cards> cardsPlayed;
    public static boolean cardsNeedToBeVisible;

    /*
        Sets trump to card leadingPlayer played
        Initializes new list of cards played
     */
    public Trick(int leadPlayer, Cards.SUIT tr) {
        leadingPlayer = leadPlayer % 4;
        currentPlayer = leadPlayer % 4;
        trump = tr;
        cardsPlayed = new ArrayList<Cards>();
    }

    /*
        If leading player: sets the leading suit and that card to winning card.
        Otherwise: compares card to currentWinningCard.
        Adds card to cardsPlayed and then increments turn.
     */
    public void playCardForCurrentPlayer(Cards c) {
        if (currentPlayer == leadingPlayer) {
            leadingSuit = c.suit;
            leadingSuitText = c.suit;
            currentWinner = currentPlayer;
            currentWinningCard = c;
        }

        else if (c.greater(currentWinningCard, trump, leadingSuit)) {
            currentWinner = currentPlayer;
            currentWinningCard = c;
        }

        cardsPlayed.add(c);
        incrementTurn();

        if (currentPlayer == 0) {
            boolean[] clickable = GameSetUp.getPlayableCardsForHuman();
            GameActivity.setPlayersCardsClickable(clickable);
            Log.v("--Trick46--", "Player Hand: " + players.get(0).hand);
            Log.v("--Trick46--", "cardsClickable: " + Arrays.toString(clickable));
            Log.v("--Trick47--", "Player needs to play card");
            //cardsNeedToBeVisible = true;
        }
    }

    /*
        increments currentPlayer
     */
    public void incrementTurn()
    {
        currentPlayer = (currentPlayer + 1) % 4;
    }

    /*
        boolean return false if cardsPlayed is 0, true otherwise
     */
    public boolean isStarted()
    {
        return cardsPlayed.size() != 0;
    }

    /*
        boolean return true if cardsPlayed is 4, false otherwise
     */
    public static boolean isOver() {
        return cardsPlayed.size() == 4;
    }

    /*
        returns an instance of a new trick
     */
    public Trick getNextTrick()
    {
        return new Trick(currentWinner,trump);
    }
}
