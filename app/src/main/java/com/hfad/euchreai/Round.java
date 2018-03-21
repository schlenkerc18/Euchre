package com.hfad.euchreai;

import android.util.Log;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Schlenker18 on 12/4/2017.
 */

public class Round {
    public LinkedList<Cards> deck = new LinkedList<Cards>();
    public ArrayList<Cards> allCards;
    public static ArrayList<Player> players;
    public static Trick currentTrick;
    public static ArrayList<Trick> trickHistory = new ArrayList<Trick>();
    public static Cards.SUIT trump;
    public int[] trickCount = new int[2];
    public int callingTeam;
    public int dealer;
    public static Cards turnedUpCard;
    public Cards.SUIT[] callableSuits = new Cards.SUIT[3];
    public boolean isInPreGameState = true;
    public boolean isCardTurnedUp = true;
    public boolean isStickTheDealer = true;
    public static boolean dealerNeedsToDiscard = false;
    public static int outPlayer = -1;

    public Round(ArrayList<Cards> allCardsIn, ArrayList<Player> playersIn, int dealerIn){
        allCards = allCardsIn;
        players = playersIn;
        shuffle();
        deal();
        turnedUpCard = deck.pop();
        Log.v("--Round37--", "TurnedUpCard " + turnedUpCard.toString());
        fillCallableSuits();

        dealer = dealerIn;
        Log.v("--Round41--", "Dealer index: " + dealer);
        currentTrick = new Trick(dealer + 1,trump);
        //Log.v("--Round43--", "Dealer index after new Trick: " + dealer);
        trickCount[0] = 0;
        trickCount[1] = 0;
    }

    private void fillCallableSuits() {
        int index = 0;
        if (!(turnedUpCard.suit==Cards.SUIT.CLUBS)) {
            callableSuits[index] = Cards.SUIT.CLUBS;
            index++;
        }

        if (!(turnedUpCard.suit==Cards.SUIT.DIAMONDS)) {
            callableSuits[index] = Cards.SUIT.DIAMONDS;
            index++;
        }

        if (!(turnedUpCard.suit==Cards.SUIT.HEARTS)) {
            callableSuits[index] = Cards.SUIT.HEARTS;
            index++;
        }

        if (!(turnedUpCard.suit==Cards.SUIT.SPADES)) {
            callableSuits[index] = Cards.SUIT.SPADES;
        }
    }

    public void preRoundPass() {
        if (currentTrick.currentPlayer == dealer) {
            if (isCardTurnedUp) {
                isCardTurnedUp = false;
                GameActivity.assignImage("Back of Card", GameActivity.iv_deck);
            }
        }

        currentTrick.incrementTurn();
        if (currentTrick.currentPlayer == dealer && !isCardTurnedUp) {
            isStickTheDealer = true;
        }
    }

    public boolean isCurrentPlayerAI() {
        return (players.get(currentTrick.currentPlayer) instanceof SmartVirtualPlayer);
    }

    // used when the card is turned up
    public void preRoundCall() {
        trump = turnedUpCard.suit;
        currentTrick.trump = trump;
        currentTrick.currentPlayer = dealer;
        Player p = players.get(dealer);
        Log.v("--Round91--", "CardTurnedUP" + trump);

        if (p instanceof SmartVirtualPlayer) {
            SmartVirtualPlayer svp = ((SmartVirtualPlayer) p);
            Cards cardToDiscard = svp.discardDecider(turnedUpCard);
            if (cardToDiscard != turnedUpCard) {
                svp.removeCardFromHand(cardToDiscard);
                svp.hand.add(turnedUpCard);
            }
            isInPreGameState = false;
            prepareForRoundStart();
        }
        else {
            dealerNeedsToDiscard = true;
            Log.v("--Round107--", "Dealer needs to discard: " + dealerNeedsToDiscard);
        }
    }

    // used when the card is turned down
    public void preRoundCall(Cards.SUIT toBeTrump) {
        trump = toBeTrump;
        currentTrick.trump = trump;
        prepareForRoundStart();
        Log.v("--Round113--", "card turned down:" + trump);
    }

    private void prepareForRoundStart() {
        callingTeam = currentTrick.currentPlayer % 2;
        isInPreGameState = false;

        if (outPlayer == currentTrick.leadingPlayer) {
            currentTrick.leadingPlayer = (currentTrick.leadingPlayer + 1) % 4;
        }

        currentTrick.currentPlayer = currentTrick.leadingPlayer;
    }

    public void dealerDiscardForRoundStart(String c) {
        callingTeam = currentTrick.currentPlayer % 2;
        isInPreGameState = false;

        if (outPlayer == currentTrick.leadingPlayer) {
            currentTrick.leadingPlayer = (currentTrick.leadingPlayer + 1) % 4;
        }

        currentTrick.currentPlayer = currentTrick.leadingPlayer;
        if (!turnedUpCard.toString().equals(c)) {
            Player p = players.get(dealer);
            p.removeCardFromHand(c);
            p.hand.add(turnedUpCard);
        }

    }

    public void playCard(Cards c) {
        System.out.println("playing...Round148...");
        currentTrick.playCardForCurrentPlayer(c);
        if (currentTrick.isOver()) {
            trickCount[currentTrick.currentWinner % 2] ++;
            trickHistory.add(currentTrick);
            currentTrick = currentTrick.getNextTrick();
        }
    }

    public boolean isOver(){
        return trickHistory.size() == 5;
    }

    public int[] scoreRound(){
        int[] ans = new int[2];
        if(trickCount[callingTeam]+trickCount[(callingTeam+1)%2]<5){
            ans[0]=0;
            ans[1]=0;
            return ans;
        }
        if (trickCount[callingTeam] < 3) { //callingTeam got euchred
            ans[(callingTeam + 1) % 2] = 2;
        }
        else if (trickCount[callingTeam] < 5 ) { //calling team gets 1 point
            ans[callingTeam] = 1;
        }
        else //trickCount[callingTeam] == 5)
        {
            if (outPlayer != -1) { //player went alone and won
                ans[callingTeam] = 4;
            }
            else { //team played together and won
                ans[callingTeam] = 2;
            }
        }

        return ans;
    }

    public void shuffle(){
        Random rand = new Random();
        boolean[] used = new boolean[24];
        for (int i = 0; i < 24; i++){
            used[i] = false;
        }

        for (int j = 0; j < 24; j++){
            int y = rand.nextInt(24);

            while (used[y]){
                y = rand.nextInt(24);
            }

            deck.push(allCards.get(y));
            used[y] = true;
        }
    }


    public void deal(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 4; j++) {
                players.get(j).hand.add(draw());
            }
        }

        Log.v("--Round224--", "human reference: " + players.get(0).hand);
        Log.v("--Round224--", "svp1 reference: " + players.get(1).hand);
        Log.v("--Round224--", "svp1 reference: " + players.get(2).hand);
        Log.v("--Round224--", "svp1 reference: " + players.get(3).hand);


//        Log.v("--Round220--", "human hand: " + human.hand);
//        Log.v("--Round220--", "ai1 hand: " + ai1.hand);
//        Log.v("--Round220--", "ai2 hand: " + ai2.hand);
//        Log.v("--Round220--", "ai3 hand: " + ai3.hand);
//
//
//        Log.v("--Round213--", "Player 0 hand size: " + human.hand.size());
//        Log.v("--Round214--", "Player 1 hand size: " + ai1.hand.size());
//        Log.v("--Round215--", "Player 2 hand size: " + ai2.hand.size());
//        Log.v("--Round216--", "Player 3 hand size: " + ai3.hand.size());
    }

    public Cards draw(){
        return deck.pop();
    }

    public Round getNextRound() {
        return new Round(allCards, players, (dealer+1) % 4);
    }
}
