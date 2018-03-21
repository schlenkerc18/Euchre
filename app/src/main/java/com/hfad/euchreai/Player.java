package com.hfad.euchreai;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Schlenker18 on 11/13/2017.
 */

public class Player {
    public String name;
    public ArrayList<Cards> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Cards>();
    }

    public ArrayList<Cards> getCardsInHandOfSuit(Cards.SUIT suit) {

        ArrayList<Cards> arr = new ArrayList<Cards>();

        for (Cards c: hand) {
            if (c.suit == suit) {
                arr.add(c);
            }
        }

        return arr;
    }

    public Cards getHighestValueCardOfSuit(Cards.SUIT suit) {
        Cards highestCard = null;
        for (Cards c: hand) {
            if (c.suit == suit && (highestCard == null || c.value > highestCard.value)) {
                highestCard = c;
            }
        }
        return highestCard;
    }

    public Cards getLowestValueCardOfSuit(Cards.SUIT suit) {
        Cards lowestCard = null;
        for (Cards c: hand) {
            if (c.suit == suit && (lowestCard == null || c.value < lowestCard.value)) {
                lowestCard = c;
            }
        }
        return lowestCard;
    }

    // used for the leading card
    public Cards getHighestValueCard() {
        Cards highestCard = null;

        for (Cards c: hand) {
            if (highestCard == null || c.value > highestCard.value) {
                highestCard = c;
            }
        }

        return highestCard;
    }

    //used for playing off
    public Cards getLowestValueCard() {
        Cards lowestCard = null;

        for (Cards c: hand) {
            if (lowestCard == null || c.value < lowestCard.value) {
                lowestCard = c;
            }
        }

        return lowestCard;
    }

    public boolean hasCardOfSuit(Cards.SUIT suit) {

        for (Cards c: hand) {
            if (c.suit == suit) {
                return true;
            }
        }

        return false;
    }

    public boolean[] getPlayableCards(Cards.SUIT leadingSuit, Cards.SUIT trumpSuit) {
        boolean[] playableCards = new boolean[hand.size()];
        Log.v("--Player--", "Size of Hand: " + (hand.size()));
        boolean sameSuit = false;
        Cards.SUIT sameColorAsTrump = Cards.getSameColorSuit(trumpSuit);
        Cards c;

        Log.v("---Player96---", "leadingSuit: " + leadingSuit);

        for (int i = 0; i < hand.size(); i++) {
            c = hand.get(i);
            // this is getting 20 cards, only want 5
            //Log.v("---Player100---", "hand(i): " + hand.get(i));

            //Log.v("---Player102---", "c.suit: " + c.suit);

            if ((c.suit == leadingSuit && !(c.suit == sameColorAsTrump && c.value == 11))
                    || (leadingSuit == trumpSuit && c.suit == sameColorAsTrump && c.value == 11)) {
                sameSuit = true;
                playableCards[i] = true;
            } else {
                playableCards[i] = false;
            }
        }

        if (!sameSuit) {
            for (int i = 0; i < hand.size(); i++) {
                playableCards[i] = true;
            }
        }

        return playableCards;

    }

    public void removeCardFromHand(Cards card) {
        hand.remove(card);
    }

    public Cards removeCardFromHand(String cardString){
        Cards cardToReturn = null;
        for(int q = 0; q < hand.size(); q++){
            cardToReturn = hand.get(q);
            System.out.println("CardToReturn...Player131: " + cardToReturn.toString());
            if(cardToReturn.toString().equals(cardString)){
                Log.v("---Player133---", "cardBeingRemoved: " + hand.get(q));
                hand.remove(q);
                return cardToReturn;
            }
        }
        return null;
    }
}
