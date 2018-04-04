package com.hfad.euchreai;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Schlenker18 on 11/13/2017.
 */

public class Player {
    public String name;
    public ArrayList<Cards> hand;

    /*
        Constructor
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Cards>();
    }

    /*
        returns array of the cards in hand that match the given suit
     */
    public ArrayList<Cards> getCardsInHandOfSuit(Cards.SUIT suit) {

        ArrayList<Cards> arr = new ArrayList<Cards>();

        for (Cards c: hand) {
            if (c.suit == suit) {
                arr.add(c);
            }
        }

        return arr;
    }

    /*
        returns highest valued card of given suit
     */
    public Cards getHighestValueCardOfSuit(Cards.SUIT suit) {
        Cards highestCard = null;
        for (Cards c: hand) {
            if (c.suit == suit && (highestCard == null || c.value > highestCard.value)) {
                highestCard = c;
            }
        }
        return highestCard;
    }

    /*
        returns lowest valued card of given suit
     */
    public Cards getLowestValueCardOfSuit(Cards.SUIT suit) {
        Cards lowestCard = null;
        for (Cards c: hand) {
            if (c.suit == suit && (lowestCard == null || c.value < lowestCard.value)) {
                lowestCard = c;
            }
        }
        return lowestCard;
    }

    /*
        returns highest value card.
        Used for leading card
     */
    public Cards getHighestValueCard() {
        Cards highestCard = null;

        for (Cards c: hand) {
            if (highestCard == null || c.value > highestCard.value) {
                highestCard = c;
            }
        }

        return highestCard;
    }

    /*
        returns lowest value card
        used for playing off
     */
    public Cards getLowestValueCard() {
        Cards lowestCard = null;

        for (Cards c: hand) {
            if (lowestCard == null || c.value < lowestCard.value) {
                lowestCard = c;
            }
        }

        return lowestCard;
    }

    /*
        boolean method to determine whether player has a
        card of that suit
     */
    public boolean hasCardOfSuit(Cards.SUIT suit) {

        for (Cards c: hand) {
            if (c.suit == suit) {
                return true;
            }
        }

        return false;
    }

    /*
        returns array of cards that the player can play.
        This means that playing that card will not break
        any euchre rules
     */
    public boolean[] getPlayableCards(Cards.SUIT leadingSuit, Cards.SUIT trumpSuit) {
        boolean[] playableCards = new boolean[hand.size()];
        //Log.v("--Player--", "Size of Hand: " + (hand.size()));
        boolean sameSuit = false;
        Cards.SUIT sameColorAsTrump = Cards.getSameColorSuit(trumpSuit);
        Cards c;

        Log.v("---Player97---", "leadingSuit: " + leadingSuit);

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

    /*
        removes card from hand
     */
    public void removeCardFromHand(Cards card) {
        hand.remove(card);
    }

    /*
        Removes card from hand in the ArrayList
     */
    public Cards removeCardFromHand(String cardString){
        Cards cardToReturn = null;
        for(int i = 0; i < hand.size(); i++){
            cardToReturn = hand.get(i);
            //System.out.println("CardToReturn...Player133: " + cardToReturn.toString());
            if(cardToReturn.toString().equals(cardString)){
                Log.v("---Player135---", "cardBeingRemoved: " + hand.get(i));
                hand.remove(i);
                return cardToReturn;
            }
        }
        return null;
    }
}
