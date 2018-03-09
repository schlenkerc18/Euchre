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

    public Cards getCardToPlay(Trick trick) {
        Cards cardToPlayMax = null;
        Cards cardToPlayMin = null;
        ArrayList<Cards> choices = new ArrayList<Cards>();
        boolean[] choiceArray = this.getPlayableCards(trick.leadingSuit, trick.trump);
        for (int i = 0; i < this.hand.size(); i++)
            if ( choiceArray[i]) {
                choices.add(this.hand.get(i));
            }

        Log.v("----test----", "choices: " + choices.toString());

        if (trick.leadingSuit == null){
            for (int i = 0; i < choices.size(); i++){
                if (choices.get(i).cardValueNoLead(trick.trump) > cardToPlayMax.cardValueNoLead(trick.trump)) {
                    cardToPlayMax = choices.get(i);
                }
            }
            this.removeCardFromHand(cardToPlayMax);
            return cardToPlayMax;
        }

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

        if (cardToPlayMax.cardValue(trick.trump, trick.leadingSuit) > trick.currentWinningCard.cardValue(trick.trump, trick.leadingSuit)){
            this.removeCardFromHand(cardToPlayMax);
            return cardToPlayMax;
        }
        this.removeCardFromHand(cardToPlayMin);
        return cardToPlayMin;
    }

    public boolean pickUp(Cards kitty){
        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean pickUpAsDealer(Cards kitty){
        Random random = new Random();
        return random.nextBoolean();
    }

    public Cards discardDecider(Cards kitty) {
        Random random = new Random();
        int ind = random.nextInt(6);
        if (ind == 5)
            return kitty;
        else
            return this.hand.get(ind);
    }

    public Cards.SUIT trumpDecider(Cards.SUIT invalid) {
        Cards.SUIT ret = invalid;
        while (ret == invalid) {
            Random random = new Random();
            ret = Cards.SUIT.values()[random.nextInt(4)];
        }
        return ret;
    }

    public boolean callDecider(Cards.SUIT invalid){
        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean goAloneDecider(Cards.SUIT trump){
        Random random = new Random();
        return random.nextBoolean();
    }
}
