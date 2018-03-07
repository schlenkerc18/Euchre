package com.hfad.euchreai;

import java.util.ArrayList;

/**
 * Created by Schlenker18 on 1/23/2018.
 */

public class Trick {
    public int leadingPlayer;
    public static Cards.SUIT leadingSuit;
    public int currentPlayer;
    public int currentWinner;
    public Cards currentWinningCard;
    public static Cards.SUIT trump;
    public ArrayList<Cards> cardsPlayed;

    public Trick(int leadPlayer, Cards.SUIT tr) {
        leadingPlayer = leadPlayer % 4;
        currentPlayer = leadPlayer % 4;
        trump = tr;
        cardsPlayed = new ArrayList<Cards>();
    }

    public void playCardForCurrentPlayer(Cards c) {
        if (currentPlayer == leadingPlayer) {
            leadingSuit = c.suit;
            currentWinner = currentPlayer;
            currentWinningCard = c;
        }

        else if (c.greater(currentWinningCard, trump, leadingSuit)) {
            currentWinner = currentPlayer;
            currentWinningCard = c;
        }

        cardsPlayed.add(c);
        incrementTurn();
    }

    public void incrementTurn()
    {
        currentPlayer = (currentPlayer + 1) % 4;
    }

    public boolean isStarted()
    {
        return cardsPlayed.size() != 0;
    }

    public boolean isOver() {
        return cardsPlayed.size() == 4;
    }

    public Trick getNextTrick()
    {
        return new Trick(currentWinner,trump);
    }
}
