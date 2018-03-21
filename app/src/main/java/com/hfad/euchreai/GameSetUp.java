package com.hfad.euchreai;

/**
 * Created by Schlenker18 on 2/27/2018.
 */

import android.util.Log;

import java.util.ArrayList;

public class GameSetUp {

    public static boolean gameOver = false;
    public ArrayList<Cards> allCards = new ArrayList<Cards>();
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Round currentRound;
    static ArrayList<Round> roundHistory = new ArrayList<Round>();
    public static int[] score = new int[2];

    public GameSetUp() {
        setUpAllCards();
        setUpPlayers();
        currentRound = new Round(allCards, players, 0);
        score[0] = 0;
        score[1] = 0;
        Log.v("---GameSetUp26", "is GameSetUp() being called??");
        makeGameReadyForHuman();
    }

    public boolean isOver() {
        if (score[0] >= 10) {
            return true;
        }

        if (score[1] >= 10) {
            return true;
        }

        return false;
    }

    /*Returns 0 if the human team won, 1 if the AI team won, -1 if no one won*/
    public int getWinner() {
        if (score[0] >= 10)
            return 0;
        if (score[1] >= 10)
            return 1;
        return -1;
    }

    private void setUpAllCards() {
        for (int i = 9; i < 15; i++) {
            allCards.add(new Cards(Cards.SUIT.CLUBS, i));
            allCards.add(new Cards(Cards.SUIT.DIAMONDS, i));
            allCards.add(new Cards(Cards.SUIT.HEARTS, i));
            allCards.add(new Cards(Cards.SUIT.SPADES, i));
        }

        Log.v("--GameSetUp58--", "Cards" + allCards.toString());
    }

    private void setUpPlayers() {
        players.add(new Player("Human"));
        players.add(new SmartVirtualPlayer("AI 1"));
        players.add(new SmartVirtualPlayer("AI 2"));
        players.add(new SmartVirtualPlayer("AI 3"));
    }

    public static void makeGameReadyForHuman() {
        //Log.v("--GameSetUp69--", "makeGameReadyForHuman(): " + players.get(currentRound.currentTrick.currentPlayer));
        players.get(currentRound.currentTrick.currentPlayer);
        while (isCurrentPlayerAI() && currentRound.isInPreGameState) {
            makeAIPlayPreRound();
            Log.v("--GameSetUp89--", "making AI play pre round...");
        }

        while (isCurrentPlayerAI() && !currentRound.isInPreGameState) {
            makeAIPlay();
            Log.v("--GameSetUp94--", "making AI play...");
        }


        // there is a possible case that AI finished round and need to do pregame now
        while (isCurrentPlayerAI() && currentRound.isInPreGameState) {
            makeAIPlayPreRound();
            Log.v("--GameSetUp101--", "making AI play pre round...");
        }

        while (isCurrentPlayerAI() && !currentRound.isInPreGameState) {
            makeAIPlay();
            Log.v("--GameSetUp106--", "making AI play...");
        }
    }

    public static void dealerDiscardForRoundStart(String c) {
        currentRound.dealerDiscardForRoundStart(c);
        makeGameReadyForHuman();
    }

    public static void humanPlayCard(String cardBeingPlayed) {
        Log.v("----testGameSetUp88----", "Card being played: " + cardBeingPlayed);
        Cards playedCard = players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
        playCard(playedCard);
        makeGameReadyForHuman();
    }

    public static boolean[] getPlayableCardsForHuman() {
        return players.get(0).getPlayableCards(currentRound.currentTrick.leadingSuit, currentRound.trump);
    }

    public static boolean isCurrentPlayerAI() {
        return currentRound.isCurrentPlayerAI();
    }

    public static void humanPreRoundPass() {
        currentRound.preRoundPass();
        makeGameReadyForHuman();
    }

    public static void humanPreRoundCall() {
        currentRound.preRoundCall();
    }

    public static void makeAIPlay() {
        SmartVirtualPlayer aiPlayer = ((SmartVirtualPlayer) players.get(currentRound.currentTrick.currentPlayer));
        Log.v("---GameSetUP113---", "aiPlayer: " + players.get(currentRound.currentTrick.currentPlayer));
        Cards cardToPlay = aiPlayer.getCardToPlay(currentRound.currentTrick);
        Log.v("---GameSetUP129---", "card AI is playing: " + cardToPlay);
        GameActivity.showAIPlaycard(cardToPlay.toString());
        playCard(cardToPlay);
    }

    public static void makeAIPlayPreRound() {
        int playerIndex = currentRound.currentTrick.currentPlayer;
        SmartVirtualPlayer aip = ((SmartVirtualPlayer) players.get(playerIndex));
        Log.v("--GameSetUp136--", "isCardTurnedUP: " + currentRound.isCardTurnedUp);
        if (currentRound.isCardTurnedUp) {
            if (aip.pickUp(currentRound.turnedUpCard)) {
                currentRound.preRoundCall();
                if (aip.goAloneDecider(currentRound.trump)) {
                    goAlone(playerIndex);
                }
            } else {
                currentRound.preRoundPass();
            }
        } else {
            if (currentRound.isStickTheDealer || aip.callDecider(currentRound.turnedUpCard.suit)) {
                currentRound.preRoundCall(aip.trumpDecider(currentRound.turnedUpCard.suit));
                if (aip.goAloneDecider(currentRound.trump))
                    goAlone(playerIndex);
            } else {
                currentRound.preRoundPass();
            }
        }
    }

    public static void goAlone(int goAlonePlayer) {
        int outPlayer = (goAlonePlayer + 2) % 4;
        currentRound.outPlayer = outPlayer;
        Player p = players.get(outPlayer);
        p.hand.clear();
        for (int i = 0; i < 5; i++) {
            p.hand.add(new NullCard());
        }
    }

    public static void humanPreRoundCallSuit(String suit) {
        if (suit.equals("DIAMONDS")) {
            currentRound.preRoundCall(Cards.SUIT.DIAMONDS);
        } else if (suit.equals("CLUBS")) {
            currentRound.preRoundCall(Cards.SUIT.CLUBS);
        } else if (suit.equals("SPADES")) {
            currentRound.preRoundCall(Cards.SUIT.SPADES);
        } else {
            currentRound.preRoundCall(Cards.SUIT.HEARTS);
        }
    }

    // used for both AI and humans
    private static void playCard(Cards c) {
        currentRound.playCard(c);

        if (currentRound.isOver()) {
            int[] roundScore = currentRound.scoreRound();
            score[0] += roundScore[0];
            score[1] += roundScore[1];
            if (score[0] >= 10 || score[1] >= 10) {
                gameOver = true;
            }
            roundHistory.add(currentRound);
            currentRound = currentRound.getNextRound();
        }
    }

    public static ArrayList<ArrayList<Cards>> getAllHands() {
        ArrayList<ArrayList<Cards>> out = new ArrayList<ArrayList<Cards>>();
        for(Player p : players) {
            out.add(p.hand);
        }
        return out;
    }

    public static ArrayList<Cards> getHumanHand() {
        ArrayList<Cards> ret = new ArrayList<Cards>();
        for (int i = 0; i < 5; i++) {
            ret.add(players.get(0).hand.get(i));
        }
        return ret;
    }

    public static ArrayList<Cards> getComHand1() {
        ArrayList<Cards> ret = new ArrayList<Cards>();
        for (int i = 0; i < 5; i++) {
            ret.add(players.get(1).hand.get(i));
        }
        return ret;
    }

    public static ArrayList<Cards> getComHand2() {
        ArrayList<Cards> ret = new ArrayList<Cards>();
        for (int i = 0; i < 5; i++) {
            ret.add(players.get(2).hand.get(i));
        }
        return ret;
    }

    public static ArrayList<Cards> getComHand3() {
        ArrayList<Cards> ret = new ArrayList<Cards>();
        for (int i = 0; i < 5; i++) {
            ret.add(players.get(3).hand.get(i));
        }
        return ret;
    }
}
