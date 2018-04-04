package com.hfad.euchreai;

/**
 * Created by Schlenker18 on 2/27/2018.
 */

import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

public class SmartVirtualPlayer extends Player {

    public SmartVirtualPlayer(String name) {
        super(name);
    }

    /*
        returns a card for SVP to play
     */
    public Cards getCardToPlay(Trick trick) {
        Cards cardToPlayMax = new NullCard();
        Cards cardToPlayMin = null;
        ArrayList<Cards> choices = new ArrayList<Cards>();
        boolean[] choiceArray = this.getPlayableCards(trick.leadingSuit, trick.trump);
        for (int i = 0; i < this.hand.size(); i++)
            if ( choiceArray[i]) {
                choices.add(this.hand.get(i));
            }

        //Log.v("--SVP27--", "choices: " + choices.toString());

        if (trick.leadingSuit == null){
            for (int i = 0; i < choices.size(); i++){
                if (choices.get(i).cardValueNoLead(trick.trump) > cardToPlayMax.cardValueNoLead(trick.trump)) {
                    cardToPlayMax = choices.get(i);
                }
            }
            this.removeCardFromHand(cardToPlayMax);
            return cardToPlayMax;
        }

        //Log.v("--SVP39--", "Choices.size(): " + choices.size());

        for (int i = 0; i < choices.size(); i++){
            if (cardToPlayMin == null) {
                cardToPlayMin = choices.get(i);
            }
            if (choices.get(i).cardValue(trick.trump, trick.leadingSuit) > cardToPlayMax.cardValue(trick.trump, trick.leadingSuit)) {
                cardToPlayMax = choices.get(i);
            }
            else if (choices.get(i).cardValue(trick.trump, trick.leadingSuit) < cardToPlayMin.cardValue(trick.trump, trick.leadingSuit)) {
                cardToPlayMin = choices.get(i);
            }
        }

        Log.v("--SVP--", "CardToPlayMax: " + cardToPlayMax);

        if (cardToPlayMax.cardValue(trick.trump, trick.leadingSuit) > trick.currentWinningCard.cardValue(trick.trump, trick.leadingSuit)){
            this.removeCardFromHand(cardToPlayMax);
            return cardToPlayMax;
        }
        this.removeCardFromHand(cardToPlayMin);
        return cardToPlayMin;
    }

    /*
        method to determine whether SVP will pick up kitty
     */
    public boolean pickUp(Cards kitty){
        ArrayList<Cards> hand = super.hand;
        Cards.SUIT choice = null;
        int max = 0;

        if (handValue(hand,Cards.SUIT.CLUBS)>max) {
            choice = Cards.SUIT.CLUBS;
            max = handValue(hand, Cards.SUIT.CLUBS);
        }

        if (handValue(hand, Cards.SUIT.SPADES)>max) {
            choice = Cards.SUIT.SPADES;
            max = handValue(hand, Cards.SUIT.SPADES);
        }

        if (handValue(hand, Cards.SUIT.HEARTS)>max) {
            choice = Cards.SUIT.HEARTS;
            max = handValue(hand, Cards.SUIT.HEARTS);
        }

        if (handValue(hand, Cards.SUIT.DIAMONDS)>max) {
            choice = Cards.SUIT.DIAMONDS;
            max = handValue(hand, Cards.SUIT.DIAMONDS);
        }

        if (max > 22 && (choice == kitty.suit)) {
            return true;
        }

        return false;
    }

    /*
        method that will decide whether or not SVP picks up as dealer
     */
    public boolean pickUpAsDealer(Cards kitty){
        ArrayList<Cards> hand = super.hand;
        int max = Math.max(Math.max(handValue(hand, Cards.SUIT.CLUBS), handValue(hand, Cards.SUIT.SPADES)),
                Math.max(handValue(hand, Cards.SUIT.DIAMONDS), handValue(hand, Cards.SUIT.HEARTS)));
        hand.add(kitty);
        for (int i = 0; i < 5;i++){
            Cards discard = hand.remove(0);
            int val = (handValue(hand, kitty.suit));

            if (val > max){
                hand.remove(kitty);
                hand.add(discard);
                return true;
            }
            hand.add(discard);
        }
        hand.remove(kitty);

        if (handValue(hand, kitty.suit) > 35) {
            return true;
        }

        return false;
    }

    /*
        discards the card with the lowest value
     */
    public Cards discardDecider(Cards kitty) {
        ArrayList<Cards> hand = super.hand;
        Cards ret = kitty;
        int max = Math.max(Math.max(handValue(hand, Cards.SUIT.CLUBS),handValue(hand, Cards.SUIT.SPADES)),
                Math.max(handValue(hand, Cards.SUIT.DIAMONDS),handValue(hand, Cards.SUIT.HEARTS)));
        hand.add(kitty);

        for (int i = 0; i < 5; i++){
            Cards discard = hand.remove(0);
            int val = (handValue(hand, kitty.suit));

            if (val > max) {
                max = val;
                ret = discard;
            }
            hand.add(discard);
        }

        hand.remove(kitty);
        return ret;
    }

    /*
        This method will find the value for the players hand based
        on the remaining callable suits and call the trump for which their
        hand has the highest value in
     */
    public Cards.SUIT trumpDecider(Cards.SUIT invalid) {
        ArrayList<Cards> hand = super.hand;
        Cards.SUIT choice = null;
        int max = 0;

        if (invalid != Cards.SUIT.CLUBS && (handValue(hand, Cards.SUIT.CLUBS)>max)){
            choice = Cards.SUIT.CLUBS;
            max = handValue(hand, Cards.SUIT.CLUBS);
        }

        if (invalid != Cards.SUIT.SPADES && (handValue(hand, Cards.SUIT.SPADES)>max)){
            choice = Cards.SUIT.SPADES;
            max = handValue(hand, Cards.SUIT.SPADES);
        }

        if (invalid != Cards.SUIT.HEARTS && (handValue(hand, Cards.SUIT.HEARTS)>max)){
            choice = Cards.SUIT.HEARTS;
            max = handValue(hand,Cards.SUIT.HEARTS);
        }

        if (invalid != Cards.SUIT.DIAMONDS && (handValue(hand, Cards.SUIT.DIAMONDS)>max)){
            choice = Cards.SUIT.DIAMONDS;
            max = handValue(hand, Cards.SUIT.DIAMONDS);
        }

        return choice;
    }

    /*
        SVP will call if value is greater than 22
     */
    public boolean callDecider(Cards.SUIT invalid){
        ArrayList<Cards> hand = super.hand;
        int max = 0;

        if (invalid != Cards.SUIT.CLUBS && (handValue(hand, Cards.SUIT.CLUBS)>max)){
            max = handValue(hand, Cards.SUIT.CLUBS);
        }

        if (invalid != Cards.SUIT.SPADES && (handValue(hand, Cards.SUIT.SPADES)>max)){
            max = handValue(hand, Cards.SUIT.SPADES);
        }

        if (invalid != Cards.SUIT.HEARTS && (handValue(hand, Cards.SUIT.HEARTS)>max)){
            max = handValue(hand, Cards.SUIT.HEARTS);
        }

        if (invalid != Cards.SUIT.DIAMONDS && (handValue(hand, Cards.SUIT.DIAMONDS)>max)){
            max = handValue(hand, Cards.SUIT.DIAMONDS);
        }

        return max > 22;
    }

    /*
        boolean return based on value of svp's hand
     */
    public boolean goAloneDecider(Cards.SUIT trump){
        //stub value being passed being going alone is buggy, value was 35
        return handValue(super.hand,trump) > 150;
    }

    /*
        method for obtaining value of svp's hand
     */
    public int handValue (ArrayList<Cards> hand, Cards.SUIT trump) {
        int val = 0;

        for(int i = 0; i < 5;i++) {
            val += hand.get(i).biddingValue(trump);
        }

        return val;
    }
}
